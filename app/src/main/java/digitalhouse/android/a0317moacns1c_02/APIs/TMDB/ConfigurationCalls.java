package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Configuration.ConfigAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 23/05/2017.
 */

public class ConfigurationCalls {
    public static void obtainConfigData(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<ConfigAPI> call = client.obtainConfiguration(TMDBClient.API_KEY);
        call.enqueue(new Callback<ConfigAPI>() {
            @Override
            public void onResponse(Call<ConfigAPI> call, Response<ConfigAPI> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ConfigAPI> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
