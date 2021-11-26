package com.squad9.bluebank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequestDTO {

    @NotBlank(message = "O campo email não pode estar vazio.")
    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "O campo senha não pode estar vazio.")
    @Size(min = 6, message = "A senha deve conter pelo menos 6 caracteres.")
    private String senha;
    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
