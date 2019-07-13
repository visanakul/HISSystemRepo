package com.ssa.state.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.state.entity.AccountEntity;
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
	 * Default constructor
	 */
	public AccountServiceImpl() {
		LOGGER.info("***AccountServiceImpl***");
	}

	@Override
	public boolean addAccount(AccountModel accountModel) {
		LOGGER.info("addAccount start");
		LOGGER.debug("Account model received : "+accountModel);
		
		accountModel.setActive(true);
		
		AccountEntity accountEntity=new AccountEntity();
		/**
		 * Converting AccountModel to AccountEntity
		 */
		BeanUtils.copyProperties(accountModel, accountEntity);
		/**
		 * Saving record
		 */
		accountEntity=accountRepository.save(accountEntity);
		LOGGER.debug("Account entity after save : "+accountEntity);
		LOGGER.info("addAccount end");
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
		LOGGER.info("getAllAccounts end");
		return accountModels;
	}

	/**
	 * Checks email availability from ajax call
	 */
	@Override
	public boolean checkEmail(String email) {
		Integer accNo=accountRepository.findAccNoByEmail(email);
		return accNo!=null;
	}

}
