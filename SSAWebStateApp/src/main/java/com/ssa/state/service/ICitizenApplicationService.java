package com.ssa.state.service;

import java.util.List;

import com.ssa.state.model.CitizenModel;

public interface ICitizenApplicationService {
	Integer addOrUpdateApplication(CitizenModel citizenModel);
	List<CitizenModel> getAllApplications();
	boolean checkEmail(String email);
	boolean applicationDeactivateOrActivate(boolean active,Integer appNo);
	String getCityName(Integer ssn);
	CitizenModel getApplicationByAppNo(Integer appNo);
}
