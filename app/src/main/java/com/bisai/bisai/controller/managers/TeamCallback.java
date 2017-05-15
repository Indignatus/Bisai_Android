package com.bisai.bisai.controller.managers;

import com.bisai.bisai.model.Equipo;

import java.util.List;

/**
 * Created by sergi on 12/05/2017.
 */

public interface TeamCallback {
    void onSuccessTeams(List<Equipo> teamList);
    void onSuccessTeam(Equipo equipo);
    void onFailure(Throwable t);
}
