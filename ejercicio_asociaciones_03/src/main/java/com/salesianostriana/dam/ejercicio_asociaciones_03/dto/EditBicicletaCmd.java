package com.salesianostriana.dam.ejercicio_asociaciones_03.dto;

public record EditBicicletaCmd(
        Long id,
        String marca,
        String modelo,
        String estado,
        GetEstacionDto estacion
) {
}
