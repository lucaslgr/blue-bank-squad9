package com.squad9.bluebank.model;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table (name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao",nullable = false)
    private Long id;

    @Column(name = "valor",nullable = false)
    @NotBlank(message = "O campo valor não pode estar vazio")
    @Positive(message = "Valor do campo valor é inválido.")
    private Long valor;

    @ManyToOne
    @NotBlank(message = "O campo id_conta_emissora não pode estar vazio")
    @JoinColumn(name = "id_conta_emissora")
    private Conta contaEmissora;

    @ManyToOne
    @JoinColumn(name = "id_conta_receptora")  // Aqui é o nome do campo que ficará no DB -> Campo SQL.
    private Conta contaReceptora;  // Isto é um campo da transação. Cada transação só tem uma única conta receptora, por isso singular.

    @Column(name = "data_envio", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotBlank(message = "O campo data_envio não pode estar vazio")
    private Date dataEnvio;

    @Column(name = "data_recebimento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotBlank(message = "O campo data_recebimento não pode estar vazio")
    private Date dataRecebimento;

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

