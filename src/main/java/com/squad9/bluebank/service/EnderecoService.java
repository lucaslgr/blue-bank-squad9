package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.EnderecoRequestDTO;
import com.squad9.bluebank.dto.EnderecoResponseDTO;
import com.squad9.bluebank.model.Cliente;

public interface EnderecoService {

    //Cadastrar novo endereço.
    public EnderecoResponseDTO salvarEndereco(EnderecoRequestDTO enderecoRequestDTO,Cliente cliente);

    //Retornar dados do endereço.
    public EnderecoResponseDTO retornarEnderecoPeloId(Long id) throws Exception;

    //Atualizar endereço
    public void atualizarEndereco(Long id,EnderecoRequestDTO enderecoRequestDTO)throws Exception;

}
