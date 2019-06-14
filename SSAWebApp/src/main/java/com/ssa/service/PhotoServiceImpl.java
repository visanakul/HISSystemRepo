package com.ssa.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.entity.PhotoEntity;
import com.ssa.model.Photo;
import com.ssa.repository.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService{

	@Autowired
	private PhotoRepository photoRepository;
	@Override
	public Integer saveUserPhoto(Photo photoModel) {
		PhotoEntity photoEntity=new PhotoEntity();
		BeanUtils.copyProperties(photoModel, photoEntity);
		photoEntity=photoRepository.save(photoEntity);
		return photoEntity.getPhotoId();
	}

	@Override
	public Photo getUserPhoto(Integer ssn) {
		PhotoEntity photoEntity=photoRepository.findById(ssn).orElse(null);
		Photo photoModel=new Photo();
		BeanUtils.copyProperties(photoEntity, photoModel);
		return photoModel;
	}

}
