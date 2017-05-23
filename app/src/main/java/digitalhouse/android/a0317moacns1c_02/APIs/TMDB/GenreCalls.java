package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.Entities.Genres.Genres;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 23/05/2017.
 */

public class GenreCalls {
    public static void obtainGenres(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<Genres> call = client.obtainGenreList(TMDBClient.API_KEY);
        call.enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
