package com.rent.T.productService;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		// Define base URI of app
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port; // Use local server port annotation
	}

	@Test
	void shouldCreateProduct() {
		// Your test logic here
		String requestBody = """
					{
						"id" : "1",
						"name" : "product 1",
						"description" : "product 1 description",
						"price" : 10000.00
					}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iphone 15"))
				.body("description", Matchers.equalTo("apple smart phone"))
				.body("price", Matchers.equalTo(100));
	}
}