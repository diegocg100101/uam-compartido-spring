package com.uam.uam_compartido_spring.DTO;

/**
 * @author diego
 */
public class HorarioDTO {
    private String dia;
    private String hora;

    public HorarioDTO() {}

    public HorarioDTO(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
