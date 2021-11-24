package com.squad9.bluebank.controller;

import com.squad9.bluebank.dto.*;
import com.squad9.bluebank.service.TransacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RequestMapping("api/cliente")
@RestController
public class ClienteController {

    private TransacaoService transacaoService;

    public ClienteController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public void criarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) throws Exception{
        //Apagar comentário e fazer implementação
    }

    @GetMapping
    public List<ClienteResponseDTO> listarTodosClientes() throws Exception{
        //Apagar comentário e fazer implementação
    }

    @GetMapping(value = "/{idCliente}")
    public ClienteResponseDTO verDadosDoCliente(@PathVariable Long idCliente) throws Exception{
        //Apagar comentário e fazer implementação
    }

    @PutMapping(value = "/{idCliente}")
    public void atualizarDadosDoCliente(@PathVariable Long idCliente) throws Exception{
        //Apagar comentário e fazer implementação
    }

    @DeleteMapping
    public void deletarCliente(@PathVariable Long idCliente) throws Exception{
        //Apagar comentário e fazer implementação
    }

    @GetMapping(value = "/{idCliente}/transacoes")
    public List<TransacaoResponseDTO> verHistoricoTransacoesDaContaDoCliente(@PathVariable Long idCliente) throws Exception{
        //Apagar comentário e fazer implementação
    }

    @PostMapping(value = "/{idCliente}/transacao")
    public ResponseEntity<String> realizarTransacao(@PathVariable Long idCliente, @RequestBody @Valid TransacaoRequestDTO transacaoRequestDTO) throws Exception{
        try {
            transacaoService.salvar(transacaoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Transação bem sucedida");
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @PostMapping(value = "/{idCliente}/endereco")
    public void cadastrarEndereco(@PathVariable Long idCliente, @RequestBody EnderecoRequestDTO enderecoRequestDTO) throws Exception{
        //Apagar comentário e fazer implementação
    }
}
