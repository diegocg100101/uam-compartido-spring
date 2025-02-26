package com.uam.uam_compartido_spring.Controller;

import com.uam.uam_compartido_spring.DTO.UnidadesDTO;
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
import java.util.Optional;

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
    public ResponseEntity<Map<String, Object>> add() {
        Map<String, Object> response = new HashMap<>();

        List<Unidad> unidades = unidadRepository.findAll();
        response.put("unidades", unidades);

        List<Tronco> troncos = troncoRepository.findAll();
        response.put("troncos", troncos);

        List<Trimestre> trimestres = trimestreRepository.findAll();
        response.put("trimestres", trimestres);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UEA uea) {
        try {
            ueaRepository.save(uea);
            return ResponseEntity.ok("UEA dada de alta exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error al dar de alta la UEA");
        }
    }


    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> list() {
        Map<String, Object> response = new HashMap<>();
        List<UEA> ueas = ueaRepository.findAll();
        response.put("ueas", ueas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/delete/{clave}")
    public ResponseEntity<String> delete(@PathVariable("clave") String clave) {
        try {
            ueaRepository.deleteById(clave);
            return ResponseEntity.ok("Eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("No se pudo eliminar");
        }
    }

    @GetMapping("/edit/{clave}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("clave") String clave) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<UEA> uea = ueaRepository.findById(clave);
            response.put("uea", uea.get());

            List<Unidad> unidades = unidadRepository.findAll();
            response.put("unidades", unidades);

            List<Tronco> troncos = troncoRepository.findAll();
            response.put("troncos", troncos);

            List<Trimestre> trimestres = trimestreRepository.findAll();
            response.put("trimestres", trimestres);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody UEA uea) {
        if (ueaRepository.existsById(uea.getClave())) {
            ueaRepository.save(uea);
            return ResponseEntity.ok("UEA editada");
        } else{
            return ResponseEntity.status(404).body("UEA con clave " + uea.getClave() + " no encontrada");
        }
    }

    @GetMapping("/share/{clave}")
    public ResponseEntity<Map<String, Object>> share(@PathVariable("clave") String clave) {
        Map<String, Object> response = new HashMap<>();

        UEA uea = ueaRepository.findById(clave).get();
        response.put("uea", uea);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/share/{clave}")
    public ResponseEntity<String> share(@PathVariable("clave") String clave, @RequestBody UnidadesDTO unidadesDTO) {
        try {
            // Busca la UEA por clave
            UEA uea = ueaRepository.findById(clave).get();

            // Itera en el arreglo de unidades
            for (String id : unidadesDTO.getIdUnidades()) {

                // Crea la nueva UEA compartida
                UEA ueaShared = new UEA();

                // Duplica los atributos a excepción de la unidad
                ueaShared.setClave(uea.getClave());
                ueaShared.setNombre(uea.getNombre());
                ueaShared.setCreditos(uea.getCreditos());
                ueaShared.setUnidad(uea.getUnidad());
                ueaShared.setTronco(uea.getTronco());
                ueaShared.setTrimestre(uea.getTrimestre());

                // Añade la unidad correspondiente al id
                ueaShared.setUnidad(unidadRepository.findById(Integer.parseInt(id)).get());

                // Guarda en la base de datos
                ueaRepository.save(ueaShared);
            }
            return ResponseEntity.ok("UEA compartida con las demás unidades");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
