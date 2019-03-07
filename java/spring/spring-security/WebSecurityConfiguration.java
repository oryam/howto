package com.oryam.howto.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.oryam.howto.domain.security.UserCredentialService;

/**
 * Import this configuration class in the @Import() annotation on bootable application class.
 * 
 * https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/
 * https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html
 */
@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserCredentialService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and().authorizeRequests()
            .anyRequest().permitAll()
            .and().formLogin().permitAll()
            ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
