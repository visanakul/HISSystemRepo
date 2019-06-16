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

@Service
public class StateServiceImpl implements StateService{

	@Autowired
	private StateRepository stateRepo;
	
	private static Logger logger=LoggerFactory.getLogger(SSNController.class);

	@Override
	public List<State> getAllStates() {
		List<StateEntity> stateEntities=stateRepo.findAll();
		logger.debug("Total states : "+stateEntities.size());
		List<State> stateModelList=new ArrayList<>();
		
		for(StateEntity stateEntity:stateEntities) {
			State stateModel=new State();
			BeanUtils.copyProperties(stateEntity, stateModel);
			stateModelList.add(stateModel);
		}
		logger.debug("State Entity list converted to State Model list");
		return stateModelList;
	}

	@Override
	public State getUserState(String stateName) {
		logger.debug("Got state name : "+stateName);
		StateEntity stateEntity=stateRepo.findByStateName(stateName);
		logger.debug("State entity : "+stateEntity);
		State stateModel=new State();
		BeanUtils.copyProperties(stateEntity, stateModel);
		logger.debug("Returning state model : "+stateModel);
		return stateModel;
	}

	
}