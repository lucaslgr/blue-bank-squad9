package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository <Transacao,Long> {
    List<Transacao> findAllByIdContaEmissoraOrIdContaReceptoraOrderByDataEnvioDesc(Long idContaEmissora, Long idContaReceptora);

    List<Transacao> findAllOrderByDataEnvioDesc();
}