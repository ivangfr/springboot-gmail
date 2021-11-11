package com.mycompany.springbootgmail.config;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.mycompany.springbootgmail.exception.GmailConfigException;
import com.mycompany.springbootgmail.properties.GmailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(GmailProperties.class)
public class GmailConfig {

    private final GmailProperties gmailProperties;

    @Bean
    Gmail gmail() {
        return new Gmail.Builder(httpTransport(), JacksonFactory.getDefaultInstance(), getCredentials())
                .setApplicationName(gmailProperties.getApplicationName()).build();
    }

    private Credential getCredentials() {
        BasicAuthentication auth = new BasicAuthentication(gmailProperties.getClientId(),
                gmailProperties.getClientSecret());

        return new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
                .setJsonFactory(JacksonFactory.getDefaultInstance())
                .setTransport(httpTransport())
                .setClientAuthentication(auth)
                .setTokenServerEncodedUrl(gmailProperties.getTokenServerUrl())
                .build()
                .setRefreshToken(gmailProperties.getRefreshToken());
    }

    @Bean
    NetHttpTransport httpTransport() {
        try {
            return GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException | IOException e) {
            throw new GmailConfigException(e);
        }
    }

    public static final String USER_ID_ME = "me";
}
