package com.smartTech.RestApi.security;

import com.smartTech.RestApi.Filter.CustomAuthorizationFilter;
import com.smartTech.RestApi.Filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor



public class SecurityConfig extends WebSecurityConfigurerAdapter {



    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;




    private  CustomAuthenticationFilter customAuthenticationFilter;






@Override

    protected void  configure (AuthenticationManagerBuilder auth )throws Exception{
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);


    }

@Override

    protected  void configure( HttpSecurity http)throws Exception {
    super.configure(http);
    CustomAuthenticationFilter  customAuthenticationFilte = new CustomAuthenticationFilter(authenticationManagerBean());
    customAuthenticationFilte.setFilterProcessesUrl("/api/login");
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(STATELESS);
    http.authorizeRequests().antMatchers("/api/login/**","/api/token/refresh/**").permitAll();
    http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
    http.authorizeRequests().antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
    http.authorizeRequests().antMatchers().authenticated();
    http.addFilter(  customAuthenticationFilte);
    http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

}

@Bean
 @Override
 public AuthenticationManager authenticationManagerBean () throws Exception {
    return super.authenticationManagerBean();
}

@Bean



    public CustomAuthorizationFilter customAuthorizationFilter() throws Exception{
    return (CustomAuthorizationFilter) super.authenticationManagerBean();
}

}
