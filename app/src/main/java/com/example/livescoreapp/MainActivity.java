package com.example.livescoreapp;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScoresAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerViewScores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v3.football.api-sports.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        String apiKey = "a3c6e5ad984d971fb75d4b234d3bb7ad";
        String timezone = "Australia/Sydney";

        // The API call
        Call<LiveScoresResponse> call = apiService.getLiveScores(apiKey, timezone);
        call.enqueue(new Callback<LiveScoresResponse>() {
            @Override
            public void onResponse(Call<LiveScoresResponse> call, Response<LiveScoresResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("API Response", new Gson().toJson(response.body())); // Log the response
                    LiveScoresResponse liveScores = response.body();

                    if (liveScores != null && liveScores.response != null && !liveScores.response.isEmpty()) {
                        List<LiveScoresResponse.Response> scoresList = liveScores.response;

                        // Create and set adapter
                        adapter = new ScoresAdapter(scoresList);
                        recyclerView.setAdapter(adapter);
                    }
                    else {
                        Log.e("API Error", "Error Code: " + response.code() + " Message: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<LiveScoresResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }
}

