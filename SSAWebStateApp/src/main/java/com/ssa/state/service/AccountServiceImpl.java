package com.ssa.state.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssa.state.entity.AccountEntity;
import com.ssa.state.exception.AccountNotFoundException;
import com.ssa.state.exception.EmptyAccountException;
import com.ssa.state.model.AccountModel;
import com.ssa.state.repository.IAccountRepository;

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
	 * Injecting BCryptPasswordEncoder for password encoding 
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/**
	 * Default constructor
	 */
	public AccountServiceImpl() {
		LOGGER.info("***AccountServiceImpl***");
	}

	@Override
	public boolean addOrUpdateAccount(AccountModel accountModel) {
		LOGGER.info("addOrUpdateAccount start");
		LOGGER.debug("Account model received : "+accountModel);
		
		accountModel.setActive(true);
	
		/**
		 * Encoding password
		 */
		String encodedPassword=bCryptPasswordEncoder.encode(accountModel.getPassword());
		accountModel.setPassword(encodedPassword);
		LOGGER.debug("Encoded password : "+encodedPassword);
		
		AccountEntity accountEntity=new AccountEntity();
		/**
		 * Converting AccountModel to AccountEntity
		 */
		BeanUtils.copyProperties(accountModel, accountEntity);
		/**
		 * Saving record
		 */
		accountEntity=accountRepository.save(accountEntity);
		LOGGER.debug("Account entity after save or update : "+accountEntity);
		LOGGER.info("addOrUpdateAccount end");
		return accountEntity.getAccNo()>0;
	}

	@Override
	public List<AccountModel> getAllAccounts() {
		LOGGER.info("getAllAccounts start");
		List<AccountEntity> accountEntities=accountRepository.findAll();
		if(accountEntities==null || accountEntities.isEmpty()) {
			throw new EmptyAccountException("No account exists");
		}
		LOGGER.debug("Account Entities : "+accountEntities);
		/**
		 * Converting entities list into models
		 */
		List<AccountModel> accountModels=new ArrayList<>(accountEntities.size());
		
		for(AccountEntity accountEntity:accountEntities) {
			AccountModel accountModel=new AccountModel();
			BeanUtils.copyProperties(accountEntity, accountModel);
			accountModels.add(accountModel);
		}
		LOGGER.debug("Account Models : "+accountModels);
		LOGGER.info("getAllAccounts service end");
		return accountModels;
	}

	/**
	 * Checks email availability from ajax call
	 */
	@Override
	public boolean checkEmail(String email) {
		LOGGER.info("checkEmail service start");
		Integer accNo=accountRepository.findAccNoByEmail(email);
		LOGGER.info("checkEmail service end");
		return accNo!=null;
	}

	/**
	 * Account soft delete and active
	 */
	@Override
	public boolean accountDeactivateOrActivate(boolean active, Integer accNo) {
		LOGGER.info("accountDeactivateOrActivate service start");
		Integer status=accountRepository.softDeleteOrActiveById(active, accNo);
		LOGGER.debug("Activation Status : "+status);
		LOGGER.info("accountDeactivateOrActivate service end");
		return status>0;
	}
	
	/**
	 * Account Detaills as per accNo
	 */
	@Override
	public AccountModel getAccountByAccNo(Integer accNo) {
		Optional<AccountEntity> accountEntityOptional=accountRepository.findById(accNo);
		if(!accountEntityOptional.isPresent()) {
			LOGGER.info("Throwing AccountNotFoundException");
			throw new AccountNotFoundException("No account found");
		}
		AccountModel accountModel=new AccountModel();
		LOGGER.debug("AccountEntity : "+accountEntityOptional.get());
		BeanUtils.copyProperties(accountEntityOptional.get(), accountModel);
		LOGGER.info("AccountModel : "+accountModel);
		return accountModel;
	}

}
