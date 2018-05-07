//package com.piggymetrics.account.client;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
//import org.springframework.util.Assert;
//
//public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//
//    private static final String BEARER_TOKEN_TYPE = "Bearer";
//    private Logger log = LoggerFactory.getLogger("OAuth2FeignRequestInterceptor");
//
//    public OAuth2FeignRequestInterceptor() {
//    }
//
//    @Override
//    public void apply(RequestTemplate template) {
//        if (template.headers().containsKey(AUTHORIZATION_HEADER)) {
//            log.warn("The Authorization token has been already set");
//        } else {
//            boolean relayed = false;
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (authentication instanceof AbstractAuthenticationToken) {
//                AbstractAuthenticationToken aat = (AbstractAuthenticationToken) authentication;
//                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) aat.getDetails();
//                String type = details.getTokenType();
//                String jwt = details.getTokenValue();
//                if (OAuth2AccessToken.BEARER_TYPE.equalsIgnoreCase(type) && jwt != null) {
//                    relayed = true;
//                    log.debug("The Authorization token has added in header, token:{}", jwt);
//                    template.header("Authorization", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, jwt));
//                }
//            }
//            if (!relayed) {
//                log.warn("Not relay the JWT for service: {}", template.url());
//            }
//        }
//    }
//}