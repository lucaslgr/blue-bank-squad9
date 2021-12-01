package com.squad9.bluebank.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.squad9.bluebank.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class DetalheUsuario implements UserDetails {

    private final Optional<Cliente> cliente;

    @Autowired
    public DetalheUsuario(Optional<Cliente> cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return cliente.orElse(new Cliente()).getSenha();
    }

    @Override
    public String getUsername() {
        return cliente.orElse(new Cliente()).getEmail();
    }

    public Long getId() {
        return cliente.orElse(new Cliente()).getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
