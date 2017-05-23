package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.Entities.MovieResults.MovieResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 23/05/2017.
 */

public class MovieCalls {
    public static void obtainPopularMovies(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResults> call = client.obtainPopularMovies(TMDBClient.API_KEY);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
