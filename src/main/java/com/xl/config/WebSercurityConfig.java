package com.xl.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSercurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/hello").permitAll()
                .antMatchers("/user/login").permitAll()
                .anyRequest().authenticated()
                .anyRequest()
                .access("")
                .and()*/
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }
}
