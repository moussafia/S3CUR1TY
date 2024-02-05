package ma.youcode.thirdparty.security;

import lombok.RequiredArgsConstructor;
import ma.youcode.thirdparty.userDetails.Oauth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@RequiredArgsConstructor

public class SecurityConfig {
    @Autowired
    private final Oauth2AuthenticationSuccessHandler authenticationSuccessHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .and()
                .oauth2Login()
                .loginPage("/login")
               .successHandler(authenticationSuccessHandler)
                .and()
                .authorizeRequests()
                .requestMatchers("/register","/login").permitAll()
                .requestMatchers("/css/**", "/webjars/**").permitAll()
                .anyRequest().hasRole("USER");

        return http.build();
    }
    @Bean
    public RedirectStrategy redirectStrategy(){
        return new DefaultRedirectStrategy();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}
