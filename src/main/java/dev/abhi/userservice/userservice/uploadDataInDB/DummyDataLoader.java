//package dev.abhi.userservice.userservice.uploadDataInDB;
//
//import dev.abhi.userservice.userservice.Security.models.Client;
//import dev.abhi.userservice.userservice.Security.repositories.ClientRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.OidcScopes;
//import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//public class DummyDataLoader implements CommandLineRunner {
//
//    private RegisteredClientRepository registeredClientRepository;
//    private BCryptPasswordEncoder bCryptPasswordEncoder ;
//    public DummyDataLoader(RegisteredClientRepository registeredClientRepository, BCryptPasswordEncoder bCryptPasswordEncoder ){
//       this.registeredClientRepository = registeredClientRepository ;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder ;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        registeredClientRepository();
//
//    }
//
//    //@Bean
//    public void registeredClientRepository() {
//        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("productservice")
//                .clientSecret(bCryptPasswordEncoder.encode("passwordforproductservice"))
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
//                .redirectUri("https://oauth.pstmn.io/v1/callback")
//                .postLogoutRedirectUri("http://127.0.0.1:8080/")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//         registeredClientRepository.save(oidcClient);
//        //return new InMemoryRegisteredClientRepository(oidcClient);
//    }
//}
