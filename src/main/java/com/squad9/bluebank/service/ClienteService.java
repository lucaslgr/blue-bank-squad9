package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ClienteRequestDTO;
import com.squad9.bluebank.dto.ClienteResponseDTO;
import com.squad9.bluebank.dto.LoginRequestDTO;
import com.squad9.bluebank.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public interface ClienteService {
    ClienteResponseDTO salvarCliente(ClienteRequestDTO clienteRequestDTO) throws Exception;

    // login do cliente
    String loginCliente(LoginRequestDTO loginRequestDTO) throws Exception;

    //Listar todos os clientes
    List<ClienteResponseDTO> retornarTodosOsClientes();

    //Listar cliente por ID
    ClienteResponseDTO encontrarClientePeloId(Long id) throws Exception;

    //Atualizar Cliente
    String atualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) throws Exception;

    //Deleta o cliente por ID
    void deletarCliente(Long id) throws Exception;
}
