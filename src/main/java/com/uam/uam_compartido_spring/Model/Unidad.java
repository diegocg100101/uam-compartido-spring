package com.uam.uam_compartido_spring.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author diego
 */
@Entity(name = "unidad")
@Setter
@Getter
public class Unidad {

    @Id
    @Column(name="idunidad")
    private int IdUnidad;

    @Column(name="nombre")
    private String Nombre;
}
