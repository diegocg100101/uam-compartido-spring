package com.uam.uam_compartido_spring.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author diego
 */
@Entity(name = "salon")
@Getter
@Setter
public class Salon {

    @Id
    @Column(name = "idsalon")
    private int idSalon;

    @Column (name = "nombre")
    private String nombre;

    @Column(name = "cupo")
    private int cupo;

    @Column(name = "ubicacion")
    private String ubicacion;
}
