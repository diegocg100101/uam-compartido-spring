package com.uam.uam_compartido_spring.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author diego
 */
@Entity(name = "uea")
@Setter
@Getter
public class UEA {

    @Id
    @Column(name = "clave")
    private String Clave;

    @Column(name = "nombre")
    private String Nombre;

    @ManyToOne
    @JoinColumn(name = "idunidad")
    public Unidad Unidad;

    @Column(name = "creditos")
    private int Creditos;

    @ManyToOne
    @JoinColumn(name = "idtronco")
    public Tronco Tronco;

    @ManyToOne
    @JoinColumn(name = "idtrimestre")
    public Trimestre Trimestre;

    public UEA() {
        this.Trimestre = new Trimestre();
        this.Tronco = new Tronco();
        this.Unidad = new Unidad();
    }
}
