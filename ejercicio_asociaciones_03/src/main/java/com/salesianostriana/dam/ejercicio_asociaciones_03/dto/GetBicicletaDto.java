package com.salesianostriana.dam.ejercicio_asociaciones_03.dto;

import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Bicicleta;
import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Estacion;

public record GetBicicletaDto(
        Long id,
        String marca,
        String modelo,
        String estado,
        GetEstacionDto estacion
) {

    public static GetBicicletaDto of(Bicicleta bicicleta, GetEstacionDto estacion) {
        return new GetBicicletaDto(
                bicicleta.getId(),
                bicicleta.getMarca(),
                bicicleta.getModelo(),
                bicicleta.getEstado(),
                GetEstacionDto.of(bicicleta.getEstacion())
        );
    }
}
