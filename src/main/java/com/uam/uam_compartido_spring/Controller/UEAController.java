package com.uam.uam_compartido_spring.Controller;

import com.uam.uam_compartido_spring.Model.Trimestre;
import com.uam.uam_compartido_spring.Model.Tronco;
import com.uam.uam_compartido_spring.Model.UEA;
import com.uam.uam_compartido_spring.Model.Unidad;
import com.uam.uam_compartido_spring.Repository.TrimestreRepository;
import com.uam.uam_compartido_spring.Repository.TroncoRepository;
import com.uam.uam_compartido_spring.Repository.UeaRepository;
import com.uam.uam_compartido_spring.Repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author diego
 */
@RestController
@RequestMapping("/uea")
public class UEAController {
    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private TroncoRepository troncoRepository;

    @Autowired
    private TrimestreRepository trimestreRepository;

    @Autowired
    private UeaRepository ueaRepository;

    @GetMapping("/add")
    private ResponseEntity<Map<String, Object>> add() {
        Map<String, Object> response = new HashMap<>();

        List<Unidad> unidades = unidadRepository.findAll();
        response.put("unidades", unidades);

        List<Tronco> troncos = troncoRepository.findAll();
        response.put("troncos", troncos);

        List<Trimestre> trimestres = trimestreRepository.findAll();
        response.put("trimestres", trimestres);

        response.put("uea", new UEA());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    private void add(@RequestBody UEA uea) {
        ueaRepository.save(uea);
    }

    @GetMapping("/list")
    private ResponseEntity<Map<String, Object>> list() {
        Map<String, Object> response = new HashMap<>();
        List<UEA> ueas = ueaRepository.findAll();
        response.put("ueas", ueas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/delete/{clave}")
    private void delete(@PathVariable("clave") String clave) {
        ueaRepository.deleteById(clave);
    }

    @PostMapping("/edit/{clave}")
    private void edit(@PathVariable("clave") String clave, @RequestBody UEA uea) {
        /* TODO */
    }
}
