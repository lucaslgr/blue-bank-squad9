package com.squad9.bluebank.repository;

import com.squad9.bluebank.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByRg(String rg);
    Optional<Cliente> findByEmail(String email);

    @Query("select c from Cliente c where c.email = :email and c.id <> :idCliente")
    Optional<Cliente> findByEmailExceptById(@Param("email") String email, @Param("idCliente") Long idCliente);
//    Optional<Cliente> findByEmailExceptById(String email, Long idCliente);
}
