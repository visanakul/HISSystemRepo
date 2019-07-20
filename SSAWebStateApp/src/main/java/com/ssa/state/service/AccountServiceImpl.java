package com.ssa.state.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.state.entity.AccountEntity;
import com.ssa.state.exception.AccountNotFoundException;
import com.ssa.state.exception.EmptyAccountException;
import com.ssa.state.model.AccountModel;
import com.ssa.state.model.ForgotPassword;
import com.ssa.state.model.Login;
import com.ssa.state.repository.IAccountRepository;
import com.ssa.state.util.PasswordUtils;

@Service
public class AccountServiceImpl implements IAccountService {

	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	/**
	 * Injecting AccountRepository
	 */
	@Autowired
	private IAccountRepository accountRepository;

	/**
	 * Default constructor
	 */
	public AccountServiceImpl() {
		LOGGER.info("***AccountServiceImpl***");
	}

	@Override
	public boolean addOrUpdateAccount(AccountModel accountModel) {
		LOGGER.info("addOrUpdateAccount start");
		LOGGER.debug("Account model received : " + accountModel);

		accountModel.setActive(true);

		AccountEntity accountEntity = new AccountEntity();
		/**
		 * Converting AccountModel to AccountEntity
		 */
		BeanUtils.copyProperties(accountModel, accountEntity);
		// Encrypting password
		accountEntity.setPassword(PasswordUtils.encryptPassword(accountEntity.getPassword()));
		/**
		 * Saving record
		 */
		accountEntity = accountRepository.save(accountEntity);
		LOGGER.debug("Account entity after save or update : " + accountEntity);
		LOGGER.info("addOrUpdateAccount end");
		return accountEntity.getAccNo() > 0;
	}

	@Override
	public List<AccountModel> getAllAccounts() {
		LOGGER.info("getAllAccounts start");
		List<AccountEntity> accountEntities = accountRepository.findAll();
		if (accountEntities == null || accountEntities.isEmpty()) {
			throw new EmptyAccountException("No account exists");
		}
		LOGGER.debug("Account Entities : " + accountEntities);
		/**
		 * Converting entities list into models
		 */
		List<AccountModel> accountModels = new ArrayList<>(accountEntities.size());

		for (AccountEntity accountEntity : accountEntities) {
			AccountModel accountModel = new AccountModel();
			BeanUtils.copyProperties(accountEntity, accountModel);
			accountModels.add(accountModel);
		}
		LOGGER.debug("Account Models : " + accountModels);
		LOGGER.info("getAllAccounts service end");
		return accountModels;
	}

	/**
	 * Checks email availability from ajax call
	 */
	@Override
	public boolean checkEmail(String email) {
		LOGGER.info("checkEmail service start");
		Integer accNo = accountRepository.findAccNoByEmail(email);
		LOGGER.info("checkEmail service end");
		return accNo != null;
	}

	/**
	 * Account soft delete and active
	 */
	@Override
	public boolean accountDeactivateOrActivate(boolean active, Integer accNo) {
		LOGGER.info("accountDeactivateOrActivate service start");
		Integer status = accountRepository.softDeleteOrActiveById(active, accNo);
		LOGGER.debug("Activation Status : " + status);
		LOGGER.info("accountDeactivateOrActivate service end");
		return status > 0;
	}

	/**
	 * Account Detaills as per accNo
	 */
	@Override
	public AccountModel getAccountByAccNo(Integer accNo) {
		LOGGER.info("getAccountByAccNo service start");
		Optional<AccountEntity> accountEntityOptional = accountRepository.findById(accNo);
		if (!accountEntityOptional.isPresent()) {
			LOGGER.info("Throwing AccountNotFoundException");
			throw new AccountNotFoundException("No account found");
		}
		AccountModel accountModel = new AccountModel();
		LOGGER.debug("AccountEntity : " + accountEntityOptional.get());
		AccountEntity accountEntity = accountEntityOptional.get();
		BeanUtils.copyProperties(accountEntity, accountModel);
		accountModel.setPassword(PasswordUtils.decryptPassword(accountModel.getPassword()));
		LOGGER.debug("AccountModel : " + accountModel);
		LOGGER.info("getAccountByAccNo service end");
		return accountModel;
	}

	@Override
	public Login doesUserExist(String email, String password) {
		LOGGER.info("doesUserExist service start");
		try {
			Login login = accountRepository.findByEmailAndPassword(email,
					PasswordUtils.encryptPassword(password));
			if (login == null) {
				LOGGER.warn("No record");
				return null;
			}
			LOGGER.debug("Login data : " + login.getEmail()+","+login.getActive()+","+login.getRole());
			LOGGER.info("doesUserExist service end");
			return login;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("doesUserExist service end");
		}

	}

	@Override
	public ForgotPassword getDataByEmail(String email) {
		LOGGER.info("getDataByEmail service start");
		try {
			ForgotPassword forgotPassword=accountRepository.findByEmail(email);
			if(forgotPassword==null) {
				LOGGER.warn("No data found");
				return null;
			}
			LOGGER.debug("Forgot Data :" + forgotPassword.getFname()+","+forgotPassword.getLname()+","+forgotPassword.getPassword()+","+forgotPassword.getActive());
			return forgotPassword;
			
		} catch (Exception e) {
			LOGGER.error("Error : " + e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			LOGGER.info("getDataByEmail service end");
		}
	}

}
