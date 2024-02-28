package com.mfp.api;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfp.api.dao.UserDao;
import com.mfp.api.entity.User;
import com.mfp.api.service.UserService;
import com.mfp.api.utility.TestDataUtility;

@SpringBootTest
class MfpApplicationTests {

	@SuppressWarnings("unused")
	@Autowired
	private UserService service;

	@MockBean
	private UserDao dao;

	List<User> list = TestDataUtility.userList();
	String baseUrl = "http://localhost:8888/";
	RestTemplate restTemplate;
	String token;
	String tokenBody;

	@Test
	public void testRegisterUser() {
		
		String apiUrl = "auth/login-user";
		
		restTemplate = new RestTemplate();
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(baseUrl+apiUrl, TestDataUtility.getUserNameAndPassword(), String.class);
		Assertions.assertEquals(200, postForEntity.getStatusCodeValue());
		
		tokenBody = postForEntity.getBody();
		
		try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(tokenBody);
	        JsonNode tokenNode = jsonNode.get("token");

	        if (tokenNode != null) {
	            token = tokenNode.asText();
	        }
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
		
		//=======================================================================
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token); // Add the token to the headers
        
        // Create the request entity with headers
        HttpEntity<?> requestEntity = new HttpEntity<>(TestDataUtility.prepareUserData(),headers);
        
        // Perform the second API request with the token in the headers
        restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            "http://localhost:8888/admin/add-user", // Replace with the actual second API endpoint
            HttpMethod.POST,
            requestEntity,
            String.class
        );

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());

    }
}
