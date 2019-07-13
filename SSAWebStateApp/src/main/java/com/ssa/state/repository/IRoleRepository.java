package com.ssa.state.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.ssa.state.entity.RoleEntity;

public interface IRoleRepository extends Repository<RoleEntity, Serializable>{
	List<RoleEntity> findAll();
}
