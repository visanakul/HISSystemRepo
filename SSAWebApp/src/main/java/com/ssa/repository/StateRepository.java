package com.ssa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.ssa.entity.StateEntity;

/**
 * State Repository for STATE_MASTER table, It is made read only  by extending  Repository interface
 * @author VISHAL
 *
 */
public interface StateRepository extends Repository<StateEntity, Serializable> {
	/**
	 * getting all State list
	 */
	List<StateEntity> findAll();
	/**
	 * find State data by its name
	 * @param stateName
	 * @return
	 */
	StateEntity findByStateName(String stateName);
}