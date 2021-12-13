package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.model.Conta;
import com.squad9.bluebank.model.Transacao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class TransacaoRepositoryTest {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente clientSender = new Cliente();
    private Cliente clientReceiver = new Cliente();

    private Conta accountSender = new Conta();
    private Conta accountReceiver = new Conta();

    private Transacao primeiraTransacao = new Transacao();
    private Transacao segundaTransacao = new Transacao();
    private Transacao terceiraTransacao = new Transacao();

    @BeforeEach
    void setUp() {
        clientSender.setSenha("12345678");
        clientSender.setNome("Sender");
        clientSender.setSobrenome("Sender");
        clientSender.setCpf("254.052.370-60");
        clientSender.setRg("10.582.751-4");
        clientSender.setDataDeNascimento(new Date());
        clientSender.setEmail("Sender@email.com");
        clientSender.setCelular("(83) 98888-7777");
        clientSender.setTelefone("(83) 3522-1243");
        clientSender.setNomeDoPai("Father Sender");
        clientSender.setNomeDaMae("Mother Sender");
        clientSender.setProfissao("Developer");
        clientSender.setRendaMensal(5000);
        clientSender.setPatrimonio(10000L);

        clientReceiver.setSenha("12345678");
        clientReceiver.setNome("Receiver");
        clientReceiver.setSobrenome("Receiver");
        clientReceiver.setCpf("001.658.730-86");
        clientReceiver.setRg("34.365.816-1");
        clientReceiver.setDataDeNascimento(new Date());
        clientReceiver.setEmail("Receiver@email.com");
        clientReceiver.setCelular("(83) 98888-7777");
        clientReceiver.setTelefone("(83) 3522-1243");
        clientReceiver.setNomeDoPai("Father Receiver");
        clientReceiver.setNomeDaMae("Mother Receiver");
        clientReceiver.setProfissao("Developer");
        clientReceiver.setRendaMensal(5000);
        clientReceiver.setPatrimonio(10000L);

        this.clienteRepository.save(clientSender);
        this.clienteRepository.save(clientReceiver);

        accountSender.setSenha("12345678");
        accountSender.setNumero("1125385-7");
        accountSender.setCliente(clientSender);
        accountSender.setAgencia("00001");
        accountSender.setSaldo(50000L);
        accountSender.setDataCriacao(new Date());

        accountReceiver.setSenha("12345678");
        accountReceiver.setNumero("46483088-3");
        accountReceiver.setCliente(clientReceiver);
        accountReceiver.setAgencia("00001");
        accountReceiver.setSaldo(100000L);
        accountReceiver.setDataCriacao(new Date());

        this.contaRepository.save(accountSender);
        this.contaRepository.save(accountReceiver);

        primeiraTransacao.setContaEmissora(accountSender);
        primeiraTransacao.setContaReceptora(accountReceiver);
        primeiraTransacao.setValor(500L);

        segundaTransacao.setContaEmissora(accountSender);
        segundaTransacao.setContaReceptora(accountReceiver);
        segundaTransacao.setValor(5000L);

        terceiraTransacao.setContaEmissora(accountSender);
        terceiraTransacao.setContaReceptora(accountReceiver);
        terceiraTransacao.setValor(10000L);

        this.transacaoRepository.save(primeiraTransacao);
        this.transacaoRepository.save(segundaTransacao);
        this.transacaoRepository.save(terceiraTransacao);
    }

    @AfterEach
    void tearDown() {
        this.transacaoRepository.deleteAll();
        this.contaRepository.deleteAll();
        this.clienteRepository.deleteAll();
    }

    //check
    @Test
    void shouldBeAbleToListAllTransactionsByIdContaOrderByDesc() {
        //given
        String accountNumber = "1125385-7";
        var account = this.contaRepository.findByNumero(accountNumber);

        //when
        var transactions = this.transacaoRepository
                .findAllByIdContaOrdenadoPorDataEnvioDesc(account.get().getId());

        //then
        //setar data
        assertThat(transactions.size()).isEqualTo(3);
        assertThat(transactions.get(0)).isEqualTo(primeiraTransacao);
        assertThat(transactions.get(1)).isEqualTo(segundaTransacao);
        assertThat(transactions.get(2)).isEqualTo(terceiraTransacao);
    }

    @Test
    void shouldNotBeAbleToListAllTransactionsByIdContaIfContaNotExists() {
        //given
        String accountNumber = "164791-1";
        var account = this.contaRepository.findByNumero(accountNumber);

        //then
        assertThatThrownBy(() -> this.transacaoRepository
                .findAllByIdContaOrdenadoPorDataEnvioDesc(account.get().getId()))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void findAllByOrderByDataEnvioDesc() {
        var transactions = this.transacaoRepository.findAllByOrderByDataEnvioDesc();

        assertThat(transactions.size()).isEqualTo(3);
        assertThat(transactions.get(0)).isEqualTo(primeiraTransacao);
        assertThat(transactions.get(1)).isEqualTo(segundaTransacao);
        assertThat(transactions.get(2)).isEqualTo(terceiraTransacao);
    }

    @Test
    void deleteByContaReceptora() {
        //given
        String accountNumber = "46483088-3";
        var account = this.contaRepository.findByNumero(accountNumber);

        //when
        this.transacaoRepository.deleteByContaReceptora(account.get());

        //then
        var finalTransactions = this.transacaoRepository
                .findAllByIdContaOrdenadoPorDataEnvioDesc(account.get().getId());

        assertThat(finalTransactions.size()).isEqualTo(0);
    }

    @Test
    void deleteByContaEmissora() {
        //given
        String accountNumber = "1125385-7";
        var account = this.contaRepository.findByNumero(accountNumber);

        //when
        this.transacaoRepository.deleteByContaEmissora(account.get());

        //then
        var finalTransactions = this.transacaoRepository
                .findAllByIdContaOrdenadoPorDataEnvioDesc(account.get().getId());

        assertThat(finalTransactions.size()).isEqualTo(0);
    }
}