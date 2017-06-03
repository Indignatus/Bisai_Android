package com.bisai.bisai.controller.services;

import android.util.Log;

import com.bisai.bisai.controller.managers.TorneoCallback;
import com.bisai.bisai.controller.managers.UserLoginManager;
import com.bisai.bisai.controller.util.CustomProperties;
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
    private static com.bisai.bisai.controller.managers.TorneoManager ourInstance;
    private List<Torneo> torneoList;
    private Retrofit retrofit;
    private TorneoService torneoService;

    private TorneoManager(){
        retrofit = new Retrofit.Builder().baseUrl(CustomProperties.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        torneoService = retrofit.create(TorneoService.class);
    }

    public static com.bisai.bisai.controller.managers.TorneoManager getInstance(){
        if(ourInstance == null){
            ourInstance = new com.bisai.bisai.controller.managers.TorneoManager();
        }
        return ourInstance;
    }

    public synchronized void getAllTorneos(final com.bisai.bisai.controller.managers.TorneoCallback torneoCallback){
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

}
