package com.microservice.carro.services;

import com.microservice.carro.entities.Carro;
import com.microservice.carro.repositories.CarroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarroService {

    private CarroRepository carroRepository;

    public List<Carro> getAll() {
        return carroRepository.findAll();
    }

    public Carro getCarroById(int id) {
        return carroRepository.findById(id).orElseThrow(() -> new RuntimeException("Id: " + id + " não encontrado!"));
    }

    public Carro save(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> listCarsByUserId(int usuarioId) {
        return carroRepository.findByUsuarioId(usuarioId);
    }
}