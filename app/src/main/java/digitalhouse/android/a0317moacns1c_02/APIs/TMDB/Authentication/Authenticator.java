package digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.AuthenticationEntities.RequestToken;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.AuthenticationEntities.Session;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.ServiceGenerator;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gregorio Martin on 20/5/2017.
 */

public class Authenticator {

    private static Authenticator instance = null;

    private static final String API_KEY = "91a255db2e1d0761c2dc886c0ed08709";

    private static String token;

    private static Session session;

    private TMDBClient client;

    protected Authenticator(){
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }

    public static Authenticator getInstance(){
        if(instance == null){
            instance = new Authenticator();
        }
        return instance;
    }

    public void getRequestToken(final TMDBClient.APICallback apiCallback)
    {
        Call<RequestToken> call = client.obtainRequestToken(API_KEY);

        call.enqueue(new Callback<RequestToken>() {
            @Override
            public void onResponse(Call<RequestToken> call, Response<RequestToken> response) {
                token = response.body().getRequest_token();
                apiCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RequestToken> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void getSession(final TMDBClient.APICallback apiCallback){
        Map<String, String> parameters = new HashMap<>();

        parameters.put("api_key", API_KEY);
        parameters.put("request_token", token);

        Call<Session> call = client.obtainSession(parameters);

        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                session = response.body();
                if(apiCallback != null)
                apiCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }

    public Session getSession(){
        if(session == null){
            getSession(null);
        }
        return session;
    }

}