package com.squad9.bluebank.controller;

import com.squad9.bluebank.dto.*;
import com.squad9.bluebank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/cliente")
@RestController
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity criarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(clienteService.salvarCliente(clienteRequestDTO));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
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
    public void realizarTransacao(@PathVariable Long idCliente, @RequestBody TransacaoRequestDTO transacaoRequestDTO) throws Exception{
        //Apagar comentário e fazer implementação
    }

    @PostMapping(value = "/{idCliente}/endereco")
    public void cadastrarEndereco(@PathVariable Long idCliente, @RequestBody EnderecoRequestDTO enderecoRequestDTO) throws Exception{
        //Apagar comentário e fazer implementação
    }
}