package com.ssa.state.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ssa.state.entity.AccountEntity;
import com.ssa.state.model.ForgotPassword;
import com.ssa.state.model.Login;

public interface IAccountRepository extends JpaRepository<AccountEntity, Serializable> {
	@Query("select accNo from AccountEntity where email=:email")
	Integer findAccNoByEmail(String email);
	@Modifying
	@Query("update AccountEntity set active=:active where accNo=:accNo")
	Integer softDeleteOrActiveById(boolean active,Integer accNo);
	@Query(value="select acc_no,email,active,role from ACCOUNT_MASTER where email=:email and password=:password",nativeQuery = true)
	Login findByEmailAndPassword(String email,String password);
	@Query(value="select fname,lname,active,password from ACCOUNT_MASTER where email=:email",nativeQuery = true)
	ForgotPassword findByEmail(String email);
}
