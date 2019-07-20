package com.ssa.state;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import com.ssa.state.config.ApplicationConfig;
import com.ssa.state.model.AccountModel;
import com.ssa.state.service.ISendMailService;
import com.ssa.state.util.StringTemplateUtils;

/**
 * Test cases for Send mail operations
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSendTest {
	/**
	 * Slf4 Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSendTest.class);

	/**
	 * Injecting application data from yml file
	 */
	@Autowired
	private ApplicationConfig appConfig;
	/**
	 * Injecting JavaMailSender to send mail
	 */
	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * Injecting Send mail service to send mail
	 */
	@Autowired
	private ISendMailService sendMailService;

	@Test
	@Ignore
	public void test_ReadAppConfig() {
		LOGGER.info("appConfig : " + appConfig);
		assertNotNull(appConfig);
	}

	@Test
	@Ignore
	public void test_ReadAndPrepareTemplateFile() {
		try {
			String path = appConfig.getProperties().get("mailFilePath");
			LOGGER.debug("Path : " + path);
			assertNotNull(path);

			File file = ResourceUtils.getFile("classpath:static/" + path);
			LOGGER.debug("File : " + file);
			assertNotNull(file);

			String content = new String(Files.readAllBytes(file.toPath()));
			LOGGER.debug("File content : " + content);
			assertTrue(content.length() > 0);

			Map<String, String> valuesMap = new HashMap<>();
			valuesMap.put("FNAME", "Vishal");
			valuesMap.put("LNAME", "Kulkarni");
			valuesMap.put("URL", "https://www.google.com");
			valuesMap.put("EMAIL", "abc@gmail.com");
			valuesMap.put("PWD", "abc123");
			valuesMap.put("ROLE", "Case Worker");
			valuesMap.put("PHNO", "9812345678");
			String result = StringTemplateUtils.prepareDocFromTemplate(content, valuesMap);
			LOGGER.debug("Replaced content : \n" + result);
			assertFalse(content.equals(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	@Ignore
	public void test_sendMail() {

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("visanakul@gmail.com"));
			message.setSubject("HIS system Account");

			String path = appConfig.getProperties().get("mailFilePath");
			LOGGER.debug("Path : " + path);
			assertNotNull(path);

			File file = ResourceUtils.getFile("classpath:static/" + path);
			LOGGER.debug("File : " + file);
			assertNotNull(file);

			String content = new String(Files.readAllBytes(file.toPath()));
			LOGGER.debug("File content : " + content);
			assertTrue(content.length() > 0);

			Map<String, String> valuesMap = new HashMap<>();
			valuesMap.put("FNAME", "Vishal");
			valuesMap.put("LNAME", "Kulkarni");
			valuesMap.put("URL", "https://www.google.com");
			valuesMap.put("EMAIL", "abc@gmail.com");
			valuesMap.put("PWD", "abc123");
			valuesMap.put("ROLE", "Case Worker");
			valuesMap.put("PHNO", appConfig.getProperties().get("phoneno"));
			String result = StringTemplateUtils.prepareDocFromTemplate(content, valuesMap);
			LOGGER.debug("Replaced content : \n" + result);
			assertFalse(content.equals(result));

			message.setContent(result, "text/html");
			javaMailSender.send(message);
			LOGGER.debug("Mail sent...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	@Ignore
	public void test_sendMailService() {
		AccountModel accountModel = new AccountModel();
		accountModel.setAccNo(1);
		accountModel.setFname("Abc");
		accountModel.setLname("Xyz");
		accountModel.setPassword("abc123");
		accountModel.setEmail("visanakul@gmail.com");
		accountModel.setDob(new Date());
		accountModel.setMobile("9812345678");
		accountModel.setGender("Male");
		accountModel.setSsn(123456778);
		accountModel.setRole("Admin");
		boolean flag = sendMailService.sendMail(accountModel);
		assertTrue(flag);
	}

	/**
	 * Injecting BCryptPasswordEncoder
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	public void encryptPassword() {
		String rawPassword = "vishal123";
		String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
		LOGGER.debug("Raw password : "+rawPassword+" Encoded Password : "+encodedPassword);
		assertFalse(rawPassword.equals(encodedPassword));
		assertTrue(bCryptPasswordEncoder.matches(rawPassword, encodedPassword));
	}

}
