package com.ssa.state.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssa.state.model.AccountModel;
import com.ssa.state.model.RoleModel;
import com.ssa.state.service.IAccountService;
import com.ssa.state.service.IRoleService;
import com.ssa.state.service.ISendMailService;

import static com.ssa.state.util.ConstantUtils.*;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
	 * Injecting SendMail Service
	 */
	@Autowired
	private ISendMailService sendMailService;

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
	public String showAccountAddOrEditForm(@RequestParam(value = "accNo", required = false) Integer accNo,
			Model model) {
		LOGGER.info("showAccountAddOrEditForm start");

		try {
			AccountModel accountModel = null;
			if (accNo == null) {
				LOGGER.info("Request for new account");
				accountModel = new AccountModel();
				model.addAttribute("operationSelectedMsg", "Account Registration");
			} else {
				LOGGER.info("Request for edit account account no : " + accNo);
				accountModel = accountService.getAccountByAccNo(accNo);
				model.addAttribute("operationSelectedMsg", "Account Update");
			}
			
			model.addAttribute("account", accountModel);
			loadGenders(model);
			loadRoles(model);
		} catch (Exception exception) {
			LOGGER.error("Error in hadling showAccountAddOrEditForm request");
			throw new RuntimeException(exception.getMessage());
		}
		LOGGER.info("showAccountAddForm end");
		return ACC_VIEW;
	}

	/**
	 * Save account info in Db table
	 * 
	 * @return
	 */
	@RequestMapping(value = SAVE_ACC_POST_URL, method = RequestMethod.POST)
	public String saveOrUpdateAccountInfo(@Valid @ModelAttribute AccountModel accountModel, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		LOGGER.info("saveOrUpdateAccountInfo start");

		try {
			boolean registerStatus = accountModel.getAccNo() == null;

			String rawPassword = accountModel.getPassword();
			if (!validateData(bindingResult, model)) {
				loadGenders(model);
				loadRoles(model);
				LOGGER.info("saveOrUpdateAccountInfo end");
				return ACC_VIEW;
			}
			LOGGER.debug("User data Received: " + accountModel.toString());

			boolean result = accountService.addOrUpdateAccount(accountModel);
			if (result) {
				redirectAttributes.addFlashAttribute(ACC_REG_OR_UPDATE_REDIRECT_KEY,
						registerStatus ? ACC_REG_SUCCESS_REDIRECT_VALUE : ACC_UPDATE_SUCCESS_REDIRECT_VALUE);
				accountModel.setPassword(rawPassword);
				sendMailService.sendMail(accountModel);
			} else {
				redirectAttributes.addFlashAttribute(ACC_REG_OR_UPDATE_REDIRECT_KEY,
						registerStatus ? ACC_REG_FAIL_REDIRECT_VALUE : ACC_UPDATE_FAIL_REDIRECT_VALUE);

			}

			String operationPerformedMsg=registerStatus ? "Account Registration" : "Account Update";
			LOGGER.debug("operationPerformedMsg : "+operationPerformedMsg);
			redirectAttributes.addFlashAttribute("operationPerformedMsg",operationPerformedMsg);
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception);
			throw new RuntimeException(exception.getMessage());
		}
		LOGGER.info("saveOrUpdateAccountInfo end");

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
	 * Send all records
	 * 
	 * @return
	 */

	@RequestMapping(SEND_ALL_ACCOUNTS_1_GET_URL)
	public String sendAllAccount1(Model model) {
		LOGGER.info("sendAllAccount1 start");
		List<AccountModel> accountModels = accountService.getAllAccounts();
		LOGGER.debug("AccountModels " + accountModels);
		model.addAttribute("accountModelList", accountModels);
		LOGGER.info("sendAllAccount1 end");
		return ALL_ACC_1_VIEW;
	}

	/**
	 * Show email page for testing
	 * 
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
	 * 
	 * @return
	 */
	@RequestMapping(CHECK_EMAIL_GET_URL)
	public @ResponseBody String doesEmailExist(@RequestParam("email") String email) {
		LOGGER.info("doesEmailExist start");
		LOGGER.debug("Email id : " + email);
		boolean flag = accountService.checkEmail(email);
		String msg = flag ? EMAIL_EXISTS : EMAIL_OK;
		LOGGER.info("doesEmailExist end");
		return msg;
	}

	@RequestMapping("soft_delete/{accNo}/{active}/account")
	@Transactional
	public @ResponseBody String softDeleteRequest(@PathVariable("accNo") Integer accNo,
			@PathVariable("active") boolean active) {
		try {
			LOGGER.info("softDeleteRequest start");
			LOGGER.debug("Account no : " + accNo + " Active : " + active);
			boolean status = accountService.accountDeactivateOrActivate(active, accNo);
			LOGGER.debug("Status : " + status);
			LOGGER.info("softDeleteRequest end");
			if (status) {
				return "OK";
			} else {
				return "Error";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
