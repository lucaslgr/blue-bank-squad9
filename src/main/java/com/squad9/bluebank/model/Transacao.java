package com.squad9.bluebank.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao", nullable = false)
    private Long id;

    @Column(name = "valor", nullable = false)
    @NotNull(message = "O campo valor não pode estar vazio")
    @Positive(message = "Valor do campo valor é inválido.")
    private Long valor;

    @Column(name = "data_envio", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Date dataEnvio;

    @Column(name = "data_recebimento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Date dataRecebimento;

    @ManyToOne
    @JoinColumn(name = "id_conta_emissora")
    @NotNull(message = "O campo contaEmissora não pode estar vazio")
    private Conta contaEmissora;

    @ManyToOne
    @JoinColumn(name = "id_conta_receptora")
    @NotNull(message = "O campo contaReceptora não pode estar vazio")
    private Conta contaReceptora;

    public Long getId() {
        return id;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Conta getContaEmissora() {
        return contaEmissora;
    }

    public void setContaEmissora(Conta contaEmissora) {
        this.contaEmissora = contaEmissora;
    }

    public Conta getContaReceptora() {
        return contaReceptora;
    }

    public void setContaReceptora(Conta contaReceptora) {
        this.contaReceptora = contaReceptora;
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

