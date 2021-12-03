package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ContaRequestDTO;
import com.squad9.bluebank.dto.ContaResponseDTO;
import com.squad9.bluebank.dto.DepositoRequestDTO;
import com.squad9.bluebank.model.Conta;

public interface ContaService {
    
    //Cadastrar nova conta
    public ContaResponseDTO cadastrarNovaConta(ContaRequestDTO contaRequestDTO) throws Exception;

    //Retornar dados conta
    public ContaResponseDTO retornaDadosDaConta(Long IdConta) throws Exception;

    public DepositoResponseDTO realizarDeposito(DepositoRequestDTO depositoRequestDTO) throws Exception;
}
