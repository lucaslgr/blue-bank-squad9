package com.squad9.bluebank.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.repository.ClienteRepository;
import com.squad9.bluebank.util.JwtTokenUtil;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAutorizacaoFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final ClienteRepository clienteRepository;

    public JwtAutorizacaoFilter(
        JwtTokenUtil jwtTokenUtil, 
        ClienteRepository clienteRepository
    ) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.clienteRepository = clienteRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        if (!jwtTokenUtil.isTokenValido(token)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "O token não é valido.");
            return;
        }

        Cliente cliente = clienteRepository.findByEmail(jwtTokenUtil.getEmailDoToken(token)).orElse(new Cliente());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(cliente.getEmail(), cliente.getSenha(),
                new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
    
}
