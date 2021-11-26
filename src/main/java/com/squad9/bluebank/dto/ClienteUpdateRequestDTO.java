package com.squad9.bluebank.dto;

import javax.validation.constraints.*;

public class ClienteUpdateRequestDTO {
    @Size(min = 2, max = 100, message = "Nome inválido.")
    private String nome;

    private String sobrenome;

    @Email(message = "Email inválido.")
    private String email;

    @Pattern(
            regexp = "(^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$)",
            message = "Celular inválido."
    )
    private String celular;

    @Pattern(
            regexp = "(^\\([1-9]{2}\\) [0-9]{4}\\-[0-9]{4}$)",
            message = "Telefone inválido."
    )
    private String telefone;

    @Size(min = 6, message = "A senha deve conter pelo menos 6 caracteres.")
    private String senha;

    private String profissao;

    @Positive(message = "Valor de renda mensal inválido.")
    private Integer rendaMensal;

    @PositiveOrZero(message = "Valor de patrimônio mensal inválido.")
    private Long patrimonio;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Integer getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(Integer rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public Long getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Long patrimonio) {
        this.patrimonio = patrimonio;
    }
}
