package com.squad9.bluebank.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.repository.ClienteRepository;
import com.squad9.bluebank.service.DetalheUsuario;
import com.squad9.bluebank.service.DetalheUsuarioServiceImpl;
import com.squad9.bluebank.util.JwtTokenUtil;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAutorizacaoFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final ClienteRepository clienteRepository;
    private final DetalheUsuarioServiceImpl detalheUsuarioService;

    private final String AUTORIZACAO_BEARER = "Bearer ";

    public JwtAutorizacaoFilter(
        JwtTokenUtil jwtTokenUtil, 
        ClienteRepository clienteRepository,
        DetalheUsuarioServiceImpl detalheUsuarioService
    ) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.clienteRepository = clienteRepository;
        this.detalheUsuarioService = detalheUsuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws BadCredentialsException, ServletException, IOException {
    
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(AUTORIZACAO_BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        if (!jwtTokenUtil.isTokenValido(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        DetalheUsuario detalheUsuario = (DetalheUsuario)detalheUsuarioService.loadUserByUsername(jwtTokenUtil.getEmailDoToken(token));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(detalheUsuario, null,
                new ArrayList<>());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
    
}
