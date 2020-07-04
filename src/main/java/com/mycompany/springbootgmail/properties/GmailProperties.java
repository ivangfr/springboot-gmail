package com.mycompany.springbootgmail.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@ConfigurationProperties(prefix = "gmail")
public class GmailProperties {

    @NotBlank
    private String applicationName;

    @NotBlank
    private String tokenServerUrl;

    private String clientId;
    private String clientSecret;
    private String refreshToken;

}
