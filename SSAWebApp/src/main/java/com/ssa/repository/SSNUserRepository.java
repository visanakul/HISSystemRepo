package com.ssa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssa.entity.SSNUserEntity;

public interface SSNUserRepository extends JpaRepository<SSNUserEntity, Serializable> {
	

}
