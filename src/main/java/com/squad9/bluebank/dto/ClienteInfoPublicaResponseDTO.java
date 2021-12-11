package com.squad9.bluebank.dto;

import com.squad9.bluebank.model.Cliente;

import java.util.Date;

public class ClienteInfoPublicaResponseDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;

    public static ClienteInfoPublicaResponseDTO converter(Cliente cliente) {
        final var clienteInfoPublicaResponseDTO = new ClienteInfoPublicaResponseDTO();
        clienteInfoPublicaResponseDTO.setId(cliente.getId());
        clienteInfoPublicaResponseDTO.setNome(cliente.getNome());
        clienteInfoPublicaResponseDTO.setSobrenome(cliente.getSobrenome());
        clienteInfoPublicaResponseDTO.setEmail(cliente.getEmail());

        return clienteInfoPublicaResponseDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "ClienteInfoPublicaResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
