package com.uam.uam_compartido_spring.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author diego
 */
@Entity(name = "trimestre")
@Getter
@Setter
public class Trimestre {

    @Id
    @Column(name="idtrimestre")
    private int IdTrimestre;

    @Column(name="nombre")
    private String Nombre;
}
