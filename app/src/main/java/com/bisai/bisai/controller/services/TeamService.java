package com.bisai.bisai.controller.services;

import com.bisai.bisai.model.Equipo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by sergi on 12/05/2017.
 */

public interface TeamService {
       @GET("/api/equipos")
       Call<List<Equipo>> getAllTeams(
                    @Header("Authorization") String Authorization
            );
    @GET ("/api/equipos/{id}")
    Call<Equipo> getEquipoById(
            @Header("Authorization") String Authorization, @Path("id") Long id
    );

    // post

    @POST("api/equipos")
    Call<Equipo> registerEquipo(
            @Body Equipo equipo,
            @Header("Authorization") String Authorization
    );

    //put

    @PUT("api/equipos/{idEquipo}/jugador/{idJugador}")
    Call<Equipo> addJugadorEquipo(
            @Path("idEquipo") long idEquipo,
            @Path("idJugador") long idJugador,
            @Header("equipoPassword") String equipoPassword,
            @Header("Authorization") String Authorization
    );

}
