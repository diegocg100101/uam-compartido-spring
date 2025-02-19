package com.uam.uam_compartido_spring.Controller;

import com.uam.uam_compartido_spring.Model.Grupo;
import com.uam.uam_compartido_spring.Model.UEA;
import com.uam.uam_compartido_spring.Model.Unidad;
import com.uam.uam_compartido_spring.Repository.UeaRepository;
import com.uam.uam_compartido_spring.Repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author diego
 */
@RestController
@RequestMapping
public class IndexController {

    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private UeaRepository ueaRepository;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Bienvenido a la API de UAM Compartido");
    }
}
