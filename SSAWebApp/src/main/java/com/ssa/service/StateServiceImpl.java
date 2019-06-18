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
	@Autowired
	private StateRepository stateRepo;//NOPMD
	
	/**
	 * slf4j logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SSNController.class);

	/**
	 * Default constructor
	 */
	public StateServiceImpl() {
		LOGGER.debug("***StateServiceImpl***");
	}

	/**
	 * Return all state list
	 */
	@Override
	public List<State> getAllStates() {
		final List<StateEntity> stateEntities = stateRepo.findAll();
		LOGGER.debug("Total states : " + stateEntities.size());//NOPMD
		final List<State> stateModelList = new ArrayList<>();

		for (final StateEntity stateEntity : stateEntities) {
			final State stateModel = new State();// NOPMD
			BeanUtils.copyProperties(stateEntity, stateModel);
			stateModelList.add(stateModel);
		}
		LOGGER.debug("State Entity list converted to State Model list");
		return stateModelList;
	}

	@Override
	public State getUserState(final String stateName) {
		LOGGER.debug("Got state name : " + stateName); //NOPMD
		final StateEntity stateEntity = stateRepo.findByStateName(stateName);
		LOGGER.debug("State entity : " + stateEntity); //NOPMD
		final State stateModel = new State();
		BeanUtils.copyProperties(stateEntity, stateModel);
		LOGGER.debug("Returning state model : " + stateModel); //NOPMD
		return stateModel;
	}

}