package com.example.myapplication.api;

import retrofit2.Retrofit;

public class ApiBuilder {

  private static ApiService apiService;

  public static ApiService getInstance() {
    if (apiService == null)
      apiService = providesRedditApiService(ApiAdapter.getRetrofitInstance());
    return apiService;
  }

  private static ApiService providesRedditApiService(Retrofit apiAdapter) {
    return apiAdapter.create(ApiService.class);
  }
}
