package com.ssa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssa.entity.PhotoEntity;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Serializable>{

}
