package com.bear.security.security.config;


import com.bear.security.security.filter.JWTFilter;
import com.bear.security.security.handler.LoginFailureHandler;
import com.bear.security.security.handler.LoginSuccessHandler;
import com.bear.security.security.handler.LogoutHandler;
import com.bear.security.security.provider.SmsProvider;
import com.bear.security.security.provider.UsernamePasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security 主配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTFilter jwtFilter;

    @Autowired
    private SmsProvider smsProvider;

    @Autowired
    private UsernamePasswordProvider usernamePasswordProvider;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler failureHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(failureHandler);
        http.logout().logoutSuccessHandler(logoutHandler);

        http.authorizeRequests()
                .antMatchers( "/user/sms/login").anonymous()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(smsProvider);
        auth.authenticationProvider(usernamePasswordProvider);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
