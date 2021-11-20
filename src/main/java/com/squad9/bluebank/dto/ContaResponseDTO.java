package com.squad9.bluebank.dto;

import com.squad9.bluebank.model.Conta;

import java.util.Date;

public class ContaResponseDTO {
    private Long idConta;
    private String agencia;
    private String numero;
    private Date dataCriacao;
    private Long saldo;
    private Long idCliente;

    public static ContaResponseDTO converter(Conta conta){
        var contaResponseDTO = new ContaResponseDTO();

        contaResponseDTO.setIdConta(conta.getId());
        contaResponseDTO.setAgencia(conta.getAgencia());
        contaResponseDTO.setNumero(conta.getNumero());
        contaResponseDTO.setDataCriacao(conta.getDataCriacao());
        contaResponseDTO.setSaldo(conta.getSaldo());
//        contaResponseDTO.setIdCliente(conta.getId()); B.O TODO:

        return contaResponseDTO;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
