package com.bisai.bisai.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergi on 27/03/2017.
 */

public class Equipo {


    private Long id;
    private String nombre;
    private String fechaCreacion;
    private String password;
    private Set<Jugador> jugadors = new HashSet<>();

    public Equipo(Long id, Set<Jugador> jugadors, String password, String fechaCreacion, String nombre) {
        this.id = id;
        this.jugadors = jugadors;
        this.password = password;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipo equipo = (Equipo) o;

        if (!id.equals(equipo.id)) return false;
        if (!nombre.equals(equipo.nombre)) return false;
        if (!fechaCreacion.equals(equipo.fechaCreacion)) return false;
        if (!password.equals(equipo.password)) return false;
        return jugadors.equals(equipo.jugadors);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + fechaCreacion.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + jugadors.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Jugador> getJugadors() {
        return jugadors;
    }

    public void setJugadors(Set<Jugador> jugadors) {
        this.jugadors = jugadors;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
