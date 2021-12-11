package com.squad9.bluebank.controller;

import com.squad9.bluebank.dto.*;
import com.squad9.bluebank.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.squad9.bluebank.util.formatadorStringJson.formataUmRetornoGenerico;

@RequestMapping(value = "api/clientes", produces = "application/json")
@RestController
public class ClienteController {
    private ClienteService clienteService;
    private TransacaoService transacaoService;
    private EnderecoService enderecoService;
    private ContaService contaService;
    private SNSService snsService;

    @Autowired
    public ClienteController(
            ClienteService clienteService,
            TransacaoService transacaoService,
            EnderecoService enderecoService,
            ContaService contaService,
            SNSService snsService) {
        this.clienteService = clienteService;
        this.transacaoService = transacaoService;
        this.enderecoService = enderecoService;
        this.contaService = contaService;
        this.snsService = snsService;
    }

    @ApiOperation(value = "Cria um cliente")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> criarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        try {
            final ClienteResponseDTO clienteResponseDTO = clienteService.salvarCliente(clienteRequestDTO);

            //Publicando a mensagem no topico do SNS AWS para informar que um novo cliente foi cadastrado
            snsService.publicaMensagemNoTopicoDeCadastroDeNovosClientes(clienteResponseDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @ApiOperation(value = "Lista todos os clientes")
    @GetMapping
    public ResponseEntity<?> listarTodosClientes() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.pegarTodosClientes());
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginCliente(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws BadCredentialsException {
        try {
            String token = clienteService.loginCliente(loginRequestDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new LoginResponseDTO(token));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @ApiOperation(value = "Retorna dados de um cliente")
    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<?> verDadosDoCliente(@PathVariable Long idCliente, @AuthenticationPrincipal DetalheUsuario detalheUsuario) throws Exception {
        if (!idCliente.equals(detalheUsuario.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

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
    public ResponseEntity<?> atualizarDadosDoCliente(
            @PathVariable Long idCliente,
            @RequestBody @Valid ClienteRequestDTO clienteRequestDTO,
            @AuthenticationPrincipal DetalheUsuario detalheUsuario) throws Exception {
        if (!idCliente.equals(detalheUsuario.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        try {
            String novoToken = clienteService.atualizarCliente(idCliente, clienteRequestDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(formataUmRetornoGenerico("novoToken", novoToken));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @ApiOperation(value = "Deleta um cliente")
    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long idCliente, @AuthenticationPrincipal DetalheUsuario detalheUsuario) throws Exception {
        if (!idCliente.equals(detalheUsuario.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

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
    public ResponseEntity<?> verHistoricoTransacoesDaContaDoCliente(@PathVariable Long idCliente, @AuthenticationPrincipal DetalheUsuario detalheUsuario) {
        if (!idCliente.equals(detalheUsuario.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

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
    public ResponseEntity<?> realizarTransacao(
            @PathVariable Long idCliente,
            @RequestBody @Valid TransacaoRequestDTO transacaoRequestDTO,
            @AuthenticationPrincipal DetalheUsuario detalheUsuario) throws Exception {
        if (!idCliente.equals(detalheUsuario.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
        ClienteResponseDTO cliente = clienteService.encontrarClientePeloId(idCliente);
        final ContaResponseDTO contaEmissora = cliente.getContaResponseDTO();

        if(contaEmissora == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(formataUmRetornoGenerico("error", "Cliente ainda não possui uma conta"));
        }

        if (!contaEmissora.getIdConta().equals(transacaoRequestDTO.getIdContaEmissora())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(formataUmRetornoGenerico("error", "Operação inválida"));
        }

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
    public ResponseEntity<?> cadastrarEndereco(
            @RequestBody @Valid EnderecoRequestDTO enderecoRequestDTO,
            @PathVariable Long idCliente,
            @AuthenticationPrincipal DetalheUsuario detalheUsuario) throws Exception {
        if (!idCliente.equals(detalheUsuario.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.enderecoService.salvarEndereco(enderecoRequestDTO, idCliente));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @ApiOperation(value = "Cria uma conta para um cliente")
    @PostMapping(value = "/{idCliente}/conta", consumes = "application/json")
    public ResponseEntity<?> criarConta(
            @RequestBody @Valid ContaRequestDTO contaRequestDTO,
            @PathVariable Long idCliente,
            @AuthenticationPrincipal DetalheUsuario detalheUsuario) {
        if (!idCliente.equals(detalheUsuario.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(contaService.cadastrarNovaConta(contaRequestDTO, detalheUsuario));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }

    @ApiOperation(value = "Permite o cliente logado realizar um depósito em uma conta, seja dele ou não.")
    @PostMapping(value = "/{idCliente}/deposito", consumes = "application/json")
    public ResponseEntity<?> realizarDepositoNaConta(
            @RequestBody @Valid DepositoRequestDTO depositoRequestDTO,
            @PathVariable Long idCliente,
            @AuthenticationPrincipal DetalheUsuario detalheUsuario
    ) {
        if (!idCliente.equals(detalheUsuario.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        try {
            contaService.realizarDeposito(depositoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(formataUmRetornoGenerico(
                            "msg",
                            "Depósito realizado com sucesso na conta: " + depositoRequestDTO.getNumeroContaDestino())
                    );
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }
}
