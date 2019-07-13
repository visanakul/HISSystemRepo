package com.ssa.state.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.state.entity.RoleEntity;
import com.ssa.state.model.RoleModel;
import com.ssa.state.repository.IRoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {

	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

	/**
	 * Default constructor
	 */
	public RoleServiceImpl() {
		LOGGER.info("***RoleServiceImpl***");
	}

	/**
	 * Autowiring RoleRepository
	 */
	@Autowired(required = true)
	private IRoleRepository roleRepository;

	@Override
	public List<RoleModel> getAllRoles() {
		LOGGER.info("GetAllRoles start");
		List<RoleEntity> roleEntities=roleRepository.findAll();
		LOGGER.debug("Role Entities : "+roleEntities);
		
		/**
		 * Convert Entity list to Model list
		 */
		
		List<RoleModel> roleModels=new ArrayList<>(roleEntities.size());
		for(RoleEntity roleEntity: roleEntities) {
			RoleModel  roleModel=new RoleModel();
			BeanUtils.copyProperties(roleEntity, roleModel);
			roleModels.add(roleModel);
		}
		LOGGER.debug("Role Models : "+roleModels);
		LOGGER.info("GetAllRoles end");
		return roleModels;
	}

}
