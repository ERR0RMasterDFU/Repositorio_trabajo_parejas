package com.salesianostriana.dam.ejercicio_asociaciones_03.dto;

public record EditEstacionCmd(
        Long id,
        int numero,
        String nombre,
        String coordenadas,
        int capacidad
) {
}
