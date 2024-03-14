package com.microservice.usuario.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Moto {

    private String marca;
    private String modelo;
    private int usuarioId;
}
