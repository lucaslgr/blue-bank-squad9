package com.squad9.bluebank.controller;

import com.squad9.bluebank.dto.ClienteRequestDTO;
import com.squad9.bluebank.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity SalvarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(clienteService.SalvarCliente(clienteRequestDTO));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity PegarTodosOsClientes() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.retornarTodosOsClientes());
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarClientePeloId(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.EncontrarClientePeloId(id));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }
}
