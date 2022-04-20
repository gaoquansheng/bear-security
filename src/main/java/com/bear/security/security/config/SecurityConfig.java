package com.bear.security.security.config;


import com.bear.security.security.UnauthorizedEntryPoint;
import com.bear.security.security.filter.SmsFilter;
import com.bear.security.security.handler.LoginFailureHandler;
import com.bear.security.security.handler.LoginSuccessHandler;
import com.bear.security.security.handler.LogoutHandler;
import com.bear.security.security.provider.SmsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security 主配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private SmsProvider smsProvider;

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private LoginFailureHandler failureHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SmsFilter smsFilter;

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
//                .and()
//                .formLogin().successHandler(successHandler).failureHandler(failureHandler);
        smsFilter.setAuthenticationSuccessHandler(successHandler);
        http.addFilterAt(smsFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(smsProvider)
                .authenticationProvider(daoAuthenticationProvider);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
