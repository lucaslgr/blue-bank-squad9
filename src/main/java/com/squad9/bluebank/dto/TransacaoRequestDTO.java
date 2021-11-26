package com.squad9.bluebank.dto;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

//Serve para proteger nossa classe e "escolher" os atributos
//que queremos receber no body do request em JSON
public class TransacaoRequestDTO {

    @NotNull(message = "O campo valor não pode estar vazio")
    @Positive(message = "Valor do campo valor é inválido.")
    private Long valor;

    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Date dataEnvio;

    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Date dataRecebimento;

    @NotNull(message = "O campo idContaEmissora não pode estar vazio")
    private Long idContaEmissora;

    @NotNull(message = "O campo idContaReceptora não pode estar vazio")
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
