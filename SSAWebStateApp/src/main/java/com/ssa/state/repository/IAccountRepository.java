package com.ssa.state.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssa.state.entity.AccountEntity;

public interface IAccountRepository extends JpaRepository<AccountEntity, Serializable> {
	@Query("select accNo from AccountEntity where email=:email")
	Integer findAccNoByEmail(String email);
}
