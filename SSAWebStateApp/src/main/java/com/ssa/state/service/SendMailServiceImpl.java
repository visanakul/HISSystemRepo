package com.ssa.state.service;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ssa.state.config.ApplicationConfig;
import com.ssa.state.model.AccountModel;
import com.ssa.state.util.StringTemplateUtils;

@Service
public class SendMailServiceImpl implements ISendMailService {
	/**
	 * Slf4 Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SendMailServiceImpl.class);

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
	 * Injecting BCryptPasswordEncoder for password encoding 
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public SendMailServiceImpl() {
		LOGGER.info("***SendMailServiceImpl***");
	}

	@Override
	public boolean sendMail(AccountModel accountModel) {
		LOGGER.info("sendMail service start");
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(accountModel.getEmail()));
			message.setSubject("HIS system Account");

			String path = appConfig.getProperties().get("mailFilePath");
			LOGGER.debug("Path : " + path);

			File file = ResourceUtils.getFile("classpath:static/" + path);
			LOGGER.debug("File : " + file);

			String content = new String(Files.readAllBytes(file.toPath()));
			LOGGER.debug("File content : " + content);
			Map<String, String> valuesMap = new HashMap<>();
			valuesMap.put("FNAME", accountModel.getFname());
			valuesMap.put("LNAME",accountModel.getLname());
			valuesMap.put("URL", appConfig.getProperties().get("baseUrl"));
			valuesMap.put("EMAIL", accountModel.getEmail());
			valuesMap.put("PWD", accountModel.getPassword());
			valuesMap.put("ROLE", accountModel.getRole());
			valuesMap.put("PHNO", appConfig.getProperties().get("phoneno"));
			String result = StringTemplateUtils.prepareDocFromTemplate(content, valuesMap);
			LOGGER.debug("Replaced content : \n" + result);

			message.setContent(result, "text/html");
			javaMailSender.send(message);
			LOGGER.debug("Mail sent...");
			LOGGER.info("sendMail service end success");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("sendMail service end exception "+e.getMessage());
			return false;
		}

	}

}
