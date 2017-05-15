package com.bisai.bisai.controller.services;

import com.bisai.bisai.model.Equipo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
}
