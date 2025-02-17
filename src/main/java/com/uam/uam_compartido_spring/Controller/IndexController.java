package com.uam.uam_compartido_spring.Controller;

import com.uam.uam_compartido_spring.Model.UEA;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author diego
 */
@RestController
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public ResponseEntity<UEA> index() {
        UEA prueba = new UEA();
        prueba.setNombre("SO");
        return ResponseEntity.ok(prueba);
    }
}
