package com.ssa.state.service;

import java.util.List;

import com.ssa.state.model.COTriggerModel;

public interface ICOTriggerService {
	List<COTriggerModel> getAllPendingTriggers(Integer totalBucket,Integer bucketNumber);
	long countTotalPendingTriggers();
	int updateTriggerStatusCompleted(int trgId);
}
