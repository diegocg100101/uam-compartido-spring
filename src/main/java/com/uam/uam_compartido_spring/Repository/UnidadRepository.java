package com.uam.uam_compartido_spring.Repository;

import com.uam.uam_compartido_spring.Model.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author diego
 */

@Repository
    public interface UnidadRepository extends JpaRepository<Unidad, String> {
}
