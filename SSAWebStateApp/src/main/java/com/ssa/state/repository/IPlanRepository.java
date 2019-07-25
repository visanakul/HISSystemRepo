package com.ssa.state.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ssa.state.entity.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Serializable>{
	@Modifying
	@Query("update PlanEntity set active=:active where id=:id")
	Integer softDeleteOrActiveById(boolean active,Integer id);
	
	@Query("select name from PlanEntity where active=true")
	List<String> findActivePlans();

	@Query("Select id from PlanEntity where name=:planName")
	Integer findIdByName(String planName);
}
