package com.ssa.state.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ssa.state.entity.COTriggerEntity;

public interface ICOTriggerRepository extends JpaRepository<COTriggerEntity, Serializable> {
	@Query(value = "select * from co_triggers where trg_status='P' and ora_hash(trg_id,:totalBucket)=:bucketNumber", nativeQuery = true)
	List<COTriggerEntity> findAllPendingTriggers(Integer totalBucket, Integer bucketNumber);

	@Modifying
	@Query("update COTriggerEntity set trgStatus='C' where trgId=:trgId")
	int setTriggerStatusCompleted(Integer trgId);
}
