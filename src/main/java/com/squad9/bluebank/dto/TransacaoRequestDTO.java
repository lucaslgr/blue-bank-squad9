package com.squad9.bluebank.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

//Serve para proteger nossa classe e "escolher" os atributos
//que queremos receber no body do request em JSON
public class TransacaoRequestDTO {

    @NotNull(message = "O campo valor não pode estar vazio")
    @Positive(message = "Valor do campo valor é inválido.")
    private Long valor;

    @NotNull(message = "O campo data_envio não pode estar vazio")
    private Date dataEnvio;

    @NotNull(message = "O campo data_recebimento não pode estar vazio")
    private Date dataRecebimento;

    @NotNull(message = "O campo id_conta_emissora não pode estar vazio")
    private Long idContaEmissora;

    @NotNull(message = "O campo id_conta_receptora não pode estar vazio")
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
