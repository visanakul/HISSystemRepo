package com.ssa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.controller.SSNController;
import com.ssa.entity.StateEntity;
import com.ssa.exception.NoStateException;
import com.ssa.exception.NoUserException;
import com.ssa.exception.StateNotForUserException;
import com.ssa.model.State;
import com.ssa.repository.StateRepository;

/**
 * State service implementation for STATE_MASTER table
 * 
 * @author VISHAL
 *
 */
@Service
public class StateServiceImpl implements StateService {

	/**
	 * State Repository to access STATE_MASTER table
	 */
	@Autowired(required = true)
	private StateRepository stateRepo;//NOPMD
	
	/**
	 * slf4j logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SSNController.class);

	/**
	 * Default constructor
	 */
	public StateServiceImpl() {
		LOGGER.info("***StateServiceImpl***");
	}

	/**
	 * Return all state list
	 */
	@Override
	public List<State> getAllStates() {
		LOGGER.info("Get All States start");
		final List<StateEntity> stateEntities = stateRepo.findAll();
		
		if(stateEntities==null || stateEntities.size()==0) {
			LOGGER.info("No State data in table");
			throw new NoStateException("No state Exists");
		}
		
		LOGGER.debug("Total states : " + stateEntities.size());//NOPMD
		final List<State> stateModelList = new ArrayList<>(stateEntities.size());

		for (final StateEntity stateEntity : stateEntities) {
			final State stateModel = new State();// NOPMD
			BeanUtils.copyProperties(stateEntity, stateModel);
			stateModelList.add(stateModel);
		}
		LOGGER.debug("State Entity list converted to State Model list");
		LOGGER.info("Get All States end");
		return stateModelList;
	}

	/**
	 * From state name get state details
	 */
	@Override
	public State getUserState(final String stateName) {
		LOGGER.info("Get User State start");
		LOGGER.debug("Got state name : " + stateName); //NOPMD
		final StateEntity stateEntity = stateRepo.findByStateName(stateName);
		if(stateEntity==null) {
			LOGGER.debug("State entity not found");
			throw new StateNotForUserException("State Data not found");
		}
		LOGGER.debug("State entity : " + stateEntity); //NOPMD
		final State stateModel = new State();
		BeanUtils.copyProperties(stateEntity, stateModel);
		LOGGER.debug("Returning state model : " + stateModel); //NOPMD
		LOGGER.info("Get User State end");
		return stateModel;
	}

}