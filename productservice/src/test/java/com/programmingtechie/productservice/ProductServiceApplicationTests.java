package com.programmingtechie.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmingtechie.productservice.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//Running this test class fails due to docket video timestamp: 41:18
//https://www.youtube.com/watch?v=lh1oQHXVSc0&list=PLSVW22jAG8pBnhAdq9S8BpLnZ0_jVBj0c&index=3
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	/*https://java.testcontainers.org/
	https://java.testcontainers.org/modules/databases/mongodb/
	testContainers dependency in pom from spring tool suite ti write test cases
	that will run in docker locally
	testContainers can also be used for message queue kafka , k3, rabbitMQ

	Good Practice to keep BOM Bill of Materials in pom
	as mongodb can use different version of testContainer dependency than kafka etc
	So to make them use one version of testContainers dependent in pom using BOM
	from Maven dependency of TestContainers Documentation Home page
	*/

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2.2");

	/*
	mockmvc object to make request to productController
	It provides a mocker Servlet Environment to call Controller Endpoints and receive response
	as per response defined inside controller CREATED< OK etc
	 */
	@Autowired
	private MockMvc mockMvc;
	/*
	Object Mapper from json.databind converts pojo to json and vice versa
	 */
	@Autowired
	private ObjectMapper objectMapper;
	/*
	Dynamically providing URI for Integration Test as we are
	using mongodb docker container not local mongodb database
	 */
	@DynamicPropertySource
	static void setProperty(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getPrpductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());
		//content will only take string arg so Object mapper is used
	}

	private ProductRequest getPrpductRequest() {
		return ProductRequest.builder().name("IPhone 13")
				.description("IPhone 13")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

}
