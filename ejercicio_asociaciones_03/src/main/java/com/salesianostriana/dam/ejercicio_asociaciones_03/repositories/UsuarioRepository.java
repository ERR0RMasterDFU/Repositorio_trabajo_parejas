package com.salesianostriana.dam.ejercicio_asociaciones_03.repositories;

import com.salesianostriana.dam.ejercicio_asociaciones_03.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
