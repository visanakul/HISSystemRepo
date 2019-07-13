package com.ssa.state;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.state.entity.AccountEntity;
import com.ssa.state.model.AccountModel;
import com.ssa.state.repository.IAccountRepository;
import com.ssa.state.service.IAccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * Test cases for ACCOUNT_MASTER table
 * 
 * @author VISHAL
 *
 */
public class AccountTableTest {
	/**
	 * Slf4 Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountTableTest.class);

	/**
	 * Injecting Account Repository
	 */
	@Autowired
	private IAccountRepository accountRepository;
	/**
	 * Injecting Account Service
	 */
	@Autowired
	private IAccountService accountService;

	@SuppressWarnings("deprecation")
	@Test
	@Ignore
	public void test_addAccountRepository_success() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setFname("Vishal");
		accountEntity.setLname("Kulkarni");
		accountEntity.setGender("Male");
		accountEntity.setEmail("vis@gmail.com");
		accountEntity.setPassword("vishal123");
		accountEntity.setDob(new Date(1991, 7, 12));
		accountEntity.setSsn(12345678);
		accountEntity.setMobile("9812345678");
		accountEntity.setRole("Admin");

		accountEntity = accountRepository.save(accountEntity);
		assertNotNull(accountEntity);
		assertTrue(accountEntity.getAccNo() > 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	@Ignore
	public void test_addAccountService_success() {
		LOGGER.info("test_addAccountService_success start");
		AccountModel accountModel = new AccountModel();
		accountModel.setFname("Vikram");
		accountModel.setLname("Kulkarni");
		accountModel.setGender("Male");
		accountModel.setEmail("vik@gmail.com");
		accountModel.setPassword("vikram123");
		accountModel.setDob(new Date(1987, 5, 8));
		accountModel.setSsn(12345679);
		accountModel.setMobile("9812345679");
		accountModel.setRole("Case Worker");
		LOGGER.debug("accountModel " + accountModel);
		boolean result = accountService.addAccount(accountModel);
		LOGGER.debug("Result " + result);
		assertTrue(result);
		LOGGER.info("test_addAccountService_success end");
	}

	@Test
	@Ignore
	public void test_showAllAccountsRepository_success() {
		LOGGER.info("test_showAllAccountsRepository_success end");
		List<AccountEntity> accountEntities = accountRepository.findAll();
		LOGGER.debug("All Accounts : " + accountEntities);
		assertNotNull(accountEntities);
		assertTrue(accountEntities.size() > 0);
		LOGGER.info("test_showAllAccountsRepository_success end");
	}
	@Test
	@Ignore
	public void test_showAllAccountsService_success() {
		LOGGER.info("test_showAllAccountsService_success end");
		List<AccountModel> accountModels = accountService.getAllAccounts();
		LOGGER.debug("All Accounts : " + accountModels);
		assertNotNull(accountModels);
		assertTrue(accountModels.size() > 0);
		LOGGER.info("test_showAllAccountsService_success end");
	}
	
	@Test
	public void test_checkEmailRepository_success() {
		String email="vis@gmail.com";
		Integer accNo=accountRepository.findAccNoByEmail(email);
		LOGGER.debug("Account Number : "+accNo);
		assertNotNull(accNo);
		assertTrue(accNo>0);
	}
	@Test
	public void test_checkEmailRepository_fail() {
		String email="vis123@gmail.com";
		Integer accNo=accountRepository.findAccNoByEmail(email);
		LOGGER.debug("Account Number : "+accNo);
		assertNull(accNo);
	}
	@Test
	public void test_checkEmailService_success() {
		String email="vis@gmail.com";
		boolean flag=accountService.checkEmail(email);
		LOGGER.debug("Flag : "+flag);
		assertTrue(flag);
	}
	@Test
	public void test_checkEmailService_fail() {
		String email="vis123@gmail.com";
		boolean flag=accountService.checkEmail(email);
		LOGGER.debug("Flag : "+flag);
		assertFalse(flag);
	}


}
