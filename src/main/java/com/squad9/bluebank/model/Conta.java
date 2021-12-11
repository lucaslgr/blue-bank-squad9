package com.squad9.bluebank.model;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contas_corrente", uniqueConstraints = @UniqueConstraint(columnNames = {"agencia", "numero"}))
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta", nullable = false)
    private Long id;

    @Column(name = "numero", length = 20, nullable = false)
    @NotBlank(message = "O campo numero não pode estar vazio.")
    @Pattern(regexp = "(^([0-9]{4,20})-([0-9]{1})$)", message = "Número da conta inválido.")
    private String numero;

    @Column(name = "agencia", length = 9, nullable = false)
    @NotBlank(message = "O campo agencia não pode estar vazio.")
    @Pattern(regexp = "(^([0-9]{4,9})$)",
            message = "Número da agência inválido.")
    private String agencia;

    @Column(name = "data_criacao")
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataCriacao;

    @Column(name = "senha", length = 255, nullable = false)
    @NotBlank(message = "O campo senha não pode estar vazio.")
    @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres.")
    private String senha;

    @Column(name = "saldo")
    @ColumnDefault("0")
    @Generated(GenerationTime.INSERT)
    @PositiveOrZero(message = "Saldo inválido.")
    private Long saldo;

    @OneToOne(targetEntity = Cliente.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @NotNull(message = "O campo cliente é obrigatório")
    private Cliente cliente;

    @OneToMany(mappedBy = "contaEmissora")
    private List<Transacao> transacoesEmitidas;

    @OneToMany(mappedBy = "contaReceptora")
    private List<Transacao> transacoesRecebidas;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Transacao> getTransacoesEmitidas() {
        return transacoesEmitidas;
    }

    public List<Transacao> getTransacoesRecebidas() {
        return transacoesRecebidas;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

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
