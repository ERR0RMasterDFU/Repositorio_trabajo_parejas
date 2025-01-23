package com.salesianostriana.dam.ejercicio_asociaciones_03.Controllers;

import com.salesianostriana.dam.ejercicio_asociaciones_03.dto.EditBicicletaCmd;
import com.salesianostriana.dam.ejercicio_asociaciones_03.dto.GetBicicletaDto;
import com.salesianostriana.dam.ejercicio_asociaciones_03.dto.GetEstacionDto;
import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Bicicleta;
import com.salesianostriana.dam.ejercicio_asociaciones_03.services.BicicletaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bicicletas")
@RequiredArgsConstructor
public class BicicletaController {

    private final BicicletaService bicicletaService;

    @GetMapping
    public ResponseEntity<List<GetBicicletaDto>> findAll() {
        return ResponseEntity.ok(
                bicicletaService.findAll().stream()
                        .map(b -> GetBicicletaDto.of(b, GetEstacionDto.of(b.getEstacion())))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBicicletaDto> findById(@PathVariable Long id) {
        Bicicleta bicicleta = bicicletaService.findById(id);
        return ResponseEntity.ok(
                GetBicicletaDto.of(bicicleta, GetEstacionDto.of(bicicleta.getEstacion()))
        );
    }

    @PostMapping
    public ResponseEntity<GetBicicletaDto> create(@RequestBody EditBicicletaCmd bicicletaCmd) {
        Bicicleta bicicleta = bicicletaService.save(
                Bicicleta.builder()
                        .marca(bicicletaCmd.marca())
                        .modelo(bicicletaCmd.modelo())
                        .estado(bicicletaCmd.estado())
                        .estacion(null)
                        .build()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetBicicletaDto.of(bicicleta, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetBicicletaDto> update(@PathVariable Long id, @RequestBody EditBicicletaCmd bicicletaCmd) {
        Bicicleta updated = bicicletaService.edit(
                Bicicleta.builder()
                        .marca(bicicletaCmd.marca())
                        .modelo(bicicletaCmd.modelo())
                        .estado(bicicletaCmd.estado())
                        .estacion(null)
                        .build(),
                id
        );
        return ResponseEntity.ok(GetBicicletaDto.of(updated, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bicicletaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}