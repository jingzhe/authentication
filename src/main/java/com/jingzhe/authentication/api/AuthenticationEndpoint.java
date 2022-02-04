package com.jingzhe.authentication.api;

import com.jingzhe.authentication.api.model.AuthenticationRequest;
import com.jingzhe.authentication.api.model.AuthenticationResponse;
import com.jingzhe.authentication.api.model.JwksResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(AuthenticationEndpoint.PATH)
public interface AuthenticationEndpoint {
    String PATH = "/authentication";

    @Operation(
            summary = "User authentication",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User access token"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Input value invalid"
                    )
            }
    )
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @CrossOrigin(allowCredentials = "true", originPatterns = "*")
    Mono<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest);

    @Operation(
            summary = "Request JWKS",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Public key in JWKS format"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    @GetMapping(
            value = "jwks",
            produces = APPLICATION_JSON_VALUE
    )
    Mono<JwksResponse> getKeys();
}
