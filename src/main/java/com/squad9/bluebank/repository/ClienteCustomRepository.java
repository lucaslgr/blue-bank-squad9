package com.squad9.bluebank.repository;

import com.squad9.bluebank.dto.ClienteUpdateRequestDTO;
import com.squad9.bluebank.model.Cliente;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteCustomRepository {

    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public ClienteCustomRepository(EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void atualizaCliente(Long idCliente, ClienteUpdateRequestDTO clienteUpdateRequestDTO) {
        String queryStr = "Update C from Cliente Set ";
        String queryWhere = " Where C.id = :idCliente";
        List<String> params = new ArrayList<>();
        String senha = "";

        if (clienteUpdateRequestDTO.getNome() != null) {
            params.add("C.nome = :nome");
        }
        if (clienteUpdateRequestDTO.getSobrenome() != null) {
            params.add("C.sobrenome = :sobrenome");
        }
        if (clienteUpdateRequestDTO.getEmail() != null) {
            //TODO Verificar se o email já não pertence a outro cliente
            params.add("C.email = :email");
        }
        if (clienteUpdateRequestDTO.getCelular() != null) {
            params.add("C.celular = :celular");
        }
        if (clienteUpdateRequestDTO.getTelefone() != null) {
            params.add("C.telefone = :telefone");
        }
        if (clienteUpdateRequestDTO.getSenha() != null) {
            senha = passwordEncoder.encode(clienteUpdateRequestDTO.getSenha());
            params.add("C.senha = :senha");
        }
        if (clienteUpdateRequestDTO.getProfissao() != null) {
            params.add("C.profissao = :profissao");
        }
        if (clienteUpdateRequestDTO.getRendaMensal() != null) {
            params.add("C.rendaMensal = :rendaMensal");
        }
        if (clienteUpdateRequestDTO.getPatrimonio() != null) {
            params.add("C.patrimonio = :patrimonio");
        }

        queryStr += String.join(", ", params) + queryWhere;
        final Query query = entityManager.createQuery(queryStr, Cliente.class);

        if (clienteUpdateRequestDTO.getNome() != null) {
            query.setParameter("nome", clienteUpdateRequestDTO.getNome());
        }
        if (clienteUpdateRequestDTO.getSobrenome() != null) {
            query.setParameter("sobrenome", clienteUpdateRequestDTO.getSobrenome());
        }
        if (clienteUpdateRequestDTO.getEmail() != null) {
            //TODO Verificar se o email já não pertence a outro cliente
            query.setParameter("email", clienteUpdateRequestDTO.getEmail());
        }
        if (clienteUpdateRequestDTO.getCelular() != null) {
            query.setParameter("celular", clienteUpdateRequestDTO.getCelular());
        }
        if (clienteUpdateRequestDTO.getTelefone() != null) {
            query.setParameter("telefone", clienteUpdateRequestDTO.getTelefone());
        }
        if (clienteUpdateRequestDTO.getSenha() != null) {
            senha = passwordEncoder.encode(clienteUpdateRequestDTO.getSenha());
            query.setParameter("senha", senha);
        }
        if (clienteUpdateRequestDTO.getProfissao() != null) {
            query.setParameter("profissao", clienteUpdateRequestDTO.getProfissao());
        }
        if (clienteUpdateRequestDTO.getRendaMensal() != null) {
            query.setParameter("rendaMensal", clienteUpdateRequestDTO.getRendaMensal());
        }
        if (clienteUpdateRequestDTO.getPatrimonio() != null) {
            query.setParameter("patrimonio", clienteUpdateRequestDTO.getPatrimonio());
        }

        query.setParameter("idCliente", idCliente);
        query.executeUpdate();
    }
}
