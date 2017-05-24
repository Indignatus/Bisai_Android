package com.bisai.bisai.controller.services;

import com.bisai.bisai.model.Jugador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by sergi on 18/05/2017.
 */

public interface JugadorService {

    @GET("/api/jugadors/byLogin/{login}")
    Call<Jugador> getJugadorByLogin(
            @Header("Authorization") String Authorization, @Path("login") String login
    );

}
