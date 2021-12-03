package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Conta;
import com.squad9.bluebank.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    @Query(
            value = "SELECT * FROM transacoes t WHERE t.id_conta_emissora = :idConta OR t.id_conta_receptora = :idConta ORDER BY data_envio DESC",
            nativeQuery = true
    )
    List<Transacao> findAllByIdContaOrdenadoPorDataEnvioDesc(Long idConta);

    List<Transacao> findAllByOrderByDataEnvioDesc();

    @Transactional
    void deleteByContaReceptora(Conta contaReceptora);

    @Transactional
    void deleteByContaEmissora(Conta contaEmissora);
}