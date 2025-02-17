package com.uam.uam_compartido_spring.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uam.uam_compartido_spring.DTO.HorarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego
 */

@Entity(name = "grupos")
@Getter
@Setter
public class Grupo {

    @Id
    @Column(name = "clavegrupo")
    private String claveGrupo;

    @OneToOne
    @JoinColumn(name = "claveuea")
    private UEA uea;

    @OneToOne
    @JoinColumn(name = "unidad")
    private Unidad unidad;

    @Column(columnDefinition = "JSON")
    private String horario;

    @Transient
    private List<String> horarioList;

    @OneToOne
    @JoinColumn(name = "noeconomico")
    private Usuario profesor;

    @Column(name = "cupounidad")
    private int cupoUnidad;

    @OneToOne
    @JoinColumn(name = "salon")
    private Salon salon;

    @Column(name = "inscritos")
    private int inscritos;

    public Grupo() {
        this.unidad = new Unidad();
        this.profesor = new Usuario();
        this.salon = new Salon();
        this.uea = new UEA();
    }

    public void convertirHorarioAJson() {
        try {
            List<HorarioDTO> horarioDTOList = new ArrayList<>();

            for (String horario : horarioList) {
                String[] dividido = horario.split(",");
                if (dividido.length == 1) {
                    horarioDTOList.add(new HorarioDTO(horarioList.get(0), horarioList.get(1)));
                    break;
                } else {
                    horarioDTOList.add(new HorarioDTO(dividido[0], dividido[1]));
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            this.horario = objectMapper.writeValueAsString(horarioDTOList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            this.horario = "[]";
        }
    }
}