package com.squad9.bluebank.service;

import com.squad9.bluebank.dto.ClienteRequestDTO;
import com.squad9.bluebank.dto.ClienteResponseDTO;
import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.repository.ClienteRepository;
import com.squad9.bluebank.repository.ContaRepository;
import com.squad9.bluebank.repository.EnderecoRepository;
import com.squad9.bluebank.repository.TransacaoRepository;
import com.squad9.bluebank.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ContaRepository contaRepository;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private TransacaoRepository transacaoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    void setUp() {
        clienteService = new ClienteServiceImpl(
                clienteRepository,
                contaRepository,
                enderecoRepository,
                transacaoRepository,
                passwordEncoder,
                authenticationManager,
                jwtTokenUtil
        );
    }

    @Test
    void shouldBeAbleToSaveClient() throws Exception {
        //given
        ClienteRequestDTO client = new ClienteRequestDTO();
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

        this.clienteService.salvarCliente(client);

        //then
        ArgumentCaptor<Cliente> clienteArgumentCaptor =
                ArgumentCaptor.forClass(Cliente.class);

        Mockito.verify(clienteRepository).save(clienteArgumentCaptor.capture());

        var capturedClient = clienteArgumentCaptor.getValue();

        assertThat(capturedClient.getCpf()).isEqualTo(client.getCpf());
        assertThat(capturedClient.getRg()).isEqualTo(client.getRg());
        assertThat(capturedClient.getEmail()).isEqualTo(client.getEmail());
    }

    @Test
    void shouldNotBeAbleToSaveClientIfCpfAlreadyExists() {
        //given
        Cliente client = new Cliente();
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

        ClienteRequestDTO newClient = new ClienteRequestDTO();
        newClient.setSenha("12345678");
        newClient.setNome("Test");
        newClient.setSobrenome("Test");
        newClient.setCpf("254.052.370-60");
        newClient.setRg("10.582.751-4");
        newClient.setDataDeNascimento(new Date());
        newClient.setEmail("Test@email.com");
        newClient.setCelular("(83) 98888-7777");
        newClient.setTelefone("(83) 3522-1243");
        newClient.setNomeDoPai("Father");
        newClient.setNomeDaMae("Mother");
        newClient.setProfissao("Developer");
        newClient.setRendaMensal(5000);
        newClient.setPatrimonio(10000L);

        assertThatThrownBy(() -> this.clienteService.salvarCliente(newClient))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Cliente com CPF já cadastrado!");
    }

    @Test
    @Disabled
    void loginCliente() {
    }

    @Test
    @Disabled
    void retornarTodosOsClientes() {
    }

    @Test
    @Disabled
    void encontrarClientePeloId() {
    }

    @Test
    @Disabled
    void atualizarCliente() {
    }

    @Test
    @Disabled
    void deletarCliente() {
    }
}