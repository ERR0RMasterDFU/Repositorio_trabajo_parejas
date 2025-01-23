package com.salesianostriana.dam.ejercicio_asociaciones_03.repositories;

import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {

    // SELECCIONA LAS BICICLETAS CUYO ESTADO SEA DISPONIBLE
    List<Bicicleta> findByEstado(String disponible);

}
