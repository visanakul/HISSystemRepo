package com.ssa.state;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsaWebStateAppApplicationTests {

		@Test
		public void checkUserState() {

			String endPointUrl = "http://localhost:8081/SSNWebApp/getState/{ssn}";
			String ssn = "100000002";

			RestTemplate template = new RestTemplate();
			ResponseEntity<String> response = template.exchange(endPointUrl, HttpMethod.GET, null, String.class, ssn);

			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

			showResponse(response);
		}
		public void showResponse(ResponseEntity<String> response) {
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
