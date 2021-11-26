package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ClienteRequestDTO;
import com.squad9.bluebank.dto.ClienteResponseDTO;
import com.squad9.bluebank.dto.ClienteUpdateRequestDTO;
import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.repository.ClienteCustomRepository;
import com.squad9.bluebank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private ClienteCustomRepository clienteCustomRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ClienteServiceImpl(
            ClienteRepository clienteRepository,
            PasswordEncoder passwordEncoder,
            ClienteCustomRepository clienteCustomRepository) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.clienteCustomRepository = clienteCustomRepository;
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
    public void atualizarCliente(Long id, ClienteUpdateRequestDTO clienteUpdateRequestDTO) throws Exception {
        this.clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado!"));

        if (clienteUpdateRequestDTO.getEmail() != null) {
            //TODO Verificar se o email já não pertence a outro cliente
        }

        this.clienteCustomRepository.atualizaCliente(id, clienteUpdateRequestDTO);
    }

    //Deleta o cliente por ID
    @Override
    public void deletarCliente(Long id) throws Exception {
        var cliente = this.clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado!"));
        this.clienteRepository.delete(cliente);
    }
}
