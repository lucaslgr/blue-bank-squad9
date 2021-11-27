package com.squad9.bluebank.controller;

import com.squad9.bluebank.dto.ClienteRequestDTO;
import com.squad9.bluebank.dto.ContaRequestDTO;
import com.squad9.bluebank.dto.EnderecoRequestDTO;
import com.squad9.bluebank.dto.TransacaoRequestDTO;
import com.squad9.bluebank.service.ClienteService;
import com.squad9.bluebank.service.ContaService;
import com.squad9.bluebank.service.EnderecoService;
import com.squad9.bluebank.service.TransacaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "api/clientes", produces = "application/json")
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

    @ApiOperation(value = "Cria um clinete")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> criarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(clienteService.salvarCliente(clienteRequestDTO));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }

    }

    @ApiOperation(value = "Retorna dados de um cliente")
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

    @ApiOperation(value = "Atualiza dados de um cliente")
    @PutMapping(value = "/{idCliente}", consumes = "application/json")
    public ResponseEntity<?> atualizarDadosDoCliente(@PathVariable Long idCliente, @RequestBody @Valid ClienteRequestDTO clienteRequestDTO) throws Exception{
        clienteService.atualizarCliente(idCliente,clienteRequestDTO);
        try {
            clienteService.atualizarCliente(idCliente,clienteRequestDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(formataUmRetornoGenerico("sucesso","Cliente atualizado com sucesso"));
        } catch (Exception error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @ApiOperation(value = "Deleta um cliente")
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

    @ApiOperation(value = "Retorna um histórico de transações do cliente")
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

    @ApiOperation(value = "Realiza uma transação entre a conta de um cliente para outra")
    @PostMapping(value = "/{idCliente}/transacao", consumes = "application/json")
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

    @ApiOperation(value = "Cadastra um endereço para um cliente")
    @PostMapping(value = "/{idCliente}/endereco", consumes = "application/json")
    public ResponseEntity<?> cadastrarEndereco(@RequestBody @Valid EnderecoRequestDTO enderecoRequestDTO, @PathVariable Long idCliente) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.enderecoService.salvarEndereco(enderecoRequestDTO, idCliente));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @ApiOperation(value = "Cria uma conta para um cliente")
    @PostMapping(value = "/{idCLiente}/conta", consumes = "application/json")
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
