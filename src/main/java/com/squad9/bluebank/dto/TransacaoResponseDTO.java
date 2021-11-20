package com.squad9.bluebank.dto;

import com.squad9.bluebank.model.Transacao;

import java.util.Date;

public class TransacaoResponseDTO {
    private Long id;
    private Long valor;
    private Long idContaEmissora;
    private Long idContaReceptora;
    private Date dataEnvio;
    private Date dataRecebimento;

    public static TransacaoResponseDTO converter(Transacao transacao) {
        var transacaoResponseDTO = new TransacaoResponseDTO();
        transacaoResponseDTO.setId(transacao.getId());
        transacaoResponseDTO.setValor(transacao.getValor());
        transacaoResponseDTO.setIdContaEmissora(transacao.getContaEmissora().getId());
        transacaoResponseDTO.setIdContaReceptora(transacao.getContaReceptora().getId());
        transacaoResponseDTO.setDataEnvio(transacao.getDataEnvio());
        transacaoResponseDTO.setDataRecebimento(transacao.getDataRecebimento());
        return transacaoResponseDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Long getIdContaEmissora() {
        return idContaEmissora;
    }

    public void setIdContaEmissora(Long idContaEmissora) {
        this.idContaEmissora = idContaEmissora;
    }

    public Long getIdContaReceptora() {
        return idContaReceptora;
    }

    public void setIdContaReceptora(Long idContaReceptora) {
        this.idContaReceptora = idContaReceptora;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }
}
