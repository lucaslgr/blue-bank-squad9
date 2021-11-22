package com.squad9.bluebank.dto;

import java.util.Date;

//Serve para proteger nossa classe e "escolher" os atributos
//que queremos receber no body do request em JSON
public class TransacaoRequestDTO {
    private Long valor;
    private Date dataEnvio;
    private Date dataRecebimento;
    private Long idContaEmissora;
    private Long idContaReceptora;

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
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
}