package com.squad9.bluebank.model;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Entity
@Table (name = "transacoes")
public class Transacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long id;

    @Column(name = "valor")
    @NotBlank(message = "O campo valor não pode estar vazio")
    @PositiveOrZero
    private Long valor;

    @ManyToOne
    @NotBlank(message = "O campo id_conta_emissora não pode estar vazio")
    @JoinColumn(name = "id_conta_emissora_fk")
    private Conta id_conta_emissora;

    @ManyToOne
    @JoinColumn(name = "id_conta_receptora_fk")
    private Conta id_conta_receptora;

    @Column(name = "data_envio")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotBlank(message = "O campo data_envio não pode estar vazio")
    private Date data_envio;

    @Column(name = "data_recebimento")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotBlank(message = "O campo data_recebimento não pode estar vazio")
    private Date data_recebimento;

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

    public Conta getId_conta_emissora() {
        return id_conta_emissora;
    }

    public void setId_conta_emissora(Conta id_conta_emissora) {
        this.id_conta_emissora = id_conta_emissora;
    }

    public Conta getId_conta_receptora() {
        return id_conta_receptora;
    }

    public void setId_conta_receptora(Conta id_conta_receptora) {
        this.id_conta_receptora = id_conta_receptora;
    }

    public Date getData_envio() {
        return data_envio;
    }

    public void setData_envio(Date data_envio) {
        this.data_envio = data_envio;
    }

    public Date getData_recebimento() {
        return data_recebimento;
    }

    public void setData_recebimento(Date data_recebimento) {
        this.data_recebimento = data_recebimento;
    }
}
