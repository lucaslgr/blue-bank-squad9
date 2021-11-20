package com.squad9.bluebank.dto;

import com.squad9.bluebank.model.Endereco;
import com.squad9.bluebank.model.Estado;

public class EnderecoResponseDTO {
    
    private Long id;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private Estado estado;
    private String numeroCasa;
    private String complemento;

    public static EnderecoResponseDTO converter(Endereco endereco) {
        var enderecoResponseDTO = new EnderecoResponseDTO();

        enderecoResponseDTO.setId(endereco.getId());
        enderecoResponseDTO.setCep(endereco.getCep());
        enderecoResponseDTO.setLogradouro(endereco.getLogradouro());
        enderecoResponseDTO.setBairro(endereco.getBairro());
        enderecoResponseDTO.setCidade(endereco.getCidade());
        enderecoResponseDTO.setEstado(endereco.getEstado());
        enderecoResponseDTO.setNumeroCasa(endereco.getNumeroCasa());
        enderecoResponseDTO.setComplemento(endereco.getComplemento());

        return enderecoResponseDTO;
    }

    public Long getId() {
        return id;
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

}
