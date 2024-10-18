package com.example.livescoreapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET("fixtures/live")
    Call<LiveScoresResponse> getLiveScores(
            @Header("x-apisports-key") String apiKey,
            @Query("timezone") String timezone
    );
}
