package com.ssa.state;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import javax.validation.ReportAsSingleViolation;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ssa.state.model.ResourceResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsaWebStateAppApplicationTests {

	@Test(timeout = 3000)
	public void test_getUserState_success() {

		String endPointUrl = "http://localhost:8081/SSAWebApp/getState/{ssn}";
		String ssn = "100000020";

		RestTemplate template = new RestTemplate();

		ResponseEntity<ResourceResponse> response = template.exchange(endPointUrl, HttpMethod.GET, null, ResourceResponse.class, ssn);

		showResponse(response);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertEquals("Arizona", response.getBody().getMsg());

	}

	@Test(timeout = 3000)
	public void test_getUserState_fail() {
		
			String endPointUrl = "http://localhost:8081/SSAWebApp/getState/{ssn}";
			String ssn = "100000002";

			RestTemplate template = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>("body",headers);
			System.out.println("***Sending request***");
			ResponseEntity<ResourceResponse> response = template.exchange(endPointUrl, HttpMethod.GET, entity,
					ResourceResponse.class, ssn);
			System.out.println("***Response***" + response);
			showResponse(response);
			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
			assertEquals("No state Found", response.getBody().getMsg());
	}



	public void showResponse(ResponseEntity<ResourceResponse> response) {
		System.out.println("Status code : " + response.getStatusCode());
		System.out.println("Status Code Value : " + response.getStatusCodeValue());
		HttpHeaders headers = response.getHeaders();
		for (Entry<String, List<String>> x : headers.entrySet()) {
			String headerKey = x.getKey();
			List<String> headerValue = x.getValue();
			System.out.println("--------------------------------");
			System.out.println("Header Key :" + headerKey);
			System.out.println("--------------------------------");
			System.out.print("Header value/values :");
			for (String value : headerValue) {
				System.out.print(value + "\t");
			}
			System.out.println();

		}
		System.out.println("Response Body : " + response.getBody());
	}
}
