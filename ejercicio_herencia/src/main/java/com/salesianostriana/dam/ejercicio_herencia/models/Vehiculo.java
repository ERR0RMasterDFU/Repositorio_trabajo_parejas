package com.salesianostriana.dam.ejercicio_herencia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@NoArgsConstructor
@MappedSuperclass
public abstract class Vehiculo {

    @Id
    @GeneratedValue
    private Long id;

    private String marca;
    private String color;
    private String matricula;
    private double precio;


}
