package com.ssa.service;

import java.util.List;

import com.ssa.model.State;
/**
 * State Service for Accessing STATE_MASTER
 * @author VISHAL
 *
 */
public interface StateService {
	/**
	 * Returning list of states
	 * @return
	 */
	List<State> getAllStates();
	/**
	 * Return state data as per state name
	 * @param stateName
	 * @return
	 */
	State getUserState(String stateName);
}
