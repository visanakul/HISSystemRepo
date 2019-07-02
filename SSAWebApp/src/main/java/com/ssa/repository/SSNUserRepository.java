package com.ssa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssa.entity.SSNUserEntity;
/**
 * User Repository for USER_MASTER table
 * @author VISHAL
 *
 */
public interface SSNUserRepository extends JpaRepository<SSNUserEntity, Serializable> {
	@Query("select state from SSNUserEntity where ssn=:ssn")
	String findStateById(Integer ssn);
}
