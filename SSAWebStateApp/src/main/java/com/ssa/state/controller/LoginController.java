package com.ssa.state.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.ssa.state.model.AccountModel;
import com.ssa.state.model.ForgotPassword;
import com.ssa.state.model.Login;
import com.ssa.state.model.LoginModel;
import com.ssa.state.service.IAccountService;
import com.ssa.state.util.PasswordUtils;

import static com.ssa.state.util.ConstantUtils.Login.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.ssa.state.util.ConstantUtils.Home.*;
import static com.ssa.state.util.ConstantUtils.ForgotPassword.*;

/**
 * Login and forgot password functionality
 * 
 * @author VISHAL
 *
 */
@Controller
public class LoginController {
	/**
	 * Logger to log
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Injecting AccountService
	 */
	@Autowired(required = true)
	private IAccountService accountService;

	/**
	 * Show login form
	 * 
	 * @return
	 */
	@RequestMapping(SHOW_LOGIN_GET_URL)
	public String showLoginPage(Model model) {
		LOGGER.info("showLoginPage start");
		model.addAttribute(LOGIN_MODEL_KEY, new LoginModel());
		LOGGER.info("showLoginPage end");
		return LOGIN_VIEW;
	}

	/**
	 * Show login form
	 * 
	 * @return
	 */
	@RequestMapping(value = CHECK_LOGIN_POST_URL, method = RequestMethod.POST)
	public String checkLoginData(HttpSession ses, @ModelAttribute("login") LoginModel loginModel, 
			Model model) {
		try {
			LOGGER.info("checkLoginData start");
			Login login = accountService.doesUserExist(loginModel.getEmail(), 
					loginModel.getPassword());
			if (login == null) {
				LOGGER.warn("Login fail");
				model.addAttribute(LOGIN_KEY, LOGIN_FAILED_VALUE);
				model.addAttribute(LOGIN_MODEL_KEY, loginModel);
				return LOGIN_VIEW;
			}
			LOGGER.debug("Login data : " + login.getEmail() + "," + login.getActive() + "," + login.getRole());

			if (login.getActive() == 0) {
				LOGGER.warn("Account inactive!!!");
				model.addAttribute(LOGIN_KEY, ACCOUNT_INACTIVE_VALUE);
				model.addAttribute(LOGIN_MODEL_KEY, loginModel);
				return LOGIN_VIEW;
			}
			
			ses.setAttribute(LOGIN_SESSION_KEY, login);
			LOGGER.info("Login success");
			model.addAttribute(LOGIN_KEY, LOGIN_SUCCESS_VALUE);
			return LOGIN_TO_MENU_REDIRECT_GET_URL;
		} catch (Exception exception) {
			LOGGER.error("Login error : " + exception.getMessage());
			exception.printStackTrace();
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("checkLoginData end");
		}
	}
	/**
	 * Show Home form
	 * 
	 * @return
	 */
	@RequestMapping(SHOW_HOME_GET_URL)
	public String showHomePage(HttpSession ses, Model model) {
		LOGGER.info("showHomePage start");
//		Login loginSession=(Login) ses.getAttribute(LOGIN_SESSION_KEY);
//		LOGGER.info("Login session :"+loginSession);
//		model.addAttribute(LOGIN_EMAIL_KEY, loginSession.getEmail());
		LOGGER.info("showHomePage end");
		return HOME_VIEW;
	}

	/**
	 * Show Menu form
	 * 
	 * @return
	 */
	@RequestMapping(SHOW_MENU_GET_URL)
	public String showMenuPage(Model model) {
		LOGGER.info("showMenuPage start");
		LOGGER.info("showMenuPage end");
		return MENU_VIEW;
	}

	/**
	 * Show forgot password form
	 * 
	 * @return
	 */
	@RequestMapping(SHOW_FORGOT_PASSWORDD_GET_URL)
	public String showForgotPasswordPage(Model model) {
		LOGGER.info("showForgotPasswordPage start");
		LOGGER.info("showForgotPasswordPage end");
		return FORGOT_PASSWORD_VIEW;
	}

	/**
	 * Send password in email
	 * 
	 * @return
	 */
	@RequestMapping(value = SEND_PASSWORD_POST_URL, method = RequestMethod.POST)
	public String sendPasswordToMail(@RequestParam("email") String email, Model model) {
		LOGGER.info("sendPasswordToMail start");
		try {
			LOGGER.debug("Email : " + email);
			ForgotPassword forgotPassword = accountService.getDataByEmail(email);
			if (forgotPassword == null) {
				LOGGER.warn("No data found");
				model.addAttribute(FORGOT_PASSWORD_KEY, EMAIL_NOT_FOUND_VALUE);
			} else if (forgotPassword.getActive() == 0) {
				model.addAttribute(FORGOT_PASSWORD_KEY, ACCOUNT_INACTIVE_VALUE);
			} else {
				LOGGER.debug("Password : " + PasswordUtils.decryptPassword(forgotPassword.getPassword()));
				model.addAttribute(FORGOT_PASSWORD_KEY, PASSWORD_SENT_VALUE);
			}
			return FORGOT_PASSWORD_VIEW;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("sendPasswordToMail end");
		}

	}
}
