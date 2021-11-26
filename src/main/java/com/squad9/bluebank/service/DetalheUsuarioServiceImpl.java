package com.squad9.bluebank.service;

import java.util.Optional;

import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public DetalheUsuarioServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if (cliente.isEmpty()) {
            throw new UsernameNotFoundException("Cliente não encontrado");
        }
        return new DetalheUsuario(cliente);
    }
    
}
