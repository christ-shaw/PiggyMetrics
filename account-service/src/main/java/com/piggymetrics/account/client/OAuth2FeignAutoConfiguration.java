package com.piggymetrics.account.client;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;


/*
security:
  oauth2:
    client:
      clientId: account-service
      clientSecret: 123
      accessTokenUri: http://AUTH-SERVICE:5000/uaa/oauth/token
      grant-type: client_credentials
      scope: server

 */

public class OAuth2FeignAutoConfiguration {

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;


    @Value("${security.oauth2.client.scope}")
    private String scope;

    @Value("${security.oauth2.client.accessTokenUri}")
    private String accessTokenUrl;

    @Value("${security.oauth2.client.grant-type}")
    private String grantType;



    private OAuth2ProtectedResourceDetails resourceDetails()
    {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(accessTokenUrl);
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setGrantType(grantType);
        details.setScope(Arrays.asList(scope));
        return details;
    }

    @Bean
    RequestInterceptor oauth2FeignRequestInterceptor()
    {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext()
        , resourceDetails());
    }

    @Bean
    Logger.Level feginLoggerLevel()
    {
        return Logger.Level.FULL;
    }




}