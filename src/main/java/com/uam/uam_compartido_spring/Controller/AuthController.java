package com.uam.uam_compartido_spring.Controller;

import com.uam.uam_compartido_spring.DTO.AuthRequestDTO;
import com.uam.uam_compartido_spring.Model.Departamento;
import com.uam.uam_compartido_spring.Model.Division;
import com.uam.uam_compartido_spring.Model.Unidad;
import com.uam.uam_compartido_spring.Model.Usuario;
import com.uam.uam_compartido_spring.Repository.DepartamentoRepository;
import com.uam.uam_compartido_spring.Repository.DivisionRepository;
import com.uam.uam_compartido_spring.Repository.UnidadRepository;
import com.uam.uam_compartido_spring.Service.AuthService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup() {
        Map<String, Object> response = new HashMap<>();

        List<Unidad> unidades = unidadRepository.findAll();
        response.put("unidades", unidades);

        List<Division> divisiones = divisionRepository.findAll();
        response.put("divisiones", divisiones);

        List<Departamento> departamentos = departamentoRepository.findAll();
        response.put("departamentos", departamentos);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(authService.signup(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authDTO) {
            return ResponseEntity.ok(authService.authenticate(authDTO));
    }
}
