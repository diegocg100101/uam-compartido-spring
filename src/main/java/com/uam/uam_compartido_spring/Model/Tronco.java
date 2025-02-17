package com.uam.uam_compartido_spring.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author diego
 */
@Entity(name = "tronco")
@Setter
@Getter
public class Tronco {

    @Column(name="idtronco")
    @Id
    private int IdTronco;

    @Column(name="nombre")
    private String Nombre;
}
