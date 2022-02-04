package com.jingzhe.authentication.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

import java.util.List;

import static com.jingzhe.authentication.config.AuthenticationProperties.PREFIX;

@Validated
@Data
@ConfigurationProperties(prefix = PREFIX)
public class AuthenticationProperties {

    public static final String PREFIX = "auth";

    @NotNull
    private KeyStoreProperties keyStore;

    @NotNull
    private KeyProperties signingKey;

    private int validity = 600;

    private List<JwksKey> jwksKeys;

    @Data
    public static class KeyStoreProperties {
        @NotNull
        private Resource path;
        @NotNull
        private String password;
    }

    @Data
    public static class KeyProperties {
        @NotNull
        private String alias;

        @NotNull
        private String password;
    }

    @Data
    public static class JwksKey {
        @NotNull
        private Resource resource;
        @NotNull
        private String kid;
        private String alg;
        private String use;
    }

}