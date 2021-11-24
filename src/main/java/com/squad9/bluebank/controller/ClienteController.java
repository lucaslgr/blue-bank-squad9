package com.squad9.bluebank.controller;

import com.squad9.bluebank.dto.*;
import com.squad9.bluebank.model.Cliente;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @PostMapping
    private ResponseEntity<String> criarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO){
        return ResponseEntity.ok("passou1");
    }

//    @GetMapping
//    public List<ClienteResponseDTO> listarTodosClientes() throws Exception{
//        //Apagar comentário e fazer implementação
//    }
//
//    @GetMapping(value = "/{idCliente}")
//    public ClienteResponseDTO verDadosDoCliente(@PathVariable Long idCliente) throws Exception{
//        //Apagar comentário e fazer implementação
//    }

    @PutMapping(value = "/{idCliente}")
    public void atualizarDadosDoCliente(@PathVariable Long idCliente) throws Exception{
        //Apagar comentário e fazer implementação
    }

    @DeleteMapping
    public void deletarCliente(@PathVariable Long idCliente) throws Exception{
        //Apagar comentário e fazer implementação
    }

//    @GetMapping(value = "/{idCliente}/transacoes")
//    public List<TransacaoResponseDTO> verHistoricoTransacoesDaContaDoCliente(@PathVariable Long idCliente) throws Exception{
//        //Apagar comentário e fazer implementação
//    }

    @PostMapping(value = "/{idCliente}/transacao")
    public void realizarTransacao(@PathVariable Long idCliente, @RequestBody TransacaoRequestDTO transacaoRequestDTO) throws Exception{
        //Apagar comentário e fazer implementação
    }

    @PostMapping(value = "/{idCliente}/endereco")
    public void cadastrarEndereco(@PathVariable Long idCliente, @RequestBody EnderecoRequestDTO enderecoRequestDTO) throws Exception{
        //Apagar comentário e fazer implementação
    }
}
