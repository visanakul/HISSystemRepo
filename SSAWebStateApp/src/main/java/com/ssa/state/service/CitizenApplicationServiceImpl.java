package com.ssa.state.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssa.state.entity.CitizenEntity;
import com.ssa.state.exception.EmptyApplicationException;
import com.ssa.state.model.CitizenModel;
import com.ssa.state.model.ResourceResponse;
import com.ssa.state.repository.ICitizenApplicationRepository;
import static com.ssa.state.util.ConstantUtils.ApplicationRegistration.REST_API_SSN_SERVICE_URL;

@Service
public class CitizenApplicationServiceImpl implements ICitizenApplicationService {

	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CitizenApplicationServiceImpl.class);

	/**
	 * Injecting CitizenApplicationRepository
	 */
	@Autowired
	private ICitizenApplicationRepository citizenApplicationRepository;

	/**
	 * Default constructor
	 */
	public CitizenApplicationServiceImpl() {
		LOGGER.info("***CitizenApplicationServiceImpl***");
	}

	@Override
	public Integer addOrUpdateApplication(CitizenModel citizenModel) {
		LOGGER.info("addOrUpdateApplication start");
		try {
			LOGGER.debug("Account model received : " + citizenModel);

			citizenModel.setActive(true);

			CitizenEntity citizenEntity = new CitizenEntity();
			/**
			 * Converting CitizenModel to CitizenEntity
			 */
			BeanUtils.copyProperties(citizenModel, citizenEntity);
			LOGGER.debug("Citizen Entity : " + citizenEntity);
			/**
			 * Saving record
			 */
			citizenEntity = citizenApplicationRepository.save(citizenEntity);
			LOGGER.debug("Application entity after save or update : " + citizenEntity);

			return citizenEntity.getAppNo();
		} catch (Exception exception) {
			LOGGER.error("addOrUpdateApplication error : " + exception.getMessage());
			exception.printStackTrace();
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("addOrUpdateApplication service end");
		}
	}

	@Override
	public List<CitizenModel> getAllApplications() {
		LOGGER.info("getAllApplications start");
		List<CitizenEntity> citizenEntities = citizenApplicationRepository.findAll();
		if (citizenEntities == null || citizenEntities.isEmpty()) {
			return null;
		}
		LOGGER.debug("Citizen Entities : " + citizenEntities);
		/**
		 * Converting entities list into models
		 */
		List<CitizenModel> citizenModels = new ArrayList<>(citizenEntities.size());

		for (CitizenEntity citizenEntity : citizenEntities) {
			CitizenModel citizenModel = new CitizenModel();
			BeanUtils.copyProperties(citizenEntity, citizenModel);
			citizenModels.add(citizenModel);
		}
		LOGGER.debug("Citizen Models : " + citizenModels);
		LOGGER.info("getAllApplications service end");
		return citizenModels;
	}

	/**
	 * Checks email availability from ajax call
	 */
	@Override
	public boolean checkEmail(String email) {
		try {
			LOGGER.info("checkEmail service start");
			Integer appNo = citizenApplicationRepository.findCaseNoByEmail(email);
			LOGGER.info("checkEmail service end");
			return appNo != null;
		} catch (Exception exception) {
			LOGGER.error("checkEmail error : " + exception.getMessage());
			exception.printStackTrace();
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("checkEmail service end");
		}
	}

	/**
	 * Application soft delete and active
	 */
	@Override
	public boolean applicationDeactivateOrActivate(boolean active, Integer caseNo) {
		try {
			LOGGER.info("applicationDeactivateOrActivate service start");
			Integer status = citizenApplicationRepository.softDeleteOrActiveById(active, caseNo);
			LOGGER.debug("Activation Status : " + status);
			return status > 0;
		} catch (Exception exception) {
			LOGGER.error("applicationDeactivateOrActivate error : " + exception.getMessage());
			exception.printStackTrace();
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("applicationDeactivateOrActivate service end");
		}
	}

	/**
	 * Rest API to check city name from SSN
	 */
	@Override
	public String getCityName(Integer ssn) {
		String endPointUrl = REST_API_SSN_SERVICE_URL;
		RestTemplate template = new RestTemplate();

		ResponseEntity<ResourceResponse> response = template.exchange(endPointUrl, HttpMethod.GET, null,
				ResourceResponse.class, ssn);

		showResponse(response);
		if (response.getStatusCode() == HttpStatus.OK) {
			ResourceResponse resourceResponse = response.getBody();
			LOGGER.debug("Response Body : " + response.getBody());
			return resourceResponse.getMsg();
		}
		return "Error fetching state info";
	}

	public void showResponse(ResponseEntity<ResourceResponse> response) {
		System.out.println("Status code : " + response.getStatusCode());
		System.out.println("Status Code Value : " + response.getStatusCodeValue());
		HttpHeaders headers = response.getHeaders();
		for (Entry<String, List<String>> x : headers.entrySet()) {
			String headerKey = x.getKey();
			List<String> headerValue = x.getValue();
			LOGGER.debug("--------------------------------");
			LOGGER.debug("Header Key :" + headerKey);
			LOGGER.debug("--------------------------------");
			LOGGER.debug("Header value/values :");
			for (String value : headerValue) {
				LOGGER.debug(value + "\t");
			}
		}
	}

	
	@Override
	public CitizenModel getApplicationByAppNo(Integer appNo) {
		LOGGER.info("getApplicationByAppNo serice start");
		try {
			Optional<CitizenEntity> optional = citizenApplicationRepository.findById(appNo);
			if (!optional.isPresent()) {
				LOGGER.warn("No record found");
				return null;
			}
			CitizenEntity citizenEntity = optional.get();
			LOGGER.debug("CitizenEntity : " + citizenEntity);
			CitizenModel citizenModel = new CitizenModel();
			BeanUtils.copyProperties(citizenEntity, citizenModel);
			LOGGER.debug("CitizenModel : " + citizenModel);
			return citizenModel;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("getApplicationByAppNo serice end");
		}
	}
}
