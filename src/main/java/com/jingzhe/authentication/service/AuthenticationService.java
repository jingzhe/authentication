package com.jingzhe.authentication.service;

import com.jingzhe.authentication.api.model.AuthenticationRequest;
import com.jingzhe.authentication.api.model.AuthenticationResponse;
import com.jingzhe.authentication.config.AuthenticationProperties;
import com.jingzhe.authentication.model.AccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static com.jingzhe.authentication.utils.KeyUtils.createUuid;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccessTokenEncoder accessTokenEncoder;
    private final AuthenticationProperties authenticationProperties;

    public Mono<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        AccessToken accessToken = createAccessToken(authenticationRequest);
        return accessTokenEncoder.encode(accessToken)
                .map(token -> AuthenticationResponse.builder().accessToken(token).build());
    }

    private AccessToken createAccessToken(AuthenticationRequest authenticationRequest) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(authenticationProperties.getValidity());
        return AccessToken.builder()
                .id(createUuid())
                .user(authenticationRequest.getUserName())
                .group("user")
                .expiration(expiration)
                .issuedAt(now)
                .build();
    }

}
