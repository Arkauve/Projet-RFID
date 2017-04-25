package com.catpass.catpass;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Jordan on 04/04/2017.
 */

public interface HttpRequest {

    String URL = "http://192.168.43.191:8080/catpass/";

    @GET("authentification")
        Call<JsonObject> createUser(@Query("email") String email, @Query("password") String password);
}
