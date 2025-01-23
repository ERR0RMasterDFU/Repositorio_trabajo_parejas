package com.salesianostriana.dam.ejercicio_asociaciones_03.services;

import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Bicicleta;
import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Estacion;
import com.salesianostriana.dam.ejercicio_asociaciones_03.repositories.BicicletaRepository;
import com.salesianostriana.dam.ejercicio_asociaciones_03.repositories.EstacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BicicletaService {

    private final BicicletaRepository repo;

    public List<Bicicleta> findAll() {
        List<Bicicleta> bicicletas =  repo.findAll();

        if(bicicletas.isEmpty()) {
            throw new EntityNotFoundException("No se han encoontrado bicicletas.");
        }

        return bicicletas;
    }

    public Bicicleta findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe una bicicleta con ID: "+ id));
    }

    public Bicicleta save(Bicicleta bicicleta) {
        return repo.save(bicicleta);
    }

    public Bicicleta edit(Bicicleta bicicleta, Long id) {
        return repo.findById(id)
                .map(old -> {
                    old.setMarca(bicicleta.getMarca());
                    old.setModelo(bicicleta.getModelo());
                    old.setEstado(bicicleta.getEstado());
                    old.setEstacion(bicicleta.getEstacion());
                    return repo.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe una bicicleta con ID: "+ id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
