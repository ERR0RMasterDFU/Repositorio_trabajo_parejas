package com.salesianostriana.dam.ejercicio_asociaciones_03.dto;

import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Estacion;

public record GetEstacionDto(
        Long id,
        int numero,
        String coordenadas,
        int capacidad
) {

    public static GetEstacionDto of(Estacion estacion) {
        return new GetEstacionDto(
                estacion.getId(),
                estacion.getNumero(),
                estacion.getCoordenadas(),
                estacion.getCapacidad()
        );
    }

}
