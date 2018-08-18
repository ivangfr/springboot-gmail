package com.mycompany.springbootgmail.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
@ConfigurationProperties(prefix = "gmail")
public class GmailProperties {

    @NotNull
    @NotEmpty
    private String applicationName;

    @NotNull
    @NotEmpty
    private String clientId;

    @NotNull
    @NotEmpty
    private String clientSecret;

    @NotNull
    @NotEmpty
    private String tokenServerUrl;

    @NotNull
    @NotEmpty
    private String refreshToken;

}
