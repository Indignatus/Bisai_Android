package com.bisai.bisai.controller.managers;

import com.bisai.bisai.model.Jugador;

/**
 * Created by sergi on 18/05/2017.
 */

public interface JugadorCallback {

    void onSuccessJugador(Jugador jugador);
    void onFailure(Throwable t);

}
