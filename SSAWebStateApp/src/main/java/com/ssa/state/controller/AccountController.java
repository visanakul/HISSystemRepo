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

import com.ssa.state.config.ApplicationConfig;
import com.ssa.state.exception.AccountNotFoundException;
import com.ssa.state.model.AccountModel;
import com.ssa.state.model.RoleModel;
import com.ssa.state.service.IAccountService;
import com.ssa.state.service.IRoleService;
import com.ssa.state.service.ISendMailService;
import static com.ssa.state.util.ConstantUtils.*;
import static com.ssa.state.util.ConstantUtils.Account.*;
import java.util.List;

import javax.transaction.Transactional;
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
	 * Injecting SendMail Service
	 */
	@Autowired
	private ISendMailService sendMailService;

	/**
	 * Injecting Application Properties Service
	 */
	@Autowired
	private ApplicationConfig appConfig;

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
				model.addAttribute(OP_SELECTED_KEY, ACCOUNT_REGISTRATION_VALUE);
				model.addAttribute(OPERATION_BUTTON_TEXT, SAVE_TITLE);
			} else {
				LOGGER.info("Request for edit account account no : " + accNo);
				accountModel = accountService.getAccountByAccNo(accNo);
				model.addAttribute(OP_SELECTED_KEY, ACCOUNT_UPDATE_VALUE);
				model.addAttribute(OPERATION_BUTTON_TEXT, UPDATE_TITLE);
			}

			model.addAttribute(ACC_MODEL_KEY, accountModel);
			loadGenders(model);
			loadRoles(model);
		} catch (Exception exception) {
			LOGGER.error("Error in hadling showAccountAddOrEditForm request");
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("showAccountAddForm end");
		}
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
		try {
			if (!validateData(bindingResult, model)) {
				loadGenders(model);
				loadRoles(model);
				LOGGER.info("saveAccountInfo end");
				return ACC_VIEW;
			}
			LOGGER.debug("User data Received: " + accountModel.toString());

			boolean result = accountService.addOrUpdateAccount(accountModel);
			if (result) {
				redirectAttributes.addFlashAttribute(ACC_REG_KEY, ACC_REG_SUCCESS_VALUE);
				sendMailService.sendMail(accountModel);
			} else {
				redirectAttributes.addFlashAttribute(ACC_REG_KEY, ACC_REG_FAIL_VALUE);

			}
			redirectAttributes.addFlashAttribute(OP_SELECTED_KEY, ACCOUNT_REGISTRATION_VALUE);
			redirectAttributes.addFlashAttribute(OPERATION_BUTTON_TEXT, SAVE_TITLE);
			return REDIRECT_SHOW_ACC_FORM_GET_URL;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception);
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("saveAccountInfo end");
		}

	}

	/**
	 * Update account info in Db table
	 * 
	 * @return
	 */
	@RequestMapping(value = UPDATE_ACC_POST_URL, method = RequestMethod.POST)
	public String updateAccountInfo(@Valid @ModelAttribute AccountModel accountModel, BindingResult bindingResult,
			Model model) {
		LOGGER.info("updateAccountInfo start");
		try {
			loadGenders(model);
			loadRoles(model);
			if (!validateData(bindingResult, model)) {
				LOGGER.info("updateAccountInfo end");
				return ACC_VIEW;
			}
			LOGGER.debug("User data Received: " + accountModel.toString());

			boolean result = accountService.addOrUpdateAccount(accountModel);
			if (result) {
				model.addAttribute(ACC_UPDATE_KEY, ACC_UPDATE_SUCCESS_VALUE);
				LOGGER.info(ACC_UPDATE_SUCCESS_VALUE);
				sendMailService.sendMail(accountModel);
			} else {
				model.addAttribute(ACC_UPDATE_KEY, ACC_UPDATE_FAIL_VALUE);
				LOGGER.info(ACC_UPDATE_FAIL_VALUE);
			}
			model.addAttribute(ACC_MODEL_KEY, accountModel);
			model.addAttribute(OP_SELECTED_KEY, ACCOUNT_UPDATE_VALUE);
			model.addAttribute(OPERATION_BUTTON_TEXT, UPDATE_TITLE);
			return ACC_VIEW;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception);
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("updateAccountInfo end");
		}
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
		try {
			List<AccountModel> accountModels = accountService.getAllAccounts();
			if (accountModels == null || accountModels.size() == 0) {
				throw new AccountNotFoundException("No account available");
			}
			LOGGER.debug("AccountModels " + accountModels);
			return accountModels;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("sendAllAccount end");
		}
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
		model.addAttribute(ALL_ACCOUNTS_MODEL_KEY, accountModels);
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
