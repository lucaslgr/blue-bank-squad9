package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ContaRequestDTO;
import com.squad9.bluebank.dto.ContaResponseDTO;
import com.squad9.bluebank.model.Conta;
import com.squad9.bluebank.repository.ClienteRepository;
import com.squad9.bluebank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ContaServiceImpl implements ContaService {

    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ContaServiceImpl(ContaRepository contaRepository, ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final String numeroAgencia = "00001";

    //Cadastra um nova conta
    @Override
    public ContaResponseDTO cadastrarNovaConta(ContaRequestDTO contaRequestDTO) throws Exception {
        // Verificar se a pessoa já pelo id
        var cliente = clienteRepository.findByCpf(contaRequestDTO.getCpf()).orElseThrow(() -> new Exception("Cliente não existe"));

        //Verificar se o cliente já possui conta
        if (contaRepository.findByCliente(cliente).isPresent()) {
            throw new Exception("Cliente já possui conta");
        }

        // Gerar número da conta
        String stringNumeroConta = geraNumeroConta(cliente.getId());

        Conta conta = new Conta();
        conta.setSenha(passwordEncoder.encode(contaRequestDTO.getSenha())); // Criptografar senha
        conta.setCliente(cliente);
        conta.setNumero(stringNumeroConta);
        conta.setAgencia(numeroAgencia);

        this.contaRepository.save(conta);
        return ContaResponseDTO.converter(conta);
    }

    //Retorna os dados da conta
    @Override
    public ContaResponseDTO retornaDadosDaConta(Long idConta) throws Exception {
        //Verifica se o id do cliente logado é o mesmo id do cliente da conta sendo requisitada

        var conta = this.contaRepository.findById(idConta).orElseThrow(() -> new Exception("Conta não válida"));
        return ContaResponseDTO.converter(conta);

    }

    private String geraNumeroConta(Long idCliente) {
        Long numeroConta = 10000 + idCliente;
        Integer digito = (new Random()).nextInt(10);
        return (numeroConta.toString() + "-" + digito.toString());
    }
}
