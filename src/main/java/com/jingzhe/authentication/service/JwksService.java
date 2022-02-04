package com.jingzhe.authentication.service;

import com.jingzhe.authentication.api.model.JwkValue;
import com.jingzhe.authentication.api.model.JwksResponse;
import com.jingzhe.authentication.config.AuthenticationProperties;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.stream.Collectors;

import static com.jingzhe.authentication.utils.KeyUtils.loadRSAKey;

@Service
public class JwksService {
    private final JwksResponse jwksKeys;

    public JwksService(AuthenticationProperties properties) {
        this.jwksKeys = doGetJwks(properties.getJwksKeys());
    }

    public Mono<JwksResponse> getJwks() {
        return Mono.just(jwksKeys);
    }

    private JwksResponse doGetJwks(List<AuthenticationProperties.JwksKey> keyList) {
        List<JwkValue> keys = keyList.stream()
                .map(jwksKey -> {
                    RSAPublicKey publicKey = loadRSAKey(jwksKey.getResource());
                    RSAKey rsaKey = new RSAKey.Builder(publicKey).build();
                    return JwkValue.builder()
                            .kid(jwksKey.getKid())
                            .use(jwksKey.getUse())
                            .alg(jwksKey.getAlg())
                            .kty(rsaKey.getKeyType().getValue())
                            .e(rsaKey.getPublicExponent().toString())
                            .n(rsaKey.getModulus().toString())
                            .build();
                })
                .collect(Collectors.toList());

        return JwksResponse.builder()
                .keys(keys)
                .build();
    }
}
