package com.ssa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.entity.StateEntity;
import com.ssa.model.State;
import com.ssa.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService{

	@Autowired
	private StateRepository stateRepo;
	
	@Override
	public List<State> getAllStates() {
		List<StateEntity> stateEntities=stateRepo.findAll();
		List<State> stateModelList=new ArrayList<>();
		
		for(StateEntity stateEntity:stateEntities) {
			State stateModel=new State();
			BeanUtils.copyProperties(stateEntity, stateModel);
			stateModelList.add(stateModel);
		}
		return stateModelList;
	}

	@Override
	public State getUserState(String stateName) {
		StateEntity stateEntity=stateRepo.findByStateName(stateName);
		State stateModel=new State();
		BeanUtils.copyProperties(stateEntity, stateModel);
		return stateModel;
	}

	
}