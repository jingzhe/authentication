package com.jingzhe.authentication.config;

import com.jingzhe.authentication.service.AccessTokenEncoder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;

import static com.jingzhe.authentication.utils.KeyUtils.getAsPrivateKey;

@Configuration
@EnableConfigurationProperties(AuthenticationProperties.class)
public class AuthConfiguration {
    @Bean
    public KeyStore authKeyStore(AuthenticationProperties authenticationProperties) {
        String storeType = KeyStore.getDefaultType();

        KeyStore keyStore;

        try (InputStream keyStoreStream = authenticationProperties.getKeyStore().getPath().getInputStream()) {
            keyStore = KeyStore.getInstance(storeType);
            keyStore.load(keyStoreStream, authenticationProperties.getKeyStore().getPassword().toCharArray());
        } catch (Exception e) {
            throw new RuntimeException("Cannot initialize key store", e);
        }

        return keyStore;
    }

    @Bean
    public AccessTokenEncoder createAccessTokenEncode(AuthenticationProperties authenticationProperties,
                                                      KeyStore authKeyStore) {
        AuthenticationProperties.KeyProperties signingKeyProps = authenticationProperties.getSigningKey();
        PrivateKey signingKey = getAsPrivateKey(authKeyStore, signingKeyProps.getAlias(), signingKeyProps.getPassword());

        return new AccessTokenEncoder(authenticationProperties, signingKey);
    }
}
