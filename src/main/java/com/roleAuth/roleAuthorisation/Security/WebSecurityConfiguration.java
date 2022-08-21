package com.roleAuth.roleAuthorisation.Security;

import com.roleAuth.roleAuthorisation.Service.CredentialService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CredentialService credentialService;

    public WebSecurityConfiguration(CredentialService credentialService) {
        this.credentialService = credentialService;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(15);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(credentialService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().and().cors().disable()
                .authorizeHttpRequests()
                .antMatchers("/c1/us1").permitAll().and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                /*.and()
                .exceptionHandling().accessDeniedHandler("/")*/;
        
    }
}
