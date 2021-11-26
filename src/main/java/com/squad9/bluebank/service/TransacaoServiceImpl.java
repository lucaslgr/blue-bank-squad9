package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.TransacaoRequestDTO;
import com.squad9.bluebank.dto.TransacaoResponseDTO;
import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.model.Conta;
import com.squad9.bluebank.model.Transacao;
import com.squad9.bluebank.repository.ClienteRepository;
import com.squad9.bluebank.repository.ContaRepository;
import com.squad9.bluebank.repository.TransacaoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private TransacaoRepository transacaoRepository;
    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;
    private PasswordEncoder passwordEncoder;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository,
                                ContaRepository contaRepository,
                                ClienteRepository clienteRepository,
                                PasswordEncoder passwordEncoder) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void salvar(TransacaoRequestDTO transacaoRequestDTO) throws Exception {

        final Long idContaEmissora = transacaoRequestDTO.getIdContaEmissora();
        final Conta contaEmissora = contaRepository.findById(idContaEmissora).orElseThrow(
                () -> new Exception("Conta emissora inválida.")
        );

        final Long idContaReceptora = transacaoRequestDTO.getIdContaReceptora();
        final Conta contaReceptora = contaRepository.findById(idContaReceptora).orElseThrow(
                () -> new Exception("Conta receptora inválida.")
        );

        if (!passwordEncoder.matches(transacaoRequestDTO.getSenhaContaEmissora(), contaEmissora.getSenha())) {
            throw new Exception("Senha inválida.");
        }

        final Long valorTransacao = transacaoRequestDTO.getValor();
        final Long saldoContaEmissora = contaEmissora.getSaldo();
        if (saldoContaEmissora < valorTransacao) {
            throw new Exception("Saldo insuficiente.");
        }

        if (idContaEmissora.equals(idContaReceptora)) {
            throw new Exception("Transação inválida.");
        }

        contaRepository.updateSaldo(contaEmissora.getId(), saldoContaEmissora - valorTransacao);

        final Long saldoContaReceptora = contaReceptora.getSaldo();
        contaRepository.updateSaldo(contaReceptora.getId(), saldoContaReceptora + valorTransacao);

        final Transacao transacao = new Transacao();
        transacao.setValor(valorTransacao);
        transacao.setDataRecebimento(new Date());
        transacao.setDataEnvio(new Date());
        transacao.setContaReceptora(contaReceptora);
        transacao.setContaEmissora(contaEmissora);
        transacaoRepository.save(transacao);
    }

    @Override
    public List<TransacaoResponseDTO> pegaTransacoesPeloIdDoCliente(Long idCliente) throws Exception {
        final Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new Exception("Cliente inválido."));
        final Conta conta = contaRepository.findByCliente(cliente).orElseThrow(() -> new Exception("Conta inexistente."));
        final List<Transacao> transacoes = transacaoRepository.findAllByIdContaOrdenadoPorDataEnvioDesc(conta.getId());
        return transacoes.stream()
                .map(TransacaoResponseDTO::converter)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransacaoResponseDTO> pegaTodasTransacoesDoBanco() {
        return transacaoRepository.findAllByOrderByDataEnvioDesc().stream()
                .map(TransacaoResponseDTO::converter)
                .collect(Collectors.toList());
    }
}
