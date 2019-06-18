package com.ssa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.controller.SSNController;
import com.ssa.entity.SSNUserEntity;
import com.ssa.exception.SSNUserNotFoundException;
import com.ssa.model.SSNUser;
import com.ssa.model.State;
import com.ssa.repository.SSNUserRepository;

@Service
/**
 * User service implementation
 * 
 * @author VISHAL
 *
 */
public class SSNUserServiceImpl implements SSNUserService {

	@Autowired
	/**
	 * User repository to access USER_MASTER table
	 */
	private SSNUserRepository userRepositiry;// NOPMD

	@Autowired
	/**
	 * State Service to Access STATE_MASTER table
	 */
	private StateService stateService;// NOPMD
	/**
	 * Logging slf4j
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SSNController.class);

	/**
	 * Default constructor
	 */
	public SSNUserServiceImpl() {
		LOGGER.debug("***SSNUserServiceImpl***");
	}

	/**
	 * Register user and Return generate SSN
	 */
	@Override
	public Integer registerUser(final SSNUser userModel) {
		SSNUserEntity userEntity = new SSNUserEntity();
		BeanUtils.copyProperties(userModel, userEntity);

		// SSNUserBeanUtils.modelToEntity(userModel,userEntity);
		userEntity = userRepositiry.save(userEntity);
		LOGGER.debug("***Saved Data SSN : " + userEntity.getSsn()); //NOPMD
		return userEntity.getSsn();//NOPMD
	}

	@Override
	/**
	 *  Returns State data as per SSN value using USER_MASTER and STATE_MASTER tables
	 */
	public State getUserState(final Integer ssn) {
		LOGGER.debug("SSN received : " + ssn); //NOPMD
		final SSNUserEntity userEntity = userRepositiry.findById(ssn).orElse(null);//NOPMD
		if (userEntity == null) {
			throw new SSNUserNotFoundException("Sorry!!! No user with this SSN...");
		}
		LOGGER.debug("Got User entity by SSN for " + userEntity.getFname());//NOPMD
		final State stateModel = stateService.getUserState(userEntity.getState()); //NOPMD

		LOGGER.debug("Got State : " + stateModel);//NOPMD
		return stateModel;
	}

	/**
	 * Returns list of all users from table USER_MASTER
	 */
	@Override
	public List<SSNUser> getAllUsers() { //NOPMD

		final List<SSNUserEntity> userEntities = userRepositiry.findAll();
		LOGGER.debug("Got " + userEntities.size() + " Records"); //NOPMD

		final List<SSNUser> userModels = new ArrayList<>();
		for (final SSNUserEntity userEntity : userEntities) {
			final SSNUser userModel = new SSNUser();//NOPMD
			BeanUtils.copyProperties(userEntity, userModel);
//			byte[] encodeBase64 = Base64.encodeBase64(userModel.getPhoto());
//			String base64Encoded;
//			try {
//				base64Encoded = new String(encodeBase64, "UTF-8");
//				userModel.setPhotoString(base64Encoded);
//			} catch (UnsupportedEncodingException e) {
//				logger.error("Exception : " + e.toString());
//			}
			userModels.add(userModel);
		}
		LOGGER.debug("Returning user model list");
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
