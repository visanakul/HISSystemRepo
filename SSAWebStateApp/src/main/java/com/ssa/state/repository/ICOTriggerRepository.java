package com.ssa.state.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssa.state.entity.COTriggerEntity;

public interface ICOTriggerRepository extends JpaRepository<COTriggerEntity, Serializable>{
	@Query(value="select * from co_triggers where ora_hash(trg_id,:totalBucket)=:bucketNumber",nativeQuery = true)
	List<COTriggerEntity> findAll(Integer totalBucket,Integer bucketNumber);
}
