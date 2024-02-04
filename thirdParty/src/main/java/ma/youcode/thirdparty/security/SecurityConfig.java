package ma.youcode.thirdparty.security;

import lombok.RequiredArgsConstructor;
import ma.youcode.thirdparty.userDetails.AdditionalAuthenticationDetailsSource;
import ma.youcode.thirdparty.userDetails.Oauth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private Oauth2AuthenticationSuccessHandler authenticationSuccessHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .authenticationDetailsSource(new AdditionalAuthenticationDetailsSource())
                .and()
                .oauth2Login()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .and()
                .authorizeRequests()
                .requestMatchers("/register","/login").permitAll()
                .requestMatchers("/css/**", "/webjars/**").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
