package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Person.PersonDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Person.PersonImagesAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Person.PersonMovieCreditsAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 25/05/2017.
 */

public class PersonCalls {

    public static void obtainDetails(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<PersonDetailsAPI> call = client.obtainPersonDetails(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<PersonDetailsAPI>() {
            @Override
            public void onResponse(Call<PersonDetailsAPI> call, Response<PersonDetailsAPI> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PersonDetailsAPI> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


    public static void obtainImages(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<PersonImagesAPI> call = client.obtainPersonImages(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<PersonImagesAPI>() {
            @Override
            public void onResponse(Call<PersonImagesAPI> call, Response<PersonImagesAPI> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PersonImagesAPI> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void obtainMovieCredits(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<PersonMovieCreditsAPI> call = client.obtainPersonMovieCredits(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<PersonMovieCreditsAPI>() {
            @Override
            public void onResponse(Call<PersonMovieCreditsAPI> call, Response<PersonMovieCreditsAPI> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PersonMovieCreditsAPI> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
