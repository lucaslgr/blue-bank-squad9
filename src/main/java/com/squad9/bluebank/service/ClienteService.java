package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ClienteRequestDTO;
import com.squad9.bluebank.dto.ClienteResponseDTO;
import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Salvar um cliente
    public ClienteResponseDTO salvarCliente(ClienteRequestDTO clienteRequestDTO){
        var cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setSobrenome(clienteRequestDTO.getSobrenome());
        cliente.setCpf(clienteRequestDTO.getCpf());
        cliente.setRg(clienteRequestDTO.getRg());
        cliente.setDataDeNascimento(clienteRequestDTO.getDataDeNascimento());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setCelular(clienteRequestDTO.getCelular());
        cliente.setTelefone(clienteRequestDTO.getTelefone());
        cliente.setSenha(clienteRequestDTO.getSenha());
        cliente.setNomeDoPai(clienteRequestDTO.getNomeDoPai());
        cliente.setNomeDaMae(clienteRequestDTO.getNomeDaMae());
        cliente.setProfissao(clienteRequestDTO.getProfissao());
        cliente.setRendaMensal(clienteRequestDTO.getRendaMensal());
        cliente.setPatrimonio(clienteRequestDTO.getPatrimonio());

        this.clienteRepository.save(cliente);
        return ClienteResponseDTO.converter(cliente);
    }

    //Listar todos os clientes
    public List<ClienteResponseDTO> retornarTodosOsClientes(){
        var clientes =  this.clienteRepository.findAll();
        return clientes.stream().map(ClienteResponseDTO::converter).collect(Collectors.toList());
    }

    //Listar cliente por ID
    public ClienteResponseDTO encontrarClientePeloId(Long id) throws Exception {
        var cliente =  this.clienteRepository.findById(id).orElseThrow(() ->  new Exception("Client Not Found!"));
        return ClienteResponseDTO.converter(cliente);
    }

    //Atualizar Cliente
    public void atualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) throws Exception {
        var cliente =  this.clienteRepository.findById(id).orElseThrow(() ->  new Exception("Client Not Found!"));

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

        this.clienteRepository.save(cliente);
    }

    //Deleta o cliente por ID
    public void deletarCliente(Long id) throws Exception{
        var cliente =  this.clienteRepository.findById(id).orElseThrow(() ->  new Exception("Client Not Found!"));
        this.clienteRepository.delete(cliente);
    }
}
