package com.ssa.state.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssa.state.entity.CitizenEntity;

public interface ICitizenApplicationRepository extends JpaRepository<CitizenEntity, Serializable>{
	Integer findCaseNoByEmail(String email);
	@Query("update CitizenEntity set active=:active where appNo=:appNo")
	Integer softDeleteOrActiveById(boolean active,Integer appNo);
}
