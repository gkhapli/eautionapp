package com.eauctionapp.zuulservice.config;

import com.eauctionapp.zuulservice.filter.JwtAuthenticationFilter;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws
            Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .cors().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                //.anonymous()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()

                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/token/*","/users/*")
                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .addFilterBefore(authenticationTokenFilterBean(),
                        UsernamePasswordAuthenticationFilter.class)
                //.antMatchers("/backend/admin").hasRole("ADMIN")
                //.antMatchers("/backend/user").hasRole("USER")
                //.antMatchers("/backend/guest").permitAll();
        ;
    }

    private CorsConfigurationSource configurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedHeader("Content-Type");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
