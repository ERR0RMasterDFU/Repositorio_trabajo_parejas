package com.salesianostriana.dam.ejercicio_asociaciones_03.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Estacion {

    @Id
    @GeneratedValue
    private Long id;

    private int numero;
    private String nombre;
    private String coordenadas;
    private int capacidad;


    // ASOCIACIÓN CON BICICLETA
    @OneToMany(mappedBy = "estacion",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Bicicleta> bicicletas = new ArrayList<>();

    // ASOCIACIÓN CON BICICLETA
    @OneToMany(mappedBy = "estacion",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Uso> usos = new ArrayList<>();


    // HELPERS
    public void addBicicleta (Bicicleta b) {
        b.setEstacion(this);
        this.bicicletas.add(b);
    }

    public void removeBicicleta (Bicicleta b) {
        this.bicicletas.remove(b);
        b.setEstacion(null);
    }

    public void addUso (Uso u) {
        u.setEstacion(this);
        this.usos.add(u);
    }

    public void removeUso (Uso u) {
        this.usos.remove(u);
        u.setEstacion(null);
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Estacion estacion = (Estacion) o;
        return getId() != null && Objects.equals(getId(), estacion.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
