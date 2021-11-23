package com.squad9.bluebank.repository;

import java.util.Optional;

import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository  extends JpaRepository<Conta, Long> {
    Optional<Conta>  findByCliente(Conta conta);
}
