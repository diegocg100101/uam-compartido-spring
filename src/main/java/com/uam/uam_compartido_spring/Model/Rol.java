package com.uam.uam_compartido_spring.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author diego
 */
@Entity(name = "roles")
@Getter
@Setter
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol")
    private int idRol;

    @Column(name = "nombre")
    private String nombre;
}
