package com.ssa.state.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ssa.state.entity.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Serializable>{
	@Modifying
	@Query("update PlanEntity set active=:active where id=:id")
	Integer softDeleteOrActiveById(boolean active,Integer id);
}
