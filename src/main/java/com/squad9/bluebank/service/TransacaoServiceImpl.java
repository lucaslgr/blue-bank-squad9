package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.TransacaoRequestDTO;
import com.squad9.bluebank.dto.TransacaoResponseDTO;
import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.model.Conta;
import com.squad9.bluebank.model.Transacao;
import com.squad9.bluebank.repository.ClienteRepository;
import com.squad9.bluebank.repository.ContaRepository;
import com.squad9.bluebank.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransacaoServiceImpl implements TransacaoService{

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

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

        final Long valorTransacao = transacaoRequestDTO.getValor();
        final Long saldoContaEmissora = contaEmissora.getSaldo();
        if(saldoContaEmissora < valorTransacao) {
            throw new Exception("Saldo insuficiente.");
        }

        if(idContaEmissora.equals(idContaReceptora)) {
            throw new Exception("Transação inválida.");
        }

        contaEmissora.setSaldo(saldoContaEmissora - valorTransacao);
        contaRepository.save(contaEmissora);

        final Long saldoContaReceptora = contaReceptora.getSaldo();
        contaReceptora.setSaldo(saldoContaReceptora + valorTransacao);
        contaRepository.save(contaReceptora);

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
        final Long idConta = contaRepository.findByCliente(cliente).getId();
        final List<Transacao> transacoes = transacaoRepository.findAllByIdContaOrdenadoPorDataEnvioDesc(idConta);
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
