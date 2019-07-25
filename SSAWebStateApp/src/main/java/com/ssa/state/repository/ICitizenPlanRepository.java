package com.ssa.state.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ssa.state.entity.CitizenPlanEntity;

public interface ICitizenPlanRepository extends JpaRepository<CitizenPlanEntity, Serializable> {
}
