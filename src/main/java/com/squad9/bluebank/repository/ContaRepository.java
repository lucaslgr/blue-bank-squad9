package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Cliente;
import com.squad9.bluebank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByCliente(Cliente cliente);

    @Transactional
    @Modifying
    @Query("update Conta c set c.saldo = :saldo where c.id = :idConta")
    void updateSaldo(@Param("idConta") Long idConta, @Param("saldo") Long saldo);

    Optional<Conta> findByNumero(String numeroContaDestino);
}
