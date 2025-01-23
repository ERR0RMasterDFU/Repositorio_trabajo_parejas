package com.salesianostriana.dam.ejercicio_asociaciones_03.Controllers;

import com.salesianostriana.dam.ejercicio_asociaciones_03.dto.EditEstacionCmd;
import com.salesianostriana.dam.ejercicio_asociaciones_03.dto.GetEstacionDto;
import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Estacion;
import com.salesianostriana.dam.ejercicio_asociaciones_03.services.EstacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estaciones")
@RequiredArgsConstructor
public class EstacionController {

    private final EstacionService estacionService;

    @GetMapping
    public ResponseEntity<List<GetEstacionDto>> findAll() {
        return ResponseEntity.ok(
                estacionService.findAll().stream()
                        .map(GetEstacionDto::of)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEstacionDto> findById(@PathVariable Long id) {
        Estacion estacion = estacionService.findById(id);
        return ResponseEntity.ok(GetEstacionDto.of(estacion));
    }

    @PostMapping
    public ResponseEntity<GetEstacionDto> create(@RequestBody EditEstacionCmd estacionCmd) {
        Estacion estacion = estacionService.save(
                Estacion.builder()
                        .numero(estacionCmd.numero())
                        .nombre(estacionCmd.nombre())
                        .coordenadas(estacionCmd.coordenadas())
                        .capacidad(estacionCmd.capacidad())
                        .build()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetEstacionDto.of(estacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetEstacionDto> update(@PathVariable Long id, @RequestBody EditEstacionCmd estacionCmd) {
        Estacion updated = estacionService.edit(
                Estacion.builder()
                        .numero(estacionCmd.numero())
                        .nombre(estacionCmd.nombre())
                        .coordenadas(estacionCmd.coordenadas())
                        .capacidad(estacionCmd.capacidad())
                        .build(),
                id
        );
        return ResponseEntity.ok(GetEstacionDto.of(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}