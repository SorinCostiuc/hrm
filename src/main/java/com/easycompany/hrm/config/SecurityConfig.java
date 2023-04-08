package com.easycompany.hrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_POWER_USER = "POWER_USER";

    @Bean
    @Primary
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("Team_Lead_User")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles(ROLE_USER)
                .build());

        inMemoryUserDetailsManager.createUser(User.withUsername("HR_User")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles(ROLE_ADMIN)
                .build());

        inMemoryUserDetailsManager.createUser(User.withUsername("power_user")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles(ROLE_POWER_USER, ROLE_USER, ROLE_ADMIN)
                .build());


        return inMemoryUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> {
                //personnel
            auth.requestMatchers("/hrm/v1/personnel/create").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/personnel/name-search").hasAnyRole(ROLE_USER, ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/personnel/status-search").hasAnyRole(ROLE_USER, ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/personnel/job-title-search").hasAnyRole(ROLE_USER, ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/personnel/cnp-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/personnel/job-title-change").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/personnel/employ").hasRole(ROLE_ADMIN);
            auth.requestMatchers("/hrm/v1/personnel/fire").hasRole(ROLE_ADMIN);
            auth.requestMatchers("/hrm/v1/personnel/info").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/personnel/id-search").hasAnyRole(ROLE_USER, ROLE_ADMIN, ROLE_POWER_USER);
                //contract
            auth.requestMatchers("/hrm/v1/contracts/create").hasRole(ROLE_ADMIN);
            auth.requestMatchers("/hrm/v1/contracts/id-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/contracts/end-date").hasRole(ROLE_ADMIN);
            auth.requestMatchers("/hrm/v1/contracts/type").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/contracts/start-date-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/contracts/end-date-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/contracts/type-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
                //salary
            auth.requestMatchers("/hrm/v1/salaries/create").hasRole(ROLE_ADMIN);
            auth.requestMatchers("/hrm/v1/salaries/max").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/min").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/date-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/max-from-date").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/min-from-date").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/personnel-id-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/max-hours-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/min-hours-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/max-hours-date-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/min-hours-date-search").hasAnyRole(ROLE_ADMIN, ROLE_POWER_USER);
            auth.requestMatchers("/hrm/v1/salaries/increase").hasRole(ROLE_POWER_USER);


//            For THYMELEAF CONTROLLER
//            auth.requestMatchers("/start").hasRole(ROLE_USER);
//            auth.requestMatchers("/welcome").hasRole(ROLE_USER);
//            auth.requestMatchers("/showAll").hasRole(ROLE_USER);

        }).httpBasic();

        httpSecurity.csrf()
                .disable().authorizeHttpRequests()
                .and()
                .cors()
                .disable().authorizeHttpRequests();

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
