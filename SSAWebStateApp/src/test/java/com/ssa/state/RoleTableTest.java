package com.ssa.state;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.state.entity.RoleEntity;
import com.ssa.state.model.RoleModel;
import com.ssa.state.repository.IRoleRepository;
import com.ssa.state.service.IRoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * Test cases for ROLE_MASTER Table
 * @author VISHAL
 *
 */
public class RoleTableTest {
	/**
	 * Slf4 Logger
	 */
	private static final Logger LOGGER=LoggerFactory.getLogger(RoleTableTest.class);
	
	/**
	 * Injecting RoleService
	 */
	@Autowired
	private IRoleService roleService;
	
	/**
	 * Injecting RoleRepository
	 */
	@Autowired
	private IRoleRepository roleRepository;
	
	@Test
	public void test_findAllRolesRepository_success() {
		LOGGER.info("test_findAllRolesRepository_success start");
		List<RoleEntity> roleEntities=roleRepository.findAll();
		assertEquals(2, roleEntities.size());
		assertEquals("Admin", roleEntities.get(0).getRoleName());
		assertEquals("Case Worker", roleEntities.get(1).getRoleName());
		LOGGER.info("test_findAllRolesRepository_success start");
	}
	
	@Test
	public void test_getAllRolesService_success() {
		LOGGER.info("test_getAllRolesService_success start");
		List<RoleModel> roleModels=roleService.getAllRoles();
		assertEquals(2, roleModels.size());
		assertEquals("Admin", roleModels.get(0).getRoleName());
		assertEquals("Case Worker", roleModels.get(1).getRoleName());
		LOGGER.info("test_getAllRolesService_success start");
	}
}
