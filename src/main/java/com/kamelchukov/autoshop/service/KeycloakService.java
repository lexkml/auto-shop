package com.kamelchukov.autoshop.service;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class KeycloakService {
    @Value("${keycloak.auth-server-url}")
    private String url;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak-internal-admin.username}")
    private String username;

    @Value("${keycloak-internal-admin.password}")
    private String password;


    public String getTokenViaKeycloakPrincipal() {
        var authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var principal = (KeycloakPrincipal<?>) authentication.getPrincipal();
        var token = principal.getKeycloakSecurityContext().getTokenString();
        return "Bearer " + token;
    }

    public String getTokenViaKeycloakTokenManager() {
        var token = keycloak().tokenManager().getAccessTokenString();
        return "Bearer " + token;
    }

    private Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(url)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .username(username)
                .password(password)
                .build();
    }
}
