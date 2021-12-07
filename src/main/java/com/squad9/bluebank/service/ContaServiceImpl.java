package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ContaRequestDTO;
import com.squad9.bluebank.dto.ContaResponseDTO;
import com.squad9.bluebank.dto.DepositoRequestDTO;
import com.squad9.bluebank.model.Conta;
import com.squad9.bluebank.repository.ClienteRepository;
import com.squad9.bluebank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;

import javax.validation.Valid;

@Service
public class ContaServiceImpl implements ContaService {

    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;
    private PasswordEncoder passwordEncoder;
    
    public ResponseEntity<?> criarConta(
        @RequestBody @Valid ContaRequestDTO contaRequestDTO,
        @PathVariable Long idCLiente,
        @AuthenticationPrincipal DetalheUsuario detalheUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(ContaService.cadastrarNovaConta(contaRequestDTO, detalheUsuario));
        }
    )

    @Autowired
    public ContaServiceImpl(ContaRepository contaRepository, ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final String numeroAgencia = "00001";

    //Cadastra um nova conta
    @Override
    public ContaResponseDTO cadastrarNovaConta(ContaRequestDTO contaRequestDTO) throws Exception {
        // Verificar se a pessoa já existe pelo id
        var cliente = clienteRepository.findByCpf(contaRequestDTO.getCpf()).orElseThrow(() -> new Exception("Cliente não existe"));

        //Verificar se o cliente já possui conta
        if (contaRepository.findByCliente(cliente).isPresent()) {
            throw new Exception("Cliente já possui conta");
        }

        // Verificar se o cpf para cadastro da conta é o mesmo do ID do cliente
        var email = detalheUsuario.getUsername();
        var userCliente = clienteRepository.findByEmail(email);
        var cpfUsuario =  userCliente.get().getCpf();
        
        if (!cpfUsuario.equals(contaRequestDTO.getCpf())) {
            throw new Exception("CPF digitado não é o mesmo do cliente");
        }

        // Gerar número da conta
        String stringNumeroConta = geraNumeroConta(cliente.getId());

        Conta conta = new Conta();
        conta.setSenha(passwordEncoder.encode(contaRequestDTO.getSenha())); // Criptografar senha
        conta.setCliente(cliente);
        conta.setNumero(stringNumeroConta);
        conta.setAgencia(numeroAgencia);

        this.contaRepository.save(conta);
        return ContaResponseDTO.converter(conta);
    }

    //Retorna os dados da conta
    @Override
    public ContaResponseDTO retornaDadosDaConta(Long idConta) throws Exception {
        var conta = contaRepository.findById(idConta).orElseThrow(() -> new Exception("Conta inválida"));
        return ContaResponseDTO.converter(conta);

    }

    @Override
    public void realizarDeposito(DepositoRequestDTO depositoRequestDTO) throws Exception {
        final String numeroContaDestino = depositoRequestDTO.getNumeroContaDestino();
        var conta = contaRepository.findByNumero(numeroContaDestino).orElseThrow(() -> new Exception("Conta inválida."));

        final Long novoSaldoDaConta = conta.getSaldo() + depositoRequestDTO.getValor();
        contaRepository.updateSaldo(conta.getId(), novoSaldoDaConta);
    }

    private String geraNumeroConta(Long idCliente) {
        Long numeroConta = 10000 + idCliente;
        Integer digito = (new Random()).nextInt(10);
        return (numeroConta.toString() + "-" + digito.toString());
    }
}
