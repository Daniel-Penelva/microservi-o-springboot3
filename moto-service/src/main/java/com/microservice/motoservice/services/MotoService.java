package com.microservice.motoservice.services;

import com.microservice.motoservice.entities.Moto;
import com.microservice.motoservice.repositories.MotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MotoService {

    private MotoRepository motoRepository;

    public List<Moto> findAll() {
        return motoRepository.findAll();
    }

    public Moto getMotoById(int id) {
        return motoRepository.findById(id).orElseThrow(() -> new RuntimeException("Id: " + id + " não encontrado!"));
    }

    public Moto save(Moto moto) {
        return motoRepository.save(moto);
    }

    public List<Moto> listMotorcycleByUserId(int usuarioId) {
        return motoRepository.findByUsuarioId(usuarioId);
    }
}
