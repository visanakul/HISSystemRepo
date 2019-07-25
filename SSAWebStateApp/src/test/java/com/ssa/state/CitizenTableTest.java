package com.ssa.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.state.entity.CitizenEntity;
import com.ssa.state.model.CitizenModel;
import com.ssa.state.repository.ICitizenApplicationRepository;
import com.ssa.state.service.ICitizenApplicationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CitizenTableTest {
	
	@Autowired
	private ICitizenApplicationRepository  citizenApplicationRepository;
	@Autowired
	private ICitizenApplicationService citizenApplicationService;
	@Test
	@Ignore
	public void test_saveCitizenRepository_success() {
		CitizenEntity citizenEntity=new CitizenEntity();
		citizenEntity.setActive(true);
		Calendar calendar=Calendar.getInstance();
		calendar.set(1996, 3, 12);
		citizenEntity.setDob(calendar.getTime());
		citizenEntity.setEmail("abc@gmail.com");;
		citizenEntity.setFname("abcd");
		citizenEntity.setGender("Male");
		citizenEntity.setLname("Xyzw");
		citizenEntity.setMobile("9812345678");
		citizenEntity.setSsn(100000026);
		citizenEntity=citizenApplicationRepository.save(citizenEntity);
		assertNotNull(citizenEntity);
		assertTrue(citizenEntity.getAppNo()>0);
	}
	@Test
	@Ignore
	public void test_getCitizenByAppNoRepository_success() {
		Integer appNo=1;
		Optional<CitizenEntity> citizenEntityOptional=citizenApplicationRepository.findById(appNo);
		assertTrue(citizenEntityOptional.isPresent());
		assertEquals("abc@gmail.com", citizenEntityOptional.get().getEmail());
	}
	@Test
	@Ignore
	public void test_getCitizenByAppNoRepository_fail() {
		Integer appNo=10;
		Optional<CitizenEntity> citizenEntityOptional=citizenApplicationRepository.findById(appNo);
		assertFalse(citizenEntityOptional.isPresent());
	}
	@Test
	public void test_getCitizenByAppNoService_success() {
		Integer appNo=1;
		CitizenModel citizenModel=citizenApplicationService.getApplicationByAppNo(appNo);
		assertNotNull(citizenModel);
		assertEquals("abc@gmail.com", citizenModel.getEmail());
	}
	@Test
	public void test_getCitizenByAppNoService_fail() {
		Integer appNo=10;
		CitizenModel citizenModel=citizenApplicationService.getApplicationByAppNo(appNo);
		assertNull(citizenModel);
	}
}
