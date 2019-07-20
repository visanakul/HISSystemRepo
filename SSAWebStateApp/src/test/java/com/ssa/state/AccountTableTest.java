package com.ssa.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.state.entity.AccountEntity;
import com.ssa.state.model.AccountModel;
import com.ssa.state.model.ForgotPassword;
import com.ssa.state.model.Login;
import com.ssa.state.repository.IAccountRepository;
import com.ssa.state.service.IAccountService;
import com.ssa.state.util.PasswordUtils;

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
		LOGGER.info("test_addAccountRepository_success start");
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
		LOGGER.info("test_addAccountRepository_success end");
	}

	@SuppressWarnings("deprecation")
	@Test
	@Ignore
	public void test_updateAccountRepository_success() {
		LOGGER.info("test_updateAccountRepository_success start");
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setAccNo(2);
		accountEntity.setFname("Vishal");
		accountEntity.setLname("Kulkarni");
		accountEntity.setGender("Male");
		accountEntity.setEmail("vis1@gmail.com");
		accountEntity.setPassword("vishal1234");
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 6, 20);
		accountEntity.setDob(calendar.getTime());
		accountEntity.setSsn(123456789);
		accountEntity.setMobile("9812345679");
		accountEntity.setRole("Admin");

		accountEntity = accountRepository.save(accountEntity);
		assertNotNull(accountEntity);
		assertTrue(accountEntity.getAccNo() > 0);
		LOGGER.info("test_updateAccountRepository_success end");
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
		boolean result = accountService.addOrUpdateAccount(accountModel);
		LOGGER.debug("Result " + result);
		assertTrue(result);
		LOGGER.info("test_addAccountService_success end");
	}

	@SuppressWarnings("deprecation")
	@Test
	@Ignore
	public void test_updateAccountService_success() {
		LOGGER.info("test_updateAccountService_success start");
		AccountModel accountModel = new AccountModel();
		accountModel.setAccNo(3);
		accountModel.setFname("VikramNew");
		accountModel.setLname("Kulkarni");
		accountModel.setGender("Male");
		accountModel.setEmail("vik1@gmail.com");
		accountModel.setPassword("vikram1234");
		Calendar calendar = Calendar.getInstance();
		calendar.set(1989, 0, 20, 0, 0);
		accountModel.setDob(calendar.getTime());
		accountModel.setSsn(82345679);
		accountModel.setMobile("9812345671");
		accountModel.setRole("Case Worker");
		LOGGER.debug("accountModel " + accountModel);
		boolean result = accountService.addOrUpdateAccount(accountModel);
		LOGGER.debug("Result " + result);
		assertTrue(result);
		LOGGER.info("test_updateAccountService_success end");
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
	@Ignore
	public void test_checkEmailRepository_success() {
		String email = "vis@gmail.com";
		Integer accNo = accountRepository.findAccNoByEmail(email);
		LOGGER.debug("Account Number : " + accNo);
		assertNotNull(accNo);
		assertTrue(accNo > 0);
	}

	@Test
	@Ignore
	public void test_checkEmailRepository_fail() {
		String email = "vis123@gmail.com";
		Integer accNo = accountRepository.findAccNoByEmail(email);
		LOGGER.debug("Account Number : " + accNo);
		assertNull(accNo);
	}

	@Test
	@Ignore
	public void test_checkEmailService_success() {
		String email = "vis@gmail.com";
		boolean flag = accountService.checkEmail(email);
		LOGGER.debug("Flag : " + flag);
		assertTrue(flag);
	}

	@Test
	@Ignore
	public void test_checkEmailService_fail() {
		String email = "vis123@gmail.com";
		boolean flag = accountService.checkEmail(email);
		LOGGER.debug("Flag : " + flag);
		assertFalse(flag);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	@Ignore
	public void test_softDeleteAccountRespository() {
		Integer accNo = 10;
		Integer softDelete = accountRepository.softDeleteOrActiveById(false, accNo);
		LOGGER.debug("Soft Delete : " + softDelete);
		assertNotNull(softDelete);
		assertEquals(1, softDelete.intValue());
	}

	@Test
	@Transactional
	@Rollback(value = false)
	@Ignore
	public void test_softActiveAccountRespository() {
		Integer accNo = 10;
		Integer softActive = accountRepository.softDeleteOrActiveById(true, accNo);
		LOGGER.debug("Soft Active : " + softActive);
		assertNotNull(softActive);
		assertEquals(1, softActive.intValue());
	}

	@Test
	@Transactional
	@Rollback(value = false)
	@Ignore
	public void test_softDeleteAccountService() {
		Integer accNo = 10;
		boolean status = accountService.accountDeactivateOrActivate(false, accNo);
		LOGGER.debug("Soft Delete : " + status);
		assertTrue(status);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	@Ignore
	public void test_softActiveAccountService() {
		Integer accNo = 10;
		boolean status = accountService.accountDeactivateOrActivate(true, accNo);
		LOGGER.debug("Soft Active : " + status);
		assertTrue(status);
	}

	@Test
	@Ignore
	public void test_getAccountRepository_success() {
		Integer accNo = 6;
		Optional<AccountEntity> optional = accountRepository.findById(accNo);
		assertTrue(optional.isPresent());
		LOGGER.debug("Account Entity : " + optional.get());
	}

	@Test(expected = NoSuchElementException.class)
	@Ignore
	public void test_getAccountRepository_fail() {
		Integer accNo = 26;
		Optional<AccountEntity> optional = accountRepository.findById(accNo);
		assertFalse(optional.isPresent());
		LOGGER.debug("Account Entity : " + optional.get());
	}

	@Test
	@Ignore
	public void testLoginRepository_success() {
		LOGGER.info("testLoginRepository_success start");
		String email = "pramila@gmail.com", password = "prami123";
		Login login = accountRepository.findByEmailAndPassword(email,
				PasswordUtils.encryptPassword(password));
		LOGGER.debug("Login data : " + login.getEmail()+","+login.getActive()+","+login.getRole());
		assertNotNull(login);
		assertEquals(1, login.getActive().intValue());
		assertEquals("Admin", login.getRole());
		LOGGER.info("testLoginRepository_success end");
	}

	@Test
	@Ignore
	public void testLoginService_success() {
		LOGGER.info("testLoginService_success start");
		String email = "pramila@gmail.com", password = "prami123";
		Login login = accountService.doesUserExist(email, password);
		LOGGER.debug("Login Data :" + login);
		assertNotNull(login);
		assertEquals(1, login.getActive().intValue());
		assertEquals("Admin", login.getRole());
		LOGGER.info("testLoginService_success end");
	}

	@Test
	@Ignore
	public void testLoginRepository_fail() {
		LOGGER.info("testLoginRepository_fail start");
		String email = "pramila@gmail.com", password = "prami";
		Login login= accountRepository.findByEmailAndPassword(email,
				PasswordUtils.encryptPassword(password));
		assertNull(login);
		LOGGER.info("testLoginRepository_fail end");
	}

	@Test
	@Ignore
	public void testLoginService_fail() {
		LOGGER.info("testLoginService_fail start");
		String email = "pramila@gmail.com", password = "prami";
		Login login = accountService.doesUserExist(email, password);
		assertNull(login);
		LOGGER.info("testLoginService_fail end");
	}

	@Test
	@Ignore
	public void testFindByEmailRepository_success() {
		LOGGER.info("testFindByEmailRepository_success start");
		String email = "pramila@gmail.com";
		ForgotPassword forgotPassword= accountRepository.findByEmail(email);
		LOGGER.debug("Forgot Data :" + forgotPassword.getFname()+","+forgotPassword.getLname()+","+forgotPassword.getPassword()+","+forgotPassword.getActive());
		assertNotNull(forgotPassword);
		assertEquals(1,forgotPassword.getActive());
		LOGGER.info("testFindByEmailRepository_success end");
	}

	@Test
	@Ignore
	public void testFindByEmailRepository_fail() {
		LOGGER.info("testFindByEmailRepository_fail start");
		String email = "pramila123@gmail.com";
		ForgotPassword forgotPassword= accountRepository.findByEmail(email);
		assertNull(forgotPassword);
		LOGGER.info("testFindByEmailRepository_fail end");
	}
	
	@Test
	@Ignore
	public void testFindByEmailService_success() {
		LOGGER.info("testFindByEmailService_success start");
		String email = "pramila@gmail.com";
		ForgotPassword forgotPassword= accountService.getDataByEmail(email);
		LOGGER.debug("Forgot Data :" + forgotPassword.getFname()+","+forgotPassword.getLname()+","+forgotPassword.getPassword()+","+forgotPassword.getActive());
		assertNotNull(forgotPassword);
		assertEquals(1,forgotPassword.getActive());
		LOGGER.info("testFindByEmailService_success end");
	}

	@Test
	@Ignore
	public void testFindByEmailService_fail() {
		LOGGER.info("testFindByEmailService_fail start");
		String email = "pramila123@gmail.com";
		ForgotPassword forgotPassword= accountService.getDataByEmail(email);
		assertNull(forgotPassword);
		LOGGER.info("testFindByEmailService_fail end");
	}
}
