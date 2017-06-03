package com.bisai.bisai.controller.services;

import com.bisai.bisai.model.Torneo;

import java.util.List;

/**
 * Created by dam on 24/5/17.
 */

public interface TorneoCallback {
    void onSuccessTorneos(List<Torneo> torneoList);
    void onSuccessTorneo(Torneo torneo);
    void onFailure(Throwable t);


}
