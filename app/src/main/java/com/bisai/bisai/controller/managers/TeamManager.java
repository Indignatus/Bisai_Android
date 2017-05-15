package com.bisai.bisai.controller.managers;

import android.util.Log;

import com.bisai.bisai.controller.services.TeamService;
import com.bisai.bisai.controller.util.CustomProperties;
import com.bisai.bisai.model.Equipo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DAM on 27/4/17.
 */

public class TeamManager {
    private static TeamManager ourInstance;
    private List<Equipo> equipoList;
    private Retrofit retrofit;
    private TeamService teamService;

    private TeamManager(){
        retrofit = new Retrofit.Builder().baseUrl(CustomProperties.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        teamService =retrofit.create(TeamService.class);
    }

    public static TeamManager getInstance(){
        if(ourInstance == null){
            ourInstance = new TeamManager();
        }
        return ourInstance;
    }

    // GET

    public synchronized void getAllTeams(final TeamCallback teamCallback){
        Call<List<Equipo>> call = teamService.getAllTeams(UserLoginManager.getInstance().getBearerToken());
        call.enqueue(new Callback<List<Equipo>>() {
            @Override
            public void onResponse(Call<List<Equipo>> call, Response<List<Equipo>> response) {
                equipoList = response.body();
                int code = response.code();
                if(code == 200 || code == 201){
                    teamCallback.onSuccessTeams(equipoList);
                }else {
                    teamCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Equipo>> call, Throwable t) {
                Log.e("TeamManager->",t.toString());
                teamCallback.onFailure(t);
            }
        });
    }

    public Equipo getEquipo(String id){
        for (Equipo equipo : equipoList){
            if(equipo.getId().toString().equals(id)){
                return equipo;
            }
        }
        return null;
    }

    public synchronized void getEquipoById(long id, final TeamCallback teamCallback){

        Call<Equipo> call = teamService.getEquipoById(UserLoginManager.getInstance().getBearerToken(), id);
        call.enqueue(new Callback<Equipo>() {
            @Override
            public void onResponse(Call<Equipo> call, Response<Equipo> response) {
                Equipo equipo = response.body();
                int code = response.code();
                if(code == 200 || code == 201){
                    teamCallback.onSuccessTeam(equipo);
                }else {
                    teamCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Equipo> call, Throwable t) {
                Log.e("TeamManager->",t.toString());
                teamCallback.onFailure(t);
            }
        });

    }

    //POST



    //PUT



}