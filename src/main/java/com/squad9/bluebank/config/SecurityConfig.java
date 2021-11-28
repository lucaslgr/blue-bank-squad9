package com.squad9.bluebank.config;

import com.squad9.bluebank.filter.ExceptionHandlerFilter;
import com.squad9.bluebank.filter.JwtAutorizacaoFilter;
import com.squad9.bluebank.service.DetalheUsuarioServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DetalheUsuarioServiceImpl detalheUsuarioService;
    private final JwtAutorizacaoFilter jwtAutorizacaoFilter;

    public SecurityConfig(
        DetalheUsuarioServiceImpl detalheUsuarioService,
        JwtAutorizacaoFilter jwtAutorizacaoFilter
    ) {
        this.detalheUsuarioService = detalheUsuarioService;
        this.jwtAutorizacaoFilter = jwtAutorizacaoFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalheUsuarioService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.formLogin().disable();
        http.logout().disable();
        http.addFilterBefore(jwtAutorizacaoFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new ExceptionHandlerFilter(), JwtAutorizacaoFilter.class);
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/clientes").permitAll()
            .antMatchers(HttpMethod.POST, "/api/clientes/login").permitAll()
            .anyRequest().authenticated().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
