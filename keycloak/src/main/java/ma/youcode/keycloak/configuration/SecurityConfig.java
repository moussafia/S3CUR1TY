package ma.youcode.keycloak.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private ClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(req-> req
                        .requestMatchers("/","/oauth2Login/**","/webjars/**","/public/api/**").permitAll())
                .authorizeHttpRequests(req-> req
                        .anyRequest().authenticated())
                .oauth2Login(lp->
                        lp.loginPage("/oauth2Login")
                        .defaultSuccessUrl("/")
                )
                .logout(logout->
                        logout.logoutSuccessHandler(oidcLogoutSuccessHandler())
                                .logoutSuccessUrl("/").permitAll()
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(exception->exception.accessDeniedPage("/notAuthorized"))
                .build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        final OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}?logoutsuccess=true");
        return oidcLogoutSuccessHandler;
    }
    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper(){
        return (authorities -> {
            final Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            authorities.forEach( (authority) ->{
                if(authority instanceof OidcUserAuthority oidcUserAuthority){
                    mappedAuthorities.addAll(mapAuthorities(oidcUserAuthority.getIdToken().getClaims()));
                    System.out.println(oidcUserAuthority.getAttributes());
                }else if(authority instanceof OAuth2UserAuthority oAuth2UserAuthority){
                    mappedAuthorities.addAll(mapAuthorities(oAuth2UserAuthority.getAttributes()));
                }});
            return mappedAuthorities;

        });
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(Map<String, Object> claims) {
        final Map<String, Object> realmAccess = ((Map<String, Object>)claims.getOrDefault("realm_access", Collections.emptyMap()));
        final Collection<String> roles = ((Collection<String>)realmAccess.getOrDefault("roles", Collections.emptyList()));
        return roles.stream().map(r->new SimpleGrantedAuthority(r)).toList();
    }


}
