package com.squad9.bluebank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class DepositoRequestDTO {

    @NotBlank(message = "O campo numeroContaDestino não pode estar vazio.")
    @Pattern(regexp = "(^([0-9]{5,18})\\-[0-9]$)", message = "Conta inválida.")
    private String numeroContaDestino;

    @NotNull(message = "O campo valor não pode estar vazio.")
    @Positive(message = "Valor inválido.")
    private Long valor;

    public String getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(String numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
}
