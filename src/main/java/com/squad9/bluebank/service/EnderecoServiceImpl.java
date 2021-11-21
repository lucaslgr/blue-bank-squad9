package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.EnderecoRequestDTO;
import com.squad9.bluebank.dto.EnderecoResponseDTO;
import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.model.Endereco;
import com.squad9.bluebank.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EnderecoServiceImpl implements EnderecoService{

    @Autowired
    private EnderecoRepository enderecoRepository;


    //Cadastrar novo endereço.
    public EnderecoResponseDTO salvarEndereco(EnderecoRequestDTO enderecoRequestDTO, Cliente cliente) { // Coloquei "Cliente cliente" porque precisa indentificar o endereço do cliente.
        var endereco = new Endereco(enderecoRequestDTO.getCep(), enderecoRequestDTO.getLogradouro(), enderecoRequestDTO.getBairro(), enderecoRequestDTO.getCidade(), enderecoRequestDTO.getEstado(), enderecoRequestDTO.getNumeroCasa(), enderecoRequestDTO.getComplemento(),cliente);

        this.enderecoRepository.save(endereco);
        return EnderecoResponseDTO.converter(endereco);
    }

    //Retornar dados do endereço.
    public EnderecoResponseDTO retornarEnderecoPeloId(Long id) throws Exception{
        var endereco = this.enderecoRepository.findById(id).orElseThrow(()->new Exception("Endereço não encontrado"));
        return EnderecoResponseDTO.converter(endereco);
    }

    //Atualizar endereço
    public void atualizarEndereco(Long id,EnderecoRequestDTO enderecoRequestDTO)throws Exception{
        var endereco = this.enderecoRepository.findById(id).orElseThrow(()->new Exception("Endereço não encontrado"));

        endereco.setCep(enderecoRequestDTO.getCep());
        endereco.setLogradouro(enderecoRequestDTO.getLogradouro());
        endereco.setBairro(enderecoRequestDTO.getBairro());
        endereco.setCidade(enderecoRequestDTO.getCidade());
        endereco.setEstado(enderecoRequestDTO.getEstado());
        endereco.setNumeroCasa(enderecoRequestDTO.getNumeroCasa());
        endereco.setComplemento(enderecoRequestDTO.getComplemento());

        this.enderecoRepository.save(endereco);
    }

}


