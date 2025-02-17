package com.uam.uam_compartido_spring.Repository;

import com.uam.uam_compartido_spring.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author diego
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
