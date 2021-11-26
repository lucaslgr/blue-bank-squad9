package com.squad9.bluebank.config;


import com.squad9.bluebank.service.DetalheUsuarioServiceImpl;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DetalheUsuarioServiceImpl detalheUsuarioService;

    public SecurityConfig(DetalheUsuarioServiceImpl detalheUsuarioService) {
        this.detalheUsuarioService = detalheUsuarioService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalheUsuarioService);
    }

}
