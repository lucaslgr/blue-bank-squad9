package com.squad9.bluebank.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Entity
@Table (name = "contas_corrente")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long id;

    @Column(name = "numero")
    @NotBlank(message = "O campo não pode estar vazio.")
    @Pattern(regexp = "(^([0-9]{3}).([0-9]{3}).([0-9]{3}).([0-9]{3}).([0-9]{3})-([0-9]{1})$)",
            message = "Número da conta inválido.")
    private String numero;

    @Column(name = "agencia")
    @NotBlank(message = "O campo não pode estar vazio.")
    @Pattern(regexp = "(^([0-9]{4,9})$)",
            message = "Número da agência inválida.")
    private String agencia;

    @Column(name = "data_criacao")
    @NotBlank(message = "O campo não pode estar vazio.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date data_criacao;

    @Column(name ="senha")
    @NotBlank(message = "O campo não pode estar vazio.")
    private String senha;

    @Column(name = "saldo")
    @NotBlank(message = "O campo saldo não pode estar vazio.")
    @PositiveOrZero(message = "Saldo inválido.")
    private Long saldo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }
}
