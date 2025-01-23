package com.salesianostriana.dam.ejercicio_asociaciones_03.services;

import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Estacion;
import com.salesianostriana.dam.ejercicio_asociaciones_03.repositories.EstacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionService {

    private final EstacionRepository repo;

    public List<Estacion> findAll() {
        List<Estacion> estaciones =  repo.findAll();

        if(estaciones.isEmpty()) {
            throw new EntityNotFoundException("No se han encoontrado elementos.");
        }

        return estaciones;
    }

    public Estacion findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe una estación con ID: "+ id));
    }

    public Estacion save(Estacion estacion) {
        return repo.save(estacion);
    }

    public Estacion edit(Estacion estacion, Long id) {
        return repo.findById(id)
                .map(old -> {
                    old.setNumero(estacion.getNumero());
                    old.setNombre(estacion.getNombre());
                    old.setCoordenadas(estacion.getCoordenadas());
                    old.setCapacidad(estacion.getCapacidad());
                    return repo.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe una estación con ID: "+ id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
