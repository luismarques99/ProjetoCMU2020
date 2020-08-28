package com.cmuteam.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ZomatoApi {

    @GET("establishments")
    Call<List<Establishment>> getNearbyRestaurants(@Header("user-key") String userKey, @Query("lat")Double lat,
                                                   @Query("lon")Double lon);
}
