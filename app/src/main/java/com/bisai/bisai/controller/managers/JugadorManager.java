package com.bisai.bisai.controller.managers;

import android.util.Log;

import com.bisai.bisai.controller.services.JugadorService;
import com.bisai.bisai.controller.util.CustomProperties;
import com.bisai.bisai.model.Equipo;
import com.bisai.bisai.model.Jugador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sergi on 19/05/2017.
 */

public class JugadorManager {
private static JugadorManager ourInstance;
    private Jugador jugador;
    private Retrofit retrofit;
    private JugadorService jugadorService;


    private JugadorManager(){
        retrofit = new Retrofit.Builder().baseUrl(CustomProperties.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        jugadorService = retrofit.create(JugadorService.class);
    }

    public static JugadorManager getInstance(){
        if(ourInstance == null){
            ourInstance = new JugadorManager();
        }
        return ourInstance;
    }

    // GET

    public synchronized void getJugadorByName(final JugadorCallback jugadorCallback, String nikName){

        Call<Jugador> call = jugadorService.getJugadorByLogin(UserLoginManager.getInstance().getBearerToken(), nikName);
        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {

                Jugador jugador = response.body();
                int code = response.code();
                if(code == 200 || code == 201){
                    jugadorCallback.onSuccessJugador(jugador);
                }else {
                    jugadorCallback.onFailure(new Throwable("Error"+ code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Log.e("JugadorManager->", t.toString());
                jugadorCallback.onFailure(t);
            }




        });


    }




}
