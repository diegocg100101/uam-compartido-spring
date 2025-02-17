package com.uam.uam_compartido_spring.Repository;

import com.uam.uam_compartido_spring.Model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author diego
 */
@Repository
public interface SalonRepository extends JpaRepository<Salon, Integer> {
}
