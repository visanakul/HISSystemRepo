package com.ssa.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssa.entity.SSNUserEntity;
import com.ssa.model.SSNUser;
import com.ssa.model.State;
import com.ssa.repository.SSNUserRepository;

@Service
public class SSNUserServiceImpl implements SSNUserService {

	@Autowired
	private SSNUserRepository userRepositiry;
	
	@Autowired
	private StateService stateService;

	@Override
	public Integer registerUser(SSNUser userModel) {
		SSNUserEntity userEntity = new SSNUserEntity();
		BeanUtils.copyProperties(userModel, userEntity);
	
		//SSNUserBeanUtils.modelToEntity(userModel,userEntity);
		userEntity = userRepositiry.save(userEntity);
		//System.out.println("***Saved Data : " + userEntity + "***");
		return userEntity.getSsn();
	}

	@Override
	public State getUserState(Integer ssn) {
		SSNUserEntity userEntity=userRepositiry.findById(ssn).orElse(null);
		State stateModel=stateService.getUserState(userEntity.getState());
		return stateModel;
	}

	@Override
	public List<SSNUser> getAllUsers() {
		List<SSNUserEntity> userEntities=userRepositiry.findAll();
		System.out.println("Got "+userEntities.size()+" Records");
		List<SSNUser> userModels=new ArrayList<>();
		for(SSNUserEntity userEntity:userEntities) {
			SSNUser userModel=new SSNUser();
			BeanUtils.copyProperties(userEntity, userModel);
			byte[] encodeBase64 = Base64.encodeBase64(userModel.getPhoto());
	            String base64Encoded;
				try {
					base64Encoded = new String(encodeBase64, "UTF-8");
					 userModel.setPhotoFile(base64Encoded);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			userModels.add(userModel);
		}
		return userModels;
	}
}
class SSNUserBeanUtils{

	public static void modelToEntity(SSNUser userModel, SSNUserEntity userEntity) {
		userEntity.setFname(userModel.getFname());
		userEntity.setLname(userModel.getLname());
		userEntity.setPhone(userModel.getPhone());
		userEntity.setGender(userModel.getGender());
		userEntity.setCreationDate(userModel.getCreationDate());
		userEntity.setUpdateDate(userModel.getUpdateDate());
		userEntity.setState(userModel.getState());
		userEntity.setDob(userModel.getDob());
		userEntity.setPhoto(userModel.getPhoto());
		
	}

	public static void entityToModel(SSNUserEntity userEntity, SSNUser userModel) {
		userModel.setFname(userEntity.getFname());
		userModel.setLname(userEntity.getLname());
		userModel.setPhone(userEntity.getPhone());
		userModel.setGender(userEntity.getGender());
		userModel.setCreationDate(userEntity.getCreationDate());
		userModel.setUpdateDate(userEntity.getUpdateDate());
		userModel.setState(userEntity.getState());
		userModel.setDob(userEntity.getDob());	
		userModel.setPhoto(userEntity.getPhoto());
	}
	
}
