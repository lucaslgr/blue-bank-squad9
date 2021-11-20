package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Cria repository para Endereço
@Repository
public class EnderecoRepository extends JpaRepository<Endereco, Long> {
}