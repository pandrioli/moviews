package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Entities.Authentication.RequestToken;
import digitalhouse.android.a0317moacns1c_02.Entities.Authentication.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gregorio Martin on 20/5/2017.
 */

public class AuthenticationImpl implements Authentication {

    private TMDBClient client;

    private static AuthenticationImpl instance;

    public static AuthenticationImpl getInstance(){
        if(instance == null){
            instance = new AuthenticationImpl();
        }
        return instance;
    }

    public AuthenticationImpl() {
        //Transformo la interfaz en un objeto
        //this.client = ServiceGenerator.createService(TMDBClient.class);
    }

    public void getRequestToken(TMDBClient client, final TMDBClient.APICallback callback) {

        Call<RequestToken> call = client.obtainRequestToken(TMDBClient.API_KEY);

        call.enqueue(new Callback<RequestToken>() {
            @Override
            public void onResponse(Call<RequestToken> call, Response<RequestToken> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RequestToken> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void getSession(TMDBClient client, final TMDBClient.APICallback callback, String token) {

        final Session[] session = new Session[1];

        Map<String, String> parameters = new HashMap<>();

        parameters.put("api_key", TMDBClient.API_KEY);
        parameters.put("request_token", token);

        Call<Session> call = client.obtainSession(parameters);

        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}