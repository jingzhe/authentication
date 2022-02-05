package com.jingzhe.authentication;

import com.jingzhe.authentication.api.model.AuthenticationRequest;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.is;

@UtilityClass
public class Specifications {

    private final String PATH = "/authentication";

    public Response givenAccessTokenPost(AuthenticationRequest authenticationRequest) {
        return given()
                .basePath(PATH)
                .contentType(ContentType.JSON)
                .body(authenticationRequest)
                .post();
    }


    public Response givenJwksGet() {
        return given()
                .basePath(PATH + "/jwks")
                .get();
    }

    public ResponseSpecification expectResponseOk() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HTTP_OK)
                .build();
    }

    public static ResponseSpecification expect(int errorCode, String status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(errorCode)
                .expectBody("status", is(status))
                .build();
    }

}
