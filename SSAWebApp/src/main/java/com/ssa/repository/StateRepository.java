package com.ssa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.ssa.entity.StateEntity;


public interface StateRepository extends Repository<StateEntity, Serializable> {
	List<StateEntity> findAll();
}