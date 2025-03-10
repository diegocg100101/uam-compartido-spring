package com.uam.uam_compartido_spring.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author diego
 */
@Entity(name = "departamento")
@Getter
@Setter
public class Departamento {
    @Id
    @Column(name = "iddepartamento")
    private int IdDepartamento;

    @Column(name = "nombre")
    private String Nombre;
}
