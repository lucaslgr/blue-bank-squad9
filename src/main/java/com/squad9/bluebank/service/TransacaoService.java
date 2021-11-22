package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.TransacaoRequestDTO;
import com.squad9.bluebank.dto.TransacaoResponseDTO;

import java.util.List;

public interface TransacaoService {

    void salvar(TransacaoRequestDTO transacaoRequestDTO) throws Exception;

    List<TransacaoResponseDTO> pegaTransacoesPeloIdDoCliente(Long idCliente) throws Exception;

    List<TransacaoResponseDTO> pegaTodasTransacoesDoBanco();
}
