package com.example.f1driverapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DriverApi {
    String BASE_URL = "http://ergast.com/api/f1/";

    @GET("{year}/drivers.json")
    Call<DriverResponse> getDrivers(@Path("year") int year);
}
