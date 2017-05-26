package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Images.PeopleImagesAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 25/05/2017.
 */

public class PeopleCalls {
    public static void obtainImages(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<PeopleImagesAPI> call = client.obtainPeopleImages(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<PeopleImagesAPI>() {
            @Override
            public void onResponse(Call<PeopleImagesAPI> call, Response<PeopleImagesAPI> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PeopleImagesAPI> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
