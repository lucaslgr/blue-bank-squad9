package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ClienteRequestDTO;
import com.squad9.bluebank.dto.ClienteResponseDTO;
import com.squad9.bluebank.dto.LoginRequestDTO;
import com.squad9.bluebank.dto.LoginResponseDTO;
import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.repository.ClienteRepository;
import com.squad9.bluebank.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public ClienteServiceImpl(
        ClienteRepository clienteRepository, 
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        JwtTokenUtil jwtTokenUtil
    ) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    //Salvar um cliente
    @Override
    public ClienteResponseDTO salvarCliente(ClienteRequestDTO clienteRequestDTO) throws Exception {
        if (this.clienteRepository.findByCpf(clienteRequestDTO.getCpf()).isPresent()) {
            throw new Exception("Cliente com CPF já cadastrado!");
        }

        if (this.clienteRepository.findByRg(clienteRequestDTO.getRg()).isPresent()) {
            throw new Exception("Cliente com RG já cadastrado!");
        }

        if (this.clienteRepository.findByEmail(clienteRequestDTO.getEmail()).isPresent()) {
            throw new Exception("Cliente com Email já cadastrado!");
        }

        var cliente = new Cliente();

        //Criptografando a senha
        cliente.setSenha(passwordEncoder.encode(clienteRequestDTO.getSenha()));

        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setSobrenome(clienteRequestDTO.getSobrenome());
        cliente.setCpf(clienteRequestDTO.getCpf());
        cliente.setRg(clienteRequestDTO.getRg());
        cliente.setDataDeNascimento(clienteRequestDTO.getDataDeNascimento());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setCelular(clienteRequestDTO.getCelular());
        cliente.setTelefone(clienteRequestDTO.getTelefone());
        cliente.setNomeDoPai(clienteRequestDTO.getNomeDoPai());
        cliente.setNomeDaMae(clienteRequestDTO.getNomeDaMae());
        cliente.setProfissao(clienteRequestDTO.getProfissao());
        cliente.setRendaMensal(clienteRequestDTO.getRendaMensal());
        cliente.setPatrimonio(clienteRequestDTO.getPatrimonio());

        this.clienteRepository.save(cliente);
        return ClienteResponseDTO.converter(cliente);
    }

    @Override
    public String loginCliente(LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getSenha(), new ArrayList<>()));
        } catch (BadCredentialsException e) {
            throw new Exception("Email ou senha inválidos");
        }

        // gerar token
        String token = jwtTokenUtil.gerarToken(loginRequestDTO.getEmail());
    
        // retornar token
        return token;
    }

    //Listar todos os clientes
    @Override
    public List<ClienteResponseDTO> retornarTodosOsClientes() {
        var clientes = this.clienteRepository.findAll();
        return clientes.stream().map(ClienteResponseDTO::converter).collect(Collectors.toList());
    }

    //Listar cliente por ID
    @Override
    public ClienteResponseDTO encontrarClientePeloId(Long id) throws Exception {
        var cliente = this.clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado!"));
        return ClienteResponseDTO.converter(cliente);
    }

    //Atualizar Cliente
    @Override
    public void atualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) throws Exception {
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado!"));
        final String email = clienteRequestDTO.getEmail();

        if(clienteRepository.findByEmailExceptById(email, id).isPresent()) {
            throw new Exception("Email já utilizado.");
        }

        cliente.setEmail(email);
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setSobrenome(clienteRequestDTO.getSobrenome());
        cliente.setCelular(clienteRequestDTO.getCelular());
        cliente.setTelefone(clienteRequestDTO.getTelefone());
        cliente.setSenha(clienteRequestDTO.getSenha());
        cliente.setNomeDoPai(clienteRequestDTO.getNomeDoPai());
        cliente.setNomeDaMae(clienteRequestDTO.getNomeDaMae());
        cliente.setProfissao(clienteRequestDTO.getProfissao());
        cliente.setRendaMensal(clienteRequestDTO.getRendaMensal());
        cliente.setPatrimonio(clienteRequestDTO.getPatrimonio());
        cliente.setDataDeNascimento(clienteRequestDTO.getDataDeNascimento());

        this.clienteRepository.save(cliente);
    }

    //Deleta o cliente por ID
    @Override
    public void deletarCliente(Long id) throws Exception {
        var cliente = this.clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado!"));
        this.clienteRepository.delete(cliente);
    }
}
