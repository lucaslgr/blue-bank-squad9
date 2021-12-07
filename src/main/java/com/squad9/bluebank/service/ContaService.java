package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ContaRequestDTO;
import com.squad9.bluebank.dto.ContaResponseDTO;
import com.squad9.bluebank.dto.DepositoRequestDTO;

public interface ContaService {

    //Cadastrar nova conta
    public ContaResponseDTO cadastrarNovaConta(ContaRequestDTO contaRequestDTO, DetalheUsuario detalheUsuario) throws Exception;

    //Retornar dados conta
    public ContaResponseDTO retornaDadosDaConta(Long IdConta) throws Exception;

    public void realizarDeposito(DepositoRequestDTO depositoRequestDTO) throws Exception;
}
