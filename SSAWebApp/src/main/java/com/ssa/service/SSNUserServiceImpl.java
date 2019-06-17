package com.ssa.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.controller.SSNController;
import com.ssa.entity.SSNUserEntity;
import com.ssa.exception.SSNNotFoundException;
import com.ssa.exception.SSNUserNotFoundException;
import com.ssa.model.SSNUser;
import com.ssa.model.State;
import com.ssa.repository.SSNUserRepository;

@Service
public class SSNUserServiceImpl implements SSNUserService {

	@Autowired
	private SSNUserRepository userRepositiry;

	@Autowired
	private StateService stateService;

	private static Logger logger = LoggerFactory.getLogger(SSNController.class);

	public SSNUserServiceImpl() {
		logger.debug("***SSNUserServiceImpl***");
	}

	@Override
	public Integer registerUser(SSNUser userModel) {
		SSNUserEntity userEntity = new SSNUserEntity();
		BeanUtils.copyProperties(userModel, userEntity);

		// SSNUserBeanUtils.modelToEntity(userModel,userEntity);
		userEntity = userRepositiry.save(userEntity);
		logger.debug("***Saved Data SSN : " + userEntity.getSsn());
		return userEntity.getSsn();
	}

	@Override
	public State getUserState(Integer ssn) {
		logger.debug("SSN received : " + ssn);
		SSNUserEntity userEntity = userRepositiry.findById(ssn).orElse(null);
		if (userEntity == null) {
			throw new SSNUserNotFoundException("Sorry!!! No user with this SSN...");
		}
		logger.debug("Got User entity by SSN for " + userEntity.getFname());
		State stateModel = stateService.getUserState(userEntity.getState());

		logger.debug("Got State : " + stateModel);
		return stateModel;
	}

	@Override
	public List<SSNUser> getAllUsers() {

		List<SSNUserEntity> userEntities = userRepositiry.findAll();
		logger.debug("Got " + userEntities.size() + " Records");

		List<SSNUser> userModels = new ArrayList<>();
		for (SSNUserEntity userEntity : userEntities) {
			SSNUser userModel = new SSNUser();
			BeanUtils.copyProperties(userEntity, userModel);
			byte[] encodeBase64 = Base64.encodeBase64(userModel.getPhoto());
			String base64Encoded;
			try {
				base64Encoded = new String(encodeBase64, "UTF-8");
				userModel.setPhotoString(base64Encoded);
			} catch (UnsupportedEncodingException e) {
				logger.error("Exception : " + e.toString());
			}
			userModels.add(userModel);
		}
		logger.debug("byte[] data converted to base64Encoded String");
		return userModels;
	}
}
//class SSNUserBeanUtils{
//
//	public static void modelToEntity(SSNUser userModel, SSNUserEntity userEntity) {
//		userEntity.setFname(userModel.getFname());
//		userEntity.setLname(userModel.getLname());
//		userEntity.setPhone(userModel.getPhone());
//		userEntity.setGender(userModel.getGender());
//		userEntity.setCreationDate(userModel.getCreationDate());
//		userEntity.setUpdateDate(userModel.getUpdateDate());
//		userEntity.setState(userModel.getState());
//		userEntity.setDob(userModel.getDob());
//		userEntity.setPhoto(userModel.getPhoto());
//		
//	}
//
//	public static void entityToModel(SSNUserEntity userEntity, SSNUser userModel) {
//		userModel.setFname(userEntity.getFname());
//		userModel.setLname(userEntity.getLname());
//		userModel.setPhone(userEntity.getPhone());
//		userModel.setGender(userEntity.getGender());
//		userModel.setCreationDate(userEntity.getCreationDate());
//		userModel.setUpdateDate(userEntity.getUpdateDate());
//		userModel.setState(userEntity.getState());
//		userModel.setDob(userEntity.getDob());	
//		userModel.setPhoto(userEntity.getPhoto());
//	}
//	
//}
