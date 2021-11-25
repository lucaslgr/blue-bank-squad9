package com.squad9.bluebank.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ContaRequestDTO {

    @NotBlank(message = "O campo cpf não pode estar vazio.")
    @CPF(message = "CPF inválido.")
    private String cpf;

    @NotBlank(message = "O campo senha não pode estar vazio.")
    @Size(min = 6, message = "A senha deve conter pelo menos 6 caracteres.")
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
