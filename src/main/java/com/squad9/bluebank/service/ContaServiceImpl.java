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
import org.springframework.stereotype.Service;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    //Cadastra um nova conta
    @Override
    public Conta cadastrarNovaConta(ContaRequestDTO contaRequestDTO) throws Exception {
        
        return conta;
    }

    //Retorna os dados da conta
    @Override
    public ContaResponseDTO retornaDadosDaConta(Long idConta) throws Exception {
        //Verifica se o id do cliente logado é o mesmo id do cliente da conta sendo requisitada
        var Cliente cliente = this.contaRepository.findById(idConta).getCliente();
        Long idCliente = cliente.getId();
        if () {
            throw new Exception("Conta não pertencente ao cliente");
        }
        
        //
        var conta = this.contaRepository.findById(idConta).orElseThrow(() -> new Exception("Conta não válida"));
        return ContaResponseDTO.converter(conta);

    }

    }
