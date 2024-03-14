package com.microservice.motoservice.controllers;

import com.microservice.motoservice.entities.Moto;
import com.microservice.motoservice.services.MotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moto")
@AllArgsConstructor
public class MotoController {

    private MotoService motoService;

    // http://localhost:8003/api/moto/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<Moto>> getMotoAll() {
        List<Moto> motos = motoService.findAll();
        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    // http://localhost:8003/api/moto/search/{id}
    @GetMapping("/search/{id}")
    public ResponseEntity<Moto> getMotoById(@PathVariable int id) {
        Moto moto = motoService.getMotoById(id);
        if (moto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    // http://localhost:8003/api/moto/save
    @PostMapping("/save")
    public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto) {
        return ResponseEntity.ok(motoService.save(moto));
    }

    // http://localhost:8083/api/moto/searchUserId/{usuarioId}
    @GetMapping("/searchUserId/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotoPorUsuarioId(@PathVariable int usuarioId) {
        List<Moto> motos = motoService.listMotorcycleByUserId(usuarioId);
        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }
}
