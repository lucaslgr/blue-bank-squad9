package com.squad9.bluebank.controller;

import com.squad9.bluebank.dto.ClienteRequestDTO;
import com.squad9.bluebank.dto.ContaRequestDTO;
import com.squad9.bluebank.dto.EnderecoRequestDTO;
import com.squad9.bluebank.dto.TransacaoRequestDTO;
import com.squad9.bluebank.service.ClienteService;
import com.squad9.bluebank.service.ContaService;
import com.squad9.bluebank.service.EnderecoService;
import com.squad9.bluebank.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/clientes")
@RestController
public class ClienteController {
    private ClienteService clienteService;
    private TransacaoService transacaoService;
    private EnderecoService enderecoService;
    private ContaService contaService;

    @Autowired
    public ClienteController(
            ClienteService clienteService,
            TransacaoService transacaoService,
            EnderecoService enderecoService,
            ContaService contaService) {
        this.clienteService = clienteService;
        this.transacaoService = transacaoService;
        this.enderecoService = enderecoService;
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(clienteService.salvarCliente(clienteRequestDTO));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }

    }

    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<?> verDadosDoCliente(@PathVariable Long idCliente) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(clienteService.encontrarClientePeloId(idCliente));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }
//
//    @PutMapping(value = "/{idCliente}")
//    public void atualizarDadosDoCliente(@PathVariable Long idCliente) throws Exception{
//        //Apagar comentário e fazer implementação
//    }

    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long idCliente) throws Exception {
        try {
            clienteService.deletarCliente(idCliente);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(formataUmRetornoGenerico("sucesso", "Cliente deletado com sucesso"));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @GetMapping(value = "/{idCliente}/transacoes")
    public ResponseEntity<?> verHistoricoTransacoesDaContaDoCliente(@PathVariable Long idCliente) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(transacaoService.pegaTransacoesPeloIdDoCliente(idCliente));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @PostMapping(value = "/{idCliente}/transacao")
    public ResponseEntity<?> realizarTransacao(@PathVariable Long idCliente, @RequestBody @Valid TransacaoRequestDTO transacaoRequestDTO) throws Exception {
        try {
            transacaoService.salvar(transacaoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(formataUmRetornoGenerico("sucesso", "Transação bem sucedida"));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @PostMapping(value = "/{idCliente}/endereco")
    public ResponseEntity<?> cadastrarEndereco(@RequestBody @Valid EnderecoRequestDTO enderecoRequestDTO, @PathVariable Long idCliente) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.enderecoService.salvarEndereco(enderecoRequestDTO, idCliente));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @PostMapping(value = "/{idCLiente}/conta")
    public ResponseEntity<?> criarConta(@RequestBody @Valid ContaRequestDTO contaRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(contaService.cadastrarNovaConta(contaRequestDTO));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    private Map<String, String> formataUmRetornoGenerico(String topico, String msg) {
        Map<String, String> error = new HashMap<>();
        error.put(topico, msg);
        return error;
    }
}
