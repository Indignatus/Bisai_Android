package com.bisai.bisai.controller.services;

import com.bisai.bisai.model.Equipo;
import com.bisai.bisai.model.Torneo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by dam on 25/5/17.
 */

public interface TorneoService {
    @GET("/api/torneos")
    Call<List<Torneo>> getAllTorneos(
            @Header("Authorization") String Authorization
    );
    @GET("/api/torneos/busqueda")
    Call<List<Torneo>> getBusquedaTorneos(
            @Header("Authorization") String Authorization
    );
    @GET ("/api/torneos/{id}")
    Call<Torneo> getTorneoById(
            @Header("Authorization") String Authorization, @Path("id") Long id
    );
    @PUT("/api/torneos/{id}/equipo/{idEquipo}")
    Call<Torneo> addEquipoTorneo(
            @Path("id") Long idTorneo,
            @Path("idEquipo") Long idEquipo,
            @Header("Authorization") String Authorization
    );

    @GET ("/api/torneos/pendiente/jugador/{id}")
    Call<List<Torneo>> getTorneoPendienteJugador(
            @Header("Authorization") String Authorization, @Path("id") Long id
    );

}
