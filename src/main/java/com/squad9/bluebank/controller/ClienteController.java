package com.squad9.bluebank.controller;

import com.squad9.bluebank.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/cliente")
@RestController
public class ClienteController {

    @PostMapping
    public void criarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        //Apagar comentário e fazer implementação
    }

    @GetMapping
    public List<ClienteResponseDTO> listarTodosClientes() {
        //Apagar comentário e fazer implementação
    }

    @GetMapping(value = "/{idCliente}")
    public ClienteResponseDTO verDadosDoCliente(@PathVariable Long idCliente) {
        //Apagar comentário e fazer implementação
    }

    @PutMapping(value = "/{idCliente}")
    public void atualizarDadosDoCliente(@PathVariable Long idCliente) {
        //Apagar comentário e fazer implementação
    }

    @DeleteMapping
    public void deletarCliente(@PathVariable Long idCliente) {
        //Apagar comentário e fazer implementação
    }

    @GetMapping(value = "/transacoes/{idCliente}")
    public List<TransacaoResponseDTO> verHistoricoTransacoesDaContaDoCliente(@PathVariable Long idCliente) {
        //Apagar comentário e fazer implementação
    }

    @PostMapping(value = "/transacao/{idCliente}")
    public void realizarTransacao(@PathVariable Long idCliente, @RequestBody TransacaoRequestDTO transacaoRequestDTO) {
        //Apagar comentário e fazer implementação
    }

    @PostMapping(value = "/endereco/{idCliente}")
    public void cadastrarEndereco(@PathVariable Long idCliente, @RequestBody EnderecoRequestDTO enderecoRequestDTO){
        //Apagar comentário e fazer implementação
    }
}
