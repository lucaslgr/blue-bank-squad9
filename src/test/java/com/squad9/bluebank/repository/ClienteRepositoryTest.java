package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        Cliente client = new Cliente();
        client.setSenha("12345678");
        client.setNome("Test");
        client.setSobrenome("Test");
        client.setCpf("254.052.370-60");
        client.setRg("10.582.751-4");
        client.setDataDeNascimento(new Date());
        client.setEmail("test@email.com");
        client.setCelular("(83) 98888-7777");
        client.setTelefone("(83) 3522-1243");
        client.setNomeDoPai("Father");
        client.setNomeDaMae("Mother");
        client.setProfissao("Developer");
        client.setRendaMensal(5000);
        client.setPatrimonio(10000L);

        this.clienteRepository.save(client);
    }

    @AfterEach
    void tearDown() {
        this.clienteRepository.deleteAll();
    }

    @Test
    void shouldBeReturnOneClientByCpf() {
        //given
        String cpf = "254.052.370-60";

        //when
        var clientResponse = this.clienteRepository.findByCpf(cpf).get();

        //then
        assertThat(clientResponse).hasFieldOrProperty("id");
        assertThat(clientResponse.getCpf()).isEqualTo(cpf);
    }

    @Test
    void shouldNotBeReturnOneClientIfTheCpfNotExists() {
        //given
        String cpf = "989.896.260-72";

        //when
        var clientResponse = this.clienteRepository.findByCpf(cpf);

        //then
        assertThat(clientResponse.isEmpty()).isEqualTo(true);
    }

    @Test
    void shouldBeReturnOneClientByRg() {
        //given
        String rg = "10.582.751-4";

        //when
        var clientResponse = this.clienteRepository.findByRg(rg).get();

        //then
        assertThat(clientResponse).hasFieldOrProperty("id");
        assertThat(clientResponse.getRg()).isEqualTo(rg);
    }

    @Test
    void shouldNotBeReturnOneClientIfTheRgNotExists() {
        //given
        String rg = "32.971.902-6";

        //when
        var clientResponse = this.clienteRepository.findByRg(rg);

        //then
        assertThat(clientResponse.isEmpty()).isEqualTo(true);
    }

    @Test
    void shouldBeReturnOneClientByEmail() {
        //given
        String email = "test@email.com";

        //when
        var clientResponse = this.clienteRepository.findByEmail(email).get();

        //then
        assertThat(clientResponse).hasFieldOrProperty("id");
        assertThat(clientResponse.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldNotBeReturnOneClientIfTheEmailNotExists() {
        //given
        String email = "sebastiaorafaelcosta@vitaonline.com.br";

        //when
        var clientResponse = this.clienteRepository.findByEmail(email);

        //then
        assertThat(clientResponse.isEmpty()).isEqualTo(true);
    }

    @Test
    void findByEmailExceptById() {
        Cliente client = new Cliente();
        client.setSenha("12345678");
        client.setNome("Test");
        client.setSobrenome("Test");
        client.setCpf("162.769.540-04");
        client.setRg("20.998.486-7");
        client.setDataDeNascimento(new Date());
        client.setEmail("testTest@email.com");
        client.setCelular("(83) 98888-7777");
        client.setTelefone("(83) 3522-1243");
        client.setNomeDoPai("Father");
        client.setNomeDaMae("Mother");
        client.setProfissao("Developer");
        client.setRendaMensal(5000);
        client.setPatrimonio(10000L);

        this.clienteRepository.save(client);

        //given
        String email = "testTest@email.com";
        Long id = this.clienteRepository.findByCpf(client.getCpf()).get().getId();

        var expectedClient = this.clienteRepository
                .findByEmailExceptById(email, id);

        assertThat(expectedClient.isEmpty()).isEqualTo(true);
    }

    @Test
    void findByEmailExceptByIdWhenTheEmailAlreadyExists() {
        Cliente client = new Cliente();
        client.setSenha("12345678");
        client.setNome("Test");
        client.setSobrenome("Test");
        client.setCpf("162.769.540-04");
        client.setRg("20.998.486-7");
        client.setDataDeNascimento(new Date());
        client.setEmail("testTest@email.com");
        client.setCelular("(83) 98888-7777");
        client.setTelefone("(83) 3522-1243");
        client.setNomeDoPai("Father");
        client.setNomeDaMae("Mother");
        client.setProfissao("Developer");
        client.setRendaMensal(5000);
        client.setPatrimonio(10000L);

        this.clienteRepository.save(client);

        //given
        String email = "testTest@email.com";
        String cpf = "254.052.370-60";

        Long id = this.clienteRepository.findByCpf(cpf).get().getId();

        var expectedClient = this.clienteRepository
                .findByEmailExceptById(email, id);

        assertThat(expectedClient.get()).isEqualTo(client);
    }
}