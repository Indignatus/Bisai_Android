package com.bisai.bisai.controller.services;


import com.bisai.bisai.model.UserDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Tania on 28/04/2016.
 */

public interface RegisterService {
    @POST("api/register/app")
    Call<Void> registerAccount(
            @Body UserDTO userDTO
    );



}
