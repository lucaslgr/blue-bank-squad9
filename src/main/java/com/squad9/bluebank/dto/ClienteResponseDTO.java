package com.squad9.bluebank.dto;

import com.squad9.bluebank.model.Cliente;

import java.util.Date;

public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private Date dataDeNascimento;
    private String email;
    private String celular;
    private String telefone;
    private String senha;
    private String nomeDoPai;
    private String nomeDaMae;
    private String profissao;
    private Integer rendaMensal;
    private Long patrimonio;

    public static ClienteResponseDTO converter(Cliente cliente) {
        var clienteResponseDTO = new ClienteResponseDTO();

        clienteResponseDTO.setId(cliente.getId());
        clienteResponseDTO.setNome(cliente.getNome());
        clienteResponseDTO.setSobrenome(cliente.getSobrenome());
        clienteResponseDTO.setCpf(cliente.getCpf());
        clienteResponseDTO.setRg(cliente.getRg());
        clienteResponseDTO.setDataDeNascimento(cliente.getDataDeNascimento());
        clienteResponseDTO.setEmail(cliente.getEmail());
        clienteResponseDTO.setCelular(cliente.getCelular());
        clienteResponseDTO.setTelefone(cliente.getTelefone());
        clienteResponseDTO.setSenha(cliente.getSenha());
        clienteResponseDTO.setNomeDoPai(cliente.getNomeDoPai());
        clienteResponseDTO.setNomeDaMae(cliente.getNomeDaMae());
        clienteResponseDTO.setProfissao(cliente.getProfissao());
        clienteResponseDTO.setRendaMensal(cliente.getRendaMensal());
        clienteResponseDTO.setPatrimonio(cliente.getPatrimonio());

        return clienteResponseDTO;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
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

    public String getNomeDoPai() {
        return nomeDoPai;
    }

    public void setNomeDoPai(String nomeDoPai) {
        this.nomeDoPai = nomeDoPai;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
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
