package com.uam.uam_compartido_spring.Controller;

import com.uam.uam_compartido_spring.Model.Grupo;
import com.uam.uam_compartido_spring.Model.UEA;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author diego
 */
@RestController
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> index() {
        return ResponseEntity.ok(new HashMap<>());
    }
}
