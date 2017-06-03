package com.bisai.bisai.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sergi on 27/03/2017.
 */

public class Torneo {


    private Long id;

    private String nombre;

    private Integer numeroParticipantes;

    private String fechaInicio;
    private Boolean cancelado;
    private String descripcion;
    private Juego juego;
    private Set<Jugador> administradors = new HashSet<>();
    private Set<Local> locales = new HashSet<>();
    private List<Equipo> equipos = new ArrayList<>();
    private Equipo equipoGanador;


    public Torneo(Long id, Equipo equipoGanador, List<Equipo> equipos, Set<Jugador> administradors, Set<Local> locales, String descripcion, Juego juego, Boolean cancelado, String fechaInicio, Integer numeroParticipantes, String nombre) {
        this.id = id;
        this.equipoGanador = equipoGanador;
        this.equipos = equipos;
        this.administradors = administradors;
        this.locales = locales;
        this.descripcion = descripcion;
        this.juego = juego;
        this.cancelado = cancelado;
        this.fechaInicio = fechaInicio;
        this.numeroParticipantes = numeroParticipantes;
        this.nombre = nombre;
    }

    public Set<Local> getLocales() {
        return locales;
    }

    public void setLocales(Set<Local> locales) {
        this.locales = locales;
    }

    public Equipo getEquipoGanador() {
        return equipoGanador;
    }

    public void setEquipoGanador(Equipo equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Set<Jugador> getAdministradors() {
        return administradors;
    }

    public void setAdministradors(Set<Jugador> administradors) {
        this.administradors = administradors;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(Integer numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
