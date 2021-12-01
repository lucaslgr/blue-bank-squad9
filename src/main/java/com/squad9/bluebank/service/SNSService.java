package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ClienteResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;

public interface SNSService {
    public void addSubscricao(@PathVariable String email);

    public void publicaMensagemNoTopicoDeCadastroDeNovosClientes(ClienteResponseDTO clienteResponseDTO);
}
