package com.salesianostriana.dam.ejercicio_asociaciones_03;

import com.salesianostriana.dam.ejercicio_asociaciones_03.models.*;
import com.salesianostriana.dam.ejercicio_asociaciones_03.repositories.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class MainDeMentira {

    private final UsuarioRepository usuaRepo;
    private final BicicletaRepository biciRepo;
    private final UsoRepository usoRepo;
    private final EstacionRepository estaRepo;

    @PostConstruct
    public void init() {

        Usuario kanji = Usuario.builder()
                .nombre("Kanji Tatsumi")
                .numTarjeta(123456789)
                .pin(1234)
                .saldo(1000.50)
                .build();

        Usuario aokiji = Usuario.builder()
                .nombre("Kuzan Aokiji")
                .numTarjeta(987654321)
                .pin(5678)
                .saldo(2500.75)
                .build();

        Usuario henry = Usuario.builder()
                .nombre("Enrique Juan Hinojosa Jara")
                .numTarjeta(456789123)
                .pin(4321)
                .saldo(3200.00)
                .build();

        usuaRepo.save(kanji);
        usuaRepo.save(aokiji);
        usuaRepo.save(henry);

        System.out.println(kanji);
        System.out.println(aokiji);
        System.out.println(henry);


        Estacion norte = Estacion.builder()
                .numero(1)
                .nombre("Estación Norte")
                .coordenadas("34.0522,-118.2437")
                .capacidad(30)
                .build();

        Estacion central = Estacion.builder()
                .numero(2)
                .nombre("Estación Central")
                .coordenadas("40.7128,-74.0060")
                .capacidad(50)
                .build();

        Estacion sur = Estacion.builder()
                .numero(3)
                .nombre("Estación Sur")
                .coordenadas("51.5074,-0.1278")
                .capacidad(40)
                .build();

        estaRepo.save(norte);
        estaRepo.save(central);
        estaRepo.save(sur);

        System.out.println(norte);
        System.out.println(central);
        System.out.println(sur);


        // SIN ESTACIÓN
        Bicicleta bici1 = Bicicleta.builder()
                .marca("Giant")
                .modelo("Talon 3")
                .estado("Disponible")
                .build();

        Bicicleta bici2 = Bicicleta.builder()
                .marca("Trek")
                .modelo("Marlin 5")
                .estado("En reparación")
                .estacion(central)
                .build();

        // SIN ESTACIÓN
        Bicicleta bici3 = Bicicleta.builder()
                .marca("Specialized")
                .modelo("Rockhopper")
                .estado("Disponible")
                .build();

        sur.addBicicleta(bici1);
        norte.addBicicleta(bici3);

        biciRepo.save(bici1);
        biciRepo.save(bici2);
        biciRepo.save(bici3);

        System.out.println(bici1);
        System.out.println(bici2);
        System.out.println(bici3);

        // SIN USUARIO, SIN BICICLETA, SIN ESTACIÓN
        Uso u1 = Uso.builder()
                .fechaInicio(LocalDate.of(2025, 01, 10))
                .fechaFin(LocalDate.of(2025, 01, 10))
                .coste(25.2)
                .build();

        // SIN FECHA FIN, SIN USUARIO, SIN BICICLETA, SIN ESTACIÓN
        Uso u2 = Uso.builder()
                .fechaInicio(LocalDate.of(2025, 01, 10))
                .coste(14.6)
                .build();

        kanji.addUso(u1);
        bici2.addUso(u1);
        norte.addUso(u1);

        henry.addUso(u2);
        bici3.addUso(u2);

        usoRepo.save(u1);
        usoRepo.save(u2);

        System.out.println(u1);
        System.out.println(u2);

        System.out.println(biciRepo.findByEstado("Disponible"));

    }
}
