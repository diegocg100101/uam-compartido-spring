package com.uam.uam_compartido_spring.Repository;

import com.uam.uam_compartido_spring.Model.UEA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author diego
 */

@Repository
public interface UeaRepository extends JpaRepository<UEA, String> {
}
