package com.squad9.bluebank.model;


import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "contas_corrente",uniqueConstraints = @UniqueConstraint(columnNames = {"agencia","numero"}))
public class Conta {

    @OneToOne(targetEntity = Cliente.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "contaEmissora") // Aqui é o nome do campo (cammpo no Java) na Entity vinculada (Conta)
    private List<Transacao> transacoesEmitidas; // Cada conta pode ser vinculada como emissora de várias transações, por isso aqui é uma lista.

    @OneToMany(mappedBy = "contaReceptora")
    private List<Transacao> transacoesRecebidas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta",nullable = false)
    private Long id;

    @Column(name = "numero",length = 20,nullable = false)
    @NotBlank(message = "O campo numero não pode estar vazio.")
    @Pattern(regexp = "(^([0-9]{4,20})-([0-9]{1})$)", message = "Número da conta inválido.")
    private String numero;

    @Column(name = "agencia",length = 5,nullable = false)
    @NotBlank(message = "O campo agencia não pode estar vazio.")
    @Pattern(regexp = "(^([0-9]{4,9})$)",
            message = "Número da agência inválido.")
    private String agencia;

    @Column(name = "data_criacao",columnDefinition = "DATETIME DEFAULT NOW()")
    @NotBlank(message = "O campo data_criacao não pode estar vazio.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataCriacao;

    @Column(name ="senha", length = 255, nullable = false)
    @NotBlank(message = "O campo senha não pode estar vazio.")
    private String senha;

    @Column(name = "saldo", nullable = false, columnDefinition = "BIGINT DEFAULT 0") // Não sei se esse seria assim, fui na lógica do DATETIME
    @NotBlank(message = "O campo saldo não pode estar vazio.")
    @PositiveOrZero(message = "Saldo inválido.")
    private Long saldo;

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
