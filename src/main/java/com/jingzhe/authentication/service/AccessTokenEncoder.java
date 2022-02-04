package com.jingzhe.authentication.service;

import com.jingzhe.authentication.config.AuthenticationProperties;
import com.jingzhe.authentication.exception.AccessTokenException;
import com.jingzhe.authentication.model.AccessToken;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.security.PrivateKey;
import java.sql.Date;

public class AccessTokenEncoder {
    private final AuthenticationProperties authenticationProperties;
    private final PrivateKey signingKey;

    @Autowired
    public AccessTokenEncoder(AuthenticationProperties authenticationProperties, PrivateKey signingKey) {
        this.authenticationProperties = authenticationProperties;
        this.signingKey = signingKey;
    }

    public Mono<String> encode(AccessToken accessToken) {
        JWTClaimsSet claimsSet = claimsBuilderFromAccessToken(accessToken).build();

        return Mono.fromCallable(() -> doEncode(claimsSet))
                .subscribeOn(Schedulers.parallel());

    }

    private String doEncode(JWTClaimsSet claimsSet) {
        RSASSASigner rsassaSigner = new RSASSASigner(signingKey);
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(authenticationProperties.getSigningKey().getAlias())
                .type(JOSEObjectType.JWT)
                .build();
        SignedJWT jws = new SignedJWT(header, claimsSet);
        try {
            jws.sign(rsassaSigner);
            return jws.serialize();
        } catch (JOSEException e) {
            throw new AccessTokenException("Access token encoding failed", e);
        }
    }

    private static JWTClaimsSet.Builder claimsBuilderFromAccessToken(AccessToken accessToken) {

        return new JWTClaimsSet.Builder()
                .issuer("auth-service")
                .audience("building")
                .jwtID(accessToken.getId())
                .expirationTime(Date.from(accessToken.getExpiration()))
                .issueTime(Date.from(accessToken.getIssuedAt()))
                .notBeforeTime(Date.from(accessToken.getIssuedAt()))
                .claim("user", accessToken.getUser())
                .claim("group", accessToken.getGroup());
    }
}
