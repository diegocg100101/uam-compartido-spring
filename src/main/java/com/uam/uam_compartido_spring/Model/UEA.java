package com.uam.uam_compartido_spring.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author diego
 */
@Entity
public class UEA {

    @Id
    @Column(name = "clave")
    private String clave;

    @Column(name = "nombre")
    private String Nombre;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
