package com.ssa.state.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssa.state.model.AccountModel;
import com.ssa.state.model.RoleModel;
import com.ssa.state.service.IAccountService;
import com.ssa.state.service.IRoleService;

import static com.ssa.state.util.ConstantUtils.*;
import java.util.List;

import javax.validation.Valid;

/**
 * Handles all request related to Account
 * 
 * @author VISHAL
 *
 */
@Controller
public class AccountController {
	/**
	 * Logger to log
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	/**
	 * Injecting Role Service
	 */
	@Autowired
	private IRoleService roleService;

	/**
	 * Injecting Account Service
	 */
	@Autowired
	private IAccountService accountService;

	/**
	 * Default Constructor
	 */
	public AccountController() {
		LOGGER.info("***AccountController***");
	}

	/**
	 * Show Account form and load model with AccountModel,Genders and Roles info
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(SHOW_ACC_FORM_GET_URL)
	public String showAccountAddForm(Model model) {
		LOGGER.info("showAccountAddForm start");
		AccountModel accountModel = new AccountModel();
		model.addAttribute("account", accountModel);
		loadGenders(model);
		loadRoles(model);
		LOGGER.info("showAccountAddForm end");
		return ACC_VIEW;
	}

	/**
	 * Save account info in Db table
	 * 
	 * @return
	 */
	@RequestMapping(value = SAVE_ACC_POST_URL, method = RequestMethod.POST)
	public String saveAccountInfo(@Valid @ModelAttribute AccountModel accountModel, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		LOGGER.info("saveAccountInfo start");
		if (!validateData(bindingResult, model)) {
			LOGGER.info("saveAccountInfo end");
			return ACC_VIEW;
		}
		LOGGER.debug("User data Received: " + accountModel.toString());

		boolean result = accountService.addAccount(accountModel);
		if (result) {
			redirectAttributes.addFlashAttribute(ACC_REG_REDIRECT_KEY, ACC_REG_SUCCESS_REDIRECT_VALUE);
		} else {
			redirectAttributes.addFlashAttribute(ACC_REG_REDIRECT_KEY, ACC_REG_FAIL_REDIRECT_VALUE);

		}
		LOGGER.info("saveAccountInfo end");

		return REDIRECT_SHOW_ACC_FORM_GET_URL;
	}

	/**
	 * Server side validation
	 * 
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	private boolean validateData(BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			LOGGER.error("***Server side validation fails***");
			loadGenders(model);
			loadRoles(model);
			return false;
		}
		LOGGER.info("***Server side validation success***");
		return true;
	}

	/**
	 * Loading Genders
	 * 
	 * @param model
	 */
	private void loadGenders(Model model) {
		LOGGER.info("***Gender Loading start***");
		final String[] gens = { MALE, FEMALE };
		model.addAttribute(GENDERS_MODEL_KEY, gens);
		LOGGER.info("***Gender Loading end***");
	}

	/**
	 * Loading Roles
	 * 
	 * @param model
	 */
	private void loadRoles(Model model) {
		LOGGER.info("***Roles Loading start***");
		List<RoleModel> roles = roleService.getAllRoles();
		model.addAttribute(ROLES_MODEL_KEY, roles);
		LOGGER.info("***Roles Loading end***");
	}

	/**
	 * Show display all account form
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(SHOW_ALL_ACC_GET_URL)
	public String showAllAccountForm(Model model) {
		LOGGER.info("showAllAccountForm start");

		LOGGER.info("showAllAccountForm end");
		return ALL_ACC_VIEW;
	}

	/**
	 * Send all records to ajax call for displaying it in JQuery data table
	 * 
	 * @return
	 */

	@RequestMapping(SEND_ALL_ACCOUNTS_GET_URL)
	public @ResponseBody List<AccountModel> sendAllAccount() {
		LOGGER.info("sendAllAccount start");
		List<AccountModel> accountModels = accountService.getAllAccounts();
		LOGGER.debug("AccountModels " + accountModels);
		LOGGER.info("sendAllAccount end");
		return accountModels;
	}
	/**
	 * Show email page for testing
	 * @return
	 */
	@RequestMapping(SHOW_EMAIL_GET_URL)
	public String showEmailPage() {
		LOGGER.info("showEmailPage start");
		
		LOGGER.info("showEmailPage end");
		return EMAIL_VIEW;
	}
	/**
	 * Check email availability and send message
	 * @return
	 */
	@RequestMapping(CHECK_EMAIL_GET_URL)
	public @ResponseBody String doesEmailExist(@RequestParam("email") String email) {
		LOGGER.info("doesEmailExist start");
		LOGGER.debug("Email id : "+email);
		boolean flag=accountService.checkEmail(email);
		String msg=flag?EMAIL_EXISTS:EMAIL_OK;
		LOGGER.info("doesEmailExist end");
		return msg;
	}

}
