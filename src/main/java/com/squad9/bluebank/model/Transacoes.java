package com.squad9.bluebank.model;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table (name = "transacoes")
public class Transacoes {

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
    private Conta contasEmissoras;

    @ManyToOne
    @JoinColumn(name = "id_conta_receptora")
    private Conta contasReceptoras;

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

    public Conta getContasEmissoras() {
        return contasEmissoras;
    }

    public void setContasEmissoras(Conta contasEmissoras) {
        this.contasEmissoras = contasEmissoras;
    }

    public Conta getContasReceptoras() {
        return contasReceptoras;
    }

    public void setContasReceptoras(Conta contasReceptoras) {
        this.contasReceptoras = contasReceptoras;
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

