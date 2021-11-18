package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository  extends JpaRepository<Conta, Long> {
}
