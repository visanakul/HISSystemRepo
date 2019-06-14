package com.ssa.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.entity.SSNUserEntity;
import com.ssa.model.SSNUser;
import com.ssa.repository.SSNUserRepository;

@Service
public class SSNUserServiceImpl implements SSNUserService {

	@Autowired
	private SSNUserRepository userRepositiry;

	@Override
	public Integer registerUser(SSNUser userModel) {
		SSNUserEntity userEntity = new SSNUserEntity();
		BeanUtils.copyProperties(userModel, userEntity);
		userEntity = userRepositiry.save(userEntity);
		System.out.println("***Saved Data : " + userEntity + "***");
		return userEntity.getSsn();
	}

	@Override
	public SSNUser getUser(Integer ssn) {
		SSNUserEntity userEntity = userRepositiry.findById(ssn).orElse(null);
		SSNUser userModel = new SSNUser();
		BeanUtils.copyProperties(userEntity, userModel);
		System.out.println("***Returning Data : " + userEntity + "***");
		return userModel;
	}
}
