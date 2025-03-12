package com.uam.uam_compartido_spring.Controller;

import com.uam.uam_compartido_spring.DTO.UnidadesDTO;
import com.uam.uam_compartido_spring.Model.*;
import com.uam.uam_compartido_spring.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    private UsuarioRepository usuarioRepository;


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

        List<Usuario> usuarios = usuarioRepository.findAll();
        response.put("usuarios", usuarios);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    private ResponseEntity<String> addGrupo(@RequestBody Grupo grupo) {
        try {
            grupo.convertirHorarioAJson();
            grupoRepository.save(grupo);
            return ResponseEntity.ok("Grupo dado de alta correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error al dar de alta el grupo");
        }
    }

    @GetMapping("/list")
    private ResponseEntity<Map<String, Object>> listGrupos() {
        Map<String, Object> response = new HashMap<>();

        List<Grupo> grupos = grupoRepository.findAll();
        response.put("grupos", grupos);

        return ResponseEntity.ok(response);
    }

    @GetMapping("delete/{clave}")
    public ResponseEntity<String> deleteGrupo(@PathVariable("clave") String clave) {
        try {
            grupoRepository.deleteById(clave);
            return ResponseEntity.ok("Grupo dado de alta correctamente");
        }catch (Exception e){
            return ResponseEntity.status(404).body("Error al dar de alta el grupo");
        }
    }

    @GetMapping("/edit/{clave}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("clave") String clave) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Grupo> grupo = grupoRepository.findById(clave);
            response.put("grupo", grupo.get());

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
        } catch (Exception e) {
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody Grupo grupo) {
        if (grupoRepository.existsById(grupo.getClaveGrupo())){
            if(grupo.getHorarioList() != null) grupo.convertirHorarioAJson();
            grupoRepository.save(grupo);
            return ResponseEntity.ok("Grupo editada");
        } else {
            return ResponseEntity.status(404).body("Grupo con clave " + grupo.getClaveGrupo() + " no encontrada");
        }
    }

    @PostMapping("/share/{clave}")
    public ResponseEntity<String> share(@PathVariable("clave") String clave, @RequestBody UnidadesDTO unidadesDTO) {
        try {
            // Busca el grupo por clave
            Grupo grupo = grupoRepository.findById(clave).get();

            // Itera en el arreglo de unidades
            for (String id : unidadesDTO.getIdUnidades()) {

                // Busca la unidad en la base de datos
                Unidad unidad = unidadRepository.findById(Integer.parseInt(id)).get();

                // Crea el nuevo grupo compartido
                Grupo grupoCompartido = new Grupo();

                // Duplica los atributos a excepción de la unidad
                grupoCompartido.setClaveGrupo(unidad.getNombre().substring(0, 3).toUpperCase() + clave.substring(3));
                grupoCompartido.setUea(grupo.getUea());
                grupoCompartido.setUnidad(grupo.getUnidad());
                grupoCompartido.setHorario(grupo.getHorario());
                grupoCompartido.setProfesor(grupo.getProfesor());
                grupoCompartido.setSalon(grupo.getSalon());
                grupoCompartido.setInscritos(grupo.getInscritos());
                grupoCompartido.setCupoUnidad(grupo.getCupoUnidad());
                grupoCompartido.setTrimestreestacion(grupo.getTrimestreestacion());

                // Añade la unidad correspondiente al id
                grupoCompartido.setUnidad(unidad);

                // Guarda en la base de datos
                grupoRepository.save(grupoCompartido);
            }
            return ResponseEntity.ok("Grupo compartida con las demás unidades");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
