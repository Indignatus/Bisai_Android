package com.bisai.bisai.controller.managers;

import android.content.Context;
import android.util.Log;

import com.bisai.bisai.controller.services.JugadorService;
import com.bisai.bisai.model.Jugador;
import com.bisai.bisai.model.UserToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginManager implements JugadorCallback {
    private static UserLoginManager ourInstance;
    private UserToken userToken;
    private Context context;
    private String bearerToken;
    private JugadorService jugadorService;
    // Declaramos el objeto jugador, será utilizado durante la sesión
    private Jugador jugador;
    private LoginCallback loginCallback;

    private UserLoginManager() {
    }

    public Jugador getJugador() {
        return jugador;
    }

    public static UserLoginManager getInstance() {
        if(ourInstance == null){
            ourInstance = new UserLoginManager();
        }

        return ourInstance;
    }

    public synchronized void performLogin(final String username, String password, final LoginCallback loginCallback){
        Call<UserToken> call =  UserTokenManager.getInstance().getUserToken(username, password);

        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                Log.i("UserLoginManager ", " performtaks->call.enqueue->onResponse res: " + response.body());
                userToken = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    bearerToken = "Bearer " + userToken.getAccessToken();
                    UserLoginManager.this.loginCallback = loginCallback;

                    // Una vez que ha sido validado y recogio el token, recojemos el Jugador
                   JugadorManager.getInstance().getJugadorByName(UserLoginManager.this, username);







                } else {
                    loginCallback.onFailure(new Throwable("ERROR " + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                Log.e("UserLoginManager ", " performtaks->call.enqueue->onResponse err: " + t.toString());
                loginCallback.onFailure(t);
            }
        });
    }

    public UserToken getUserToken(){
        return userToken;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    @Override
    public void onSuccessJugador(Jugador jugador) {
        this.jugador = jugador;
        loginCallback.onSuccess(userToken);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("UserLoginManager ", " performtaks->call.enqueue->onResponse err: " + t.toString());
    }
}