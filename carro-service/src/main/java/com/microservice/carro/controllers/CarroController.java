package com.microservice.carro.controllers;

import com.microservice.carro.entities.Carro;
import com.microservice.carro.services.CarroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carro")
@AllArgsConstructor
public class CarroController {

    private CarroService carroService;

    // http://localhost:8002/api/carro/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<Carro>> getCarroAll() {
        List<Carro> carros = carroService.getAll();
        if (carros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }

    // http://localhost:8002/api/carro/search/{id}
    @GetMapping("/search/{id}")
    public ResponseEntity<Carro> getCarroById(@PathVariable int id) {
        Carro carro = carroService.getCarroById(id);
        if (carro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    // http://localhost:8002/api/carro/save
    @PostMapping("/save")
    public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro) {
        return ResponseEntity.ok(carroService.save(carro));
    }

    // http://localhost:8002/api/carro/searchUserId/{usuarioId}
    @GetMapping("/searchUserId/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarroPorUsuarioId(@PathVariable int usuarioId) {
        List<Carro> carros = carroService.listCarsByUserId(usuarioId);
        if (carros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
}
