package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByRg(String rg);
    Optional<Cliente> findByEmail(String email);
}
