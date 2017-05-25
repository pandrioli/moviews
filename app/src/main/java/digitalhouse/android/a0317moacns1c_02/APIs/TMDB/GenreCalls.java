package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Genres.GenresAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 23/05/2017.
 */

public class GenreCalls {
    public static void obtainGenres(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<GenresAPI> call = client.obtainGenreList(TMDBClient.API_KEY);
        call.enqueue(new Callback<GenresAPI>() {
            @Override
            public void onResponse(Call<GenresAPI> call, Response<GenresAPI> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GenresAPI> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
