package com.jingzhe.authentication.utils;

import com.jingzhe.authentication.exception.AuthenticationServiceException;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Locale;
import java.util.UUID;

public class KeyUtils {

    public static RSAPublicKey loadRSAKey(Resource resource) {
        try {
            CertificateFactory e = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) e.generateCertificate(resource.getInputStream());
            return (RSAPublicKey) cert.getPublicKey();
        } catch (CertificateException | IOException e) {
            throw new AuthenticationServiceException("Failed to RSA public key", e);
        }
    }

    public static PrivateKey getAsPrivateKey(KeyStore keyStore, String alias, String password) {
        Key key;
        try {
            key = keyStore.getKey(alias, password.toCharArray());
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to load key with alias " + alias, e);
        }
        if (!(key instanceof PrivateKey)) {
            throw new RuntimeException("No private key exists for the given alias " + alias);
        }
        return (PrivateKey) key;
    }

    public static String createUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}