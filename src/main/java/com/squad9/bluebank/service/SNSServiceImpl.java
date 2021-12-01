package com.squad9.bluebank.service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.squad9.bluebank.dto.ClienteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class SNSServiceImpl implements SNSService {

    private AmazonSNSClient snsClient;

    @Autowired
    public SNSServiceImpl(AmazonSNSClient snsClient) {
        this.snsClient = snsClient;
    }

    //Tópico para informar o cadastro de novos clientes
    private final String TOPIC_ARN = "";

    public void addSubscricao(@PathVariable String email) {
        SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN, "email", email);
        snsClient.subscribe(subscribeRequest);
    }

    public void publicaMensagemNoTopicoDeCadastroDeNovosClientes(ClienteResponseDTO clienteResponseDTO) {
        PublishRequest publishRequest = new PublishRequest(
                TOPIC_ARN,
                constroiCorpoDoEmailDeCadastroDeNovosClientes(clienteResponseDTO),
                "Cadastro de um novo cliente no BlueBank [SNS]"
        );
        snsClient.publish(publishRequest);
    }

    private String constroiCorpoDoEmailDeCadastroDeNovosClientes(ClienteResponseDTO clienteResponseDTO) {
        return "Um novo cliente se cadastrou no BlueBank!\n" +
                "\n " +
                "\n " +
                "\n " + clienteResponseDTO.toString();
    }
}
