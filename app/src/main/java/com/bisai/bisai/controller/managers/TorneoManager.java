package com.bisai.bisai.controller.managers;

import android.util.Log;

import com.bisai.bisai.controller.services.TorneoService;
import com.bisai.bisai.controller.util.CustomProperties;
import com.bisai.bisai.model.Equipo;
import com.bisai.bisai.model.Torneo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dam on 24/5/17.
 */

public class TorneoManager {
    private static TorneoManager ourInstance;
    private List<Torneo> torneoList;
    private Retrofit retrofit;
    private TorneoService torneoService;

    public TorneoManager(){
        retrofit = new Retrofit.Builder().baseUrl(CustomProperties.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        torneoService = retrofit.create(TorneoService.class);
    }

    public static TorneoManager getInstance(){
        if(ourInstance == null){
            ourInstance = new TorneoManager();
        }
        return ourInstance;
    }

    public synchronized void getAllTorneos(final TorneoCallback torneoCallback){
        Call<List<Torneo>> call = torneoService.getAllTorneos(UserLoginManager.getInstance().getBearerToken());
        call.enqueue(new Callback<List<Torneo>>() {
            @Override
            public void onResponse(Call<List<Torneo>> call, Response<List<Torneo>> response) {
                torneoList = response.body();
                int code = response.code();
                if(code == 200 || code == 201){
                    torneoCallback.onSuccessTorneos(torneoList);
                }else{
                    torneoCallback.onFailure(new Throwable("Error" + code + " , " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Torneo>> call, Throwable t) {
                Log.e("TorneoManager->", t.toString());
                torneoCallback.onFailure(t);
            }
        });
    }
    public synchronized void getBusquedaTorneos(final TorneoCallback torneoCallback){
        Call<List<Torneo>> call = torneoService.getBusquedaTorneos( UserLoginManager.getInstance().getJugador().getId(), UserLoginManager.getInstance().getBearerToken());
        call.enqueue(new Callback<List<Torneo>>() {
            @Override
            public void onResponse(Call<List<Torneo>> call, Response<List<Torneo>> response) {
                torneoList = response.body();
                int code = response.code();
                if(code == 200 || code == 201){
                    torneoCallback.onSuccessTorneos(torneoList);
                }else{
                    torneoCallback.onFailure(new Throwable("Error" + code + " , " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Torneo>> call, Throwable t) {
                Log.e("TorneoManager->", t.toString());
                torneoCallback.onFailure(t);
            }
        });
    }

    public synchronized void getTorneoPendienteJugador(final TorneoCallback torneoCallback){
        Call<List<Torneo>> call = torneoService.getTorneoPendienteJugador(UserLoginManager.getInstance().getBearerToken(), UserLoginManager.getInstance().getJugador().getId());
        call.enqueue(new Callback<List<Torneo>>() {
            @Override
            public void onResponse(Call<List<Torneo>> call, Response<List<Torneo>> response) {
                torneoList = response.body();
                int code = response.code();
                if(code == 200 || code == 201){
                    torneoCallback.onSuccessTorneos(torneoList);
                }else{
                    torneoCallback.onFailure(new Throwable("Error" + code + " , " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Torneo>> call, Throwable t) {
                Log.e("TorneoManager->", t.toString());
                torneoCallback.onFailure(t);
            }
        });
    }

    public Torneo getTorneo(String id){
        for (Torneo torneo : torneoList){
            if(torneo.getId().toString().equals(id)){
                return torneo;
            }
        }
        return null;
    }

    public synchronized void getTorneoById(long id, final TorneoCallback torneoCallback){
        Call<Torneo> call = torneoService.getTorneoById(UserLoginManager.getInstance().getBearerToken(), id);
        call.enqueue(new Callback<Torneo>() {
            @Override
            public void onResponse(Call<Torneo> call, Response<Torneo> response) {
                Torneo torneo = response.body();
                int code = response.code();
                if(code == 200 || code == 201){
                    torneoCallback.onSuccessTorneo(torneo);
                }else {
                    torneoCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Torneo> call, Throwable t) {
                Log.e("TorneoManager->",t.toString());
                torneoCallback.onFailure(t);
            }
        });
    }

    public synchronized void addEquipoTorneo (final TorneoCallback torneoCallback, long idTorneo, long idEquipo) {
        Call<Torneo> call = torneoService.addEquipoTorneo(idTorneo, idEquipo, UserLoginManager.getInstance().getBearerToken());
        call.enqueue(new Callback<Torneo>() {
            @Override
            public void onResponse(Call<Torneo> call, Response<Torneo> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    torneoCallback.onSuccessTorneo(response.body());

                } else {
                    torneoCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Torneo> call, Throwable t) {
            }
        });
    }

}
