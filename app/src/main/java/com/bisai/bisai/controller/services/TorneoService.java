package com.bisai.bisai.controller.services;

import com.bisai.bisai.model.Torneo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by dam on 25/5/17.
 */

public interface TorneoService {
    @GET("/api/torneos")
    Call<List<Torneo>> getAllTorneos(
        @Header("Authorization") String Authorization
    );
    @GET ("/api/torneos/{id}")
    Call<Torneo> getTorneoById(
        @Header("Authorization") String Authorization, @Path("id") Long id
    );
}
