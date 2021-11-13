package com.omega.bioway.apigateway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String CUSTOMER="ROLE_CUSTOMER";
    private final String SUPPLIER="ROLE_SUPPLIER";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.POST,"/supplier").permitAll()
                .antMatchers(HttpMethod.GET,"/supplier").permitAll()
                .antMatchers(HttpMethod.GET,"/supplier/{supplier_id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/suppliers/{supplier_id}").hasAnyAuthority(SUPPLIER)
                .antMatchers(HttpMethod.DELETE,"/suppliers/{supplier_id}").hasAnyAuthority(SUPPLIER)
                .antMatchers(HttpMethod.GET, "/products").permitAll()
                .antMatchers(HttpMethod.GET,"/products/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/products/{productId}/questions").hasAnyAuthority(CUSTOMER)
                .antMatchers(HttpMethod.PUT,"/products/{productId}/questions").hasAnyAuthority(SUPPLIER)
                .antMatchers(HttpMethod.POST,"/products").hasAnyAuthority(SUPPLIER)
                .antMatchers(HttpMethod.PUT,"/products/{productId}").hasAnyAuthority(SUPPLIER)
                .antMatchers(HttpMethod.POST, "/payments").hasAnyAuthority(CUSTOMER)
                .antMatchers(HttpMethod.POST, "/customers").permitAll()
                .antMatchers(HttpMethod.GET, "/customers").permitAll()
                .antMatchers(HttpMethod.GET, "/customers/{customerId}").permitAll()
                .antMatchers(HttpMethod.PUT, "/customers/{customerId}").hasAnyAuthority(CUSTOMER)
                .antMatchers(HttpMethod.POST, "/cart").hasAnyAuthority(CUSTOMER)
                .antMatchers(HttpMethod.GET, "/cart/{cartId}").hasAnyAuthority(CUSTOMER)
                .antMatchers(HttpMethod.PUT, "/cart/{cartId}/items").hasAnyAuthority(CUSTOMER)
                .antMatchers(HttpMethod.PATCH, "/cart/{cartId}/items").hasAnyAuthority(CUSTOMER)
                .antMatchers(HttpMethod.DELETE, "/cart/{cartId}/items/{productId}").hasAnyAuthority(CUSTOMER)
                .anyRequest().authenticated();
    }
}
