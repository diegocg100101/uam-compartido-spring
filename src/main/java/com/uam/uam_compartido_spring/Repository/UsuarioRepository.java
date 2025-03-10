package com.uam.uam_compartido_spring.Repository;

import com.uam.uam_compartido_spring.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author diego
 */
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    // @Query(value = "SELECT * FROM usuarios WHERE email = :email", nativeQuery = true)
    Optional<Usuario> findByEmail(String email);
}
