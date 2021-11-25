package com.squad9.bluebank.dto;

import com.squad9.bluebank.model.Estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EnderecoRequestDTO {

    @NotBlank(message = "O campo cep não pode estar vazio.")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP inválido")
    private String cep;

    @NotBlank(message = "O campo logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "O campo bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "O campo cidade é obrigatório")
    private String cidade;

    @NotNull(message = "O campo estado é obrigatório")
    private Estado estado;

    @NotBlank(message = "O campo numeroCasa é obrigatório")
    @Pattern(regexp = "^\\d+$", message = "Número da casa inválido")
    private String numeroCasa;

    private String complemento;

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
}
