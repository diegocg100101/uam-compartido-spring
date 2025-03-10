package com.uam.uam_compartido_spring.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author diego
 */
@Entity(name = "division")
@Getter
@Setter
public class Division {
    @Id
    @Column(name = "iddivision")
    private int idDivision;

    @Column(name = "nombre")
    private String nombre;
}
