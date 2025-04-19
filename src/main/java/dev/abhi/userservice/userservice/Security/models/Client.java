package dev.abhi.userservice.userservice.Security.models;

import java.time.Instant;

import com.mysql.cj.protocol.ColumnDefinition;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "`client`")
//@Builder
public class Client {
    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String clientAuthenticationMethods;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String authorizationGrantTypes;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String redirectUris;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String postLogoutRedirectUris;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String scopes;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String clientSettings;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String tokenSettings;



    public void setId(String id) {
        this.id = id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientIdIssuedAt(Instant clientIdIssuedAt) {
        this.clientIdIssuedAt = clientIdIssuedAt;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setClientSecretExpiresAt(Instant clientSecretExpiresAt) {
        this.clientSecretExpiresAt = clientSecretExpiresAt;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientAuthenticationMethods(String clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public void setAuthorizationGrantTypes(String authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public void setRedirectUris(String redirectUris) {
        this.redirectUris = redirectUris;
    }

    public void setPostLogoutRedirectUris(String postLogoutRedirectUris) {
        this.postLogoutRedirectUris = postLogoutRedirectUris;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public void setClientSettings(String clientSettings) {
        this.clientSettings = clientSettings;
    }

    public void setTokenSettings(String tokenSettings) {
        this.tokenSettings = tokenSettings;
    }
}