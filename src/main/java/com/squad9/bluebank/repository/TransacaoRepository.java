package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository <Transacao,Long> {
}
