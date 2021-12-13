package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.model.Conta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ContaRepositoryTest {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente client = new Cliente();
    private Conta account = new Conta();

    @BeforeEach
    void setUp() {
        client.setSenha("12345678");
        client.setNome("Test");
        client.setSobrenome("Test");
        client.setCpf("254.052.370-60");
        client.setRg("10.582.751-4");
        client.setDataDeNascimento(new Date());
        client.setEmail("Test@email.com");
        client.setCelular("(83) 98888-7777");
        client.setTelefone("(83) 3522-1243");
        client.setNomeDoPai("Father");
        client.setNomeDaMae("Mother");
        client.setProfissao("Developer");
        client.setRendaMensal(5000);
        client.setPatrimonio(10000L);

        this.clienteRepository.save(client);

        account.setSenha("12345678");
        account.setCliente(client);
        account.setNumero("1125385-7");
        account.setAgencia("00001");
        account.setSaldo(10L);
        account.setDataCriacao(new Date());

        this.contaRepository.save(account);
    }

    @AfterEach
    void tearDown() {
        this.contaRepository.deleteAll();
        this.clienteRepository.deleteAll();
    }

    @Test
    void shouldNotBeReturnAccountIfTheClienteNotExists() {
        //given
        Cliente clientTest = new Cliente();
        clientTest.setSenha("12345678");
        clientTest.setNome("Test 2");
        clientTest.setSobrenome("Test 2");
        clientTest.setCpf("411.944.090-75");
        clientTest.setRg("38.745.329-5");
        clientTest.setDataDeNascimento(new Date());
        clientTest.setEmail("test2@email.com");
        clientTest.setCelular("(83) 98888-7777");
        clientTest.setTelefone("(83) 3522-1243");
        clientTest.setNomeDoPai("Father");
        clientTest.setNomeDaMae("Mother");
        clientTest.setProfissao("Developer");
        clientTest.setRendaMensal(5000);
        clientTest.setPatrimonio(10000L);

        this.clienteRepository.save(clientTest);

        //when
        var data = this.contaRepository.findByCliente(clientTest);

        //then
        assertThat(data.isEmpty()).isEqualTo(true);
    }

    @Test
    void shouldBeReturnOneAccountByCliente() {
        //when
        var data = this.contaRepository.findByCliente(client);

        //then
        assertThat(data.get()).hasFieldOrProperty("id");
        assertThat(data.get().getCliente()).isEqualTo(client);
    }

    @Test
    void shouldBeUpdateSaldo() {
        //given
        var accountAlreadyExists = this.contaRepository.findByCliente(client);
        Long finalSaldo = 2000L;
        Long accountId = accountAlreadyExists.get().getId();

        //when
        this.contaRepository.updateSaldo(accountId, finalSaldo);
        //TODO Verificar no banco o saldo
        accountAlreadyExists = this.contaRepository.findByCliente(client);

        //then
        assertThat(accountAlreadyExists.get().getSaldo()).isEqualTo(finalSaldo);
    }

    @Test
    void shouldBeReturnOneAccountByNumero() {
        //given
        String accountNumber = "1125385-7";

        //whem
        var accountAlreadyExists = this.contaRepository.findByNumero(accountNumber);

        //then
        assertThat(accountAlreadyExists.get().getNumero()).isEqualTo(accountNumber);
    }

    @Test
    void shouldNotBeReturnAccountIfNumeroIsInvalid() {
        //given
        String accountNumber = "0278783-0";

        //when
        var data = this.contaRepository.findByNumero(accountNumber);

        //then
        assertThat(data.isEmpty()).isEqualTo(true);
    }
}