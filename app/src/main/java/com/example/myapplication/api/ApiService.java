package com.example.myapplication.api;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

  @GET("api/data_colector/create")
  Observable<JsonObject> getData(
      @Query("email") String email,
      @Query("ping") String ping,
      @Query("sign_level") Integer signLevel,
      @Query("link_speed") Integer linkSpeed);
}
