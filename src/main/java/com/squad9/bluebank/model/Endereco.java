package com.squad9.bluebank.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", nullable = false)
    private Long id;

    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP inválido")
    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @NotBlank(message = "O campo logradouro é obrigatório")
    @Column(name = "logradouro", nullable = false, length = 255)
    private String logradouro;

    @NotBlank(message = "O campo bairro é obrigatório")
    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;

    @NotBlank(message = "O campo cidade é obrigatório")
    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Size(min = 2, max = 2, message = "O campo estado deve ter 2 caracteres")
    @Column(name = "estado", nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Pattern(regexp = "^\\d+$", message = "Número da casa inválido")
    @Column(name = "numero_casa", nullable = false, length = 10)
    private String numeroCasa;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @OneToOne(targetEntity = Cliente.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    public Endereco() {}

    public Endereco(String cep, String logradouro, String bairro, String cidade, Estado estado, String numeroCasa, String complemento, Cliente cliente) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numeroCasa = numeroCasa;
        this.complemento = complemento;
        this.cliente = cliente;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNumeroCasa() {
        return this.numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}