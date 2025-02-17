package com.uam.uam_compartido_spring.Controller;

import com.uam.uam_compartido_spring.Model.Grupo;
import com.uam.uam_compartido_spring.Model.Salon;
import com.uam.uam_compartido_spring.Model.UEA;
import com.uam.uam_compartido_spring.Model.Unidad;
import com.uam.uam_compartido_spring.Repository.GrupoRepository;
import com.uam.uam_compartido_spring.Repository.SalonRepository;
import com.uam.uam_compartido_spring.Repository.UeaRepository;
import com.uam.uam_compartido_spring.Repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author diego
 */
@RestController
@RequestMapping("/grupos")
public class GruposController {

    @Autowired
    private UeaRepository ueaRepository;

    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private GrupoRepository grupoRepository;


    @GetMapping("/add")
    private ResponseEntity<Map<String, Object>> addGrupo() {
        Map<String, Object> response = new HashMap<>();

        List<UEA> ueas = ueaRepository.findAll();
        response.put("ueas", ueas);

        List<Unidad> unidades = unidadRepository.findAll();
        response.put("unidades", unidades);

        List<Salon> salones = salonRepository.findAll();
        response.put("salones", salones);

        List<String> dias = Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes");
        response.put("dias", dias);

        List<String> horas = Arrays.asList("08:00 - 09:30", "09:30 - 11:00", "11:00 - 12:30", "12:30 - 14:00",
                "14:00 - 15:30", "15:30 - 17:00", "17:00 - 19:00");
        response.put("horas", horas);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    private void addGrupo(@RequestBody Grupo grupo) {
        grupoRepository.save(grupo);
    }

    @GetMapping("/list")
    private ResponseEntity<Map<String, Object>> listGrupos() {
        Map<String, Object> response = new HashMap<>();

        List<Grupo> grupos = grupoRepository.findAll();
        response.put("grupos", grupos);

        return ResponseEntity.ok(response);
    }
}
