package com.jingzhe.authentication;

import com.jingzhe.authentication.api.model.AuthenticationRequest;
import com.jingzhe.authentication.api.model.AuthenticationResponse;
import com.jingzhe.authentication.api.model.JwksResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationApplicationTests extends IntegrationTest {


	@Test
	void getJwks_succeedTest() {
		JwksResponse jwks = Specifications.givenJwksGet()
				.then()
				.spec(Specifications.expectResponseOk())
				.extract()
				.as(JwksResponse.class);

		assertEquals(1, jwks.getKeys().size());
		assertEquals("building_auth", jwks.getKeys().get(0).getKid());
	}

	@Test
	void getAccessToken_succeedTest() {
		AuthenticationRequest request = new AuthenticationRequest();
		request.setPassword("test_pass");
		request.setUserName("user");
		AuthenticationResponse response = Specifications.givenAccessTokenPost(request)
				.then()
				.spec(Specifications.expectResponseOk())
				.extract()
				.as(AuthenticationResponse.class);

		assertNotEquals(0, response.getAccessToken().length());
	}

	@Test
	void getAccessToken_withWrongData_failedTest() {
		AuthenticationRequest request = new AuthenticationRequest();
		request.setPassword("test_pass");
		Specifications.givenAccessTokenPost(request)
				.then()
				.spec(Specifications.expect(400, BAD_REQUEST.name()));
	}

}
