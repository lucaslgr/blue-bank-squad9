package com.squad9.bluebank.service;

import java.util.List;

import com.squad9.bluebank.dto.ClienteResponseDTO;
import com.squad9.bluebank.dto.ContaRequestDTO;
import com.squad9.bluebank.dto.ContaResponseDTO;
import com.squad9.bluebank.model.Conta;
import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.repository.ClienteRepository;
import com.squad9.bluebank.repository.ContaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    private final String numeroAgencia = "00001";

    private PasswordEncoder passwordEncoder;


    //Cadastra um nova conta
    @Override
    public ContaResponseDTO cadastrarNovaConta(ContaRequestDTO contaRequestDTO) throws Exception {
        // Verificar se a pessoa já pelo id
        var cliente = clienteRepository.findByCpf(contaRequestDTO.getCpf()).orElseThrow(() -> new Exception("Cliente não existe"));

        // Gerar número da conta
        Long numeroConta = 10000 + cliente.getId();
        String stringNumeroConta = numeroConta.toString();

        // Criptografar senha
        Conta conta = new Conta();
        conta.setSenha(passwordEncoder.encode(contaRequestDTO.getSenha()));
        
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

}
