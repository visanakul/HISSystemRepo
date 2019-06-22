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
import com.ssa.exception.NoUserException;
import com.ssa.exception.RegisterException;
import com.ssa.exception.SSNUserNotFoundException;
import com.ssa.exception.StateNotForUserException;
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

	@Autowired(required = true)
	/**
	 * User repository to access USER_MASTER table
	 */
	private SSNUserRepository userRepositiry;// NOPMD

	@Autowired(required = true)
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
		LOGGER.info("***SSNUserServiceImpl***");
	}

	/**
	 * Register user and Return generate SSN
	 */
	@Override
	public Integer registerUser(final SSNUser userModel) {
		LOGGER.info("Register User start");
		
		SSNUserEntity userEntity = new SSNUserEntity();
		BeanUtils.copyProperties(userModel, userEntity);
		
		userEntity = userRepositiry.save(userEntity);
		if(userEntity==null) {
			LOGGER.error("***Register fail***");
			throw new RegisterException("User not registered");
		}
		
		LOGGER.debug("***Register success with SSN : " + userEntity.getSsn()); //NOPMD
		LOGGER.info("Register User end");
		return userEntity.getSsn();//NOPMD
	}

	@Override
	/**
	 *  Returns State data as per SSN value using USER_MASTER and STATE_MASTER tables
	 */
	public State getUserState(final Integer ssn) {
		LOGGER.info("Get User State start");
		LOGGER.debug("SSN received : " + ssn); //NOPMD
		final SSNUserEntity userEntity = userRepositiry.findById(ssn).orElse(null);//NOPMD
		if (userEntity == null) {
			throw new SSNUserNotFoundException("Sorry!!! No user with this SSN...");
		}
		LOGGER.debug("Got User entity by SSN for " + userEntity.getFname());//NOPMD
		final State stateModel = stateService.getUserState(userEntity.getState()); //NOPMD
		
		if(stateModel==null) {
			LOGGER.warn("State data not available");
			throw new StateNotForUserException("State data not available for user");
		}
		
		LOGGER.debug("Got State : " + stateModel);//NOPMD
		LOGGER.info("Get User State end");
		return stateModel;
	}

	/**
	 * Returns list of all users from table USER_MASTER
	 */
	@Override
	public List<SSNUser> getAllUsers() { //NOPMD
		LOGGER.info("Get All User end");
		final List<SSNUserEntity> userEntities = userRepositiry.findAll();
		
		if(userEntities==null || userEntities.size()==0) {
			LOGGER.info("No user data in table");
			throw new NoUserException("No user Exists");
		}
		LOGGER.debug("Got Users " + userEntities.size() + " entities"); //NOPMD

		final List<SSNUser> userModels = new ArrayList<>(userEntities.size());
		
		for (final SSNUserEntity userEntity : userEntities) {
			final SSNUser userModel = new SSNUser();//NOPMD
			BeanUtils.copyProperties(userEntity, userModel);
			userModels.add(userModel);
		}
		LOGGER.debug("Converted user entity list into model list");
		LOGGER.info("Get All User end");
		return userModels;
	}
}