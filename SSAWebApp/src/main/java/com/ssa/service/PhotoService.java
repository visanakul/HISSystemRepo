package com.ssa.service;

import com.ssa.model.Photo;

public interface PhotoService {
	Integer saveUserPhoto(Photo photoModel);

	Photo getUserPhoto(Integer ssn);
}
