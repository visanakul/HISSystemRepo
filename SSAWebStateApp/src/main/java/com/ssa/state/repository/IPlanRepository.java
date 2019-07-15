package com.ssa.state.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssa.state.entity.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Serializable>{
}
