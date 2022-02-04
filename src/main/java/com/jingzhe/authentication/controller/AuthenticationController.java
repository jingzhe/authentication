package com.jingzhe.authentication.controller;

import com.jingzhe.authentication.api.AuthenticationEndpoint;
import com.jingzhe.authentication.api.model.AuthenticationRequest;
import com.jingzhe.authentication.api.model.AuthenticationResponse;
import com.jingzhe.authentication.api.model.JwksResponse;
import com.jingzhe.authentication.service.AuthenticationService;
import com.jingzhe.authentication.service.JwksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationEndpoint {
    private final JwksService jwksService;
    private final AuthenticationService authenticationService;

    @Override
    public Mono<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }

    @Override
    public Mono<JwksResponse> getKeys() {
        return jwksService.getJwks();
    }
}
