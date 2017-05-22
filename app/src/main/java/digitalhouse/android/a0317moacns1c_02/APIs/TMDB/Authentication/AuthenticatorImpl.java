package digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Entities.Authentication.RequestToken;
import digitalhouse.android.a0317moacns1c_02.Entities.Authentication.Session;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gregorio Martin on 20/5/2017.
 */

public class AuthenticatorImpl implements Authenticator {

    private static final String API_KEY = "91a255db2e1d0761c2dc886c0ed08709";

    private static RequestToken token;

    private static Session session;

    private TMDBClient client;

    public AuthenticatorImpl() {
        //Transformo la interfaz en un objeto
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }

    public void getRequestToken(final TMDBClient.APICallback apiCallback) {

        if(token != null){
            apiCallback.onSuccess(token);
            return;
        }

        Call<RequestToken> call = client.obtainRequestToken(API_KEY);

        call.enqueue(new Callback<RequestToken>() {
            @Override
            public void onResponse(Call<RequestToken> call, Response<RequestToken> response) {
                token = response.body();
                apiCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RequestToken> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void getSession(final TMDBClient.APICallback apiCallback) {

        if (token == null) {
            throw new NullPointerException("RequestToken es nulo. Antes de pedir la session se debe pedir el RequestToken");
        }

        if (session != null) {
            apiCallback.onSuccess(session);
            return;
        }

        Map<String, String> parameters = new HashMap<>();

        parameters.put("api_key", API_KEY);
        parameters.put("request_token", token.getRequest_token());

        Call<Session> call = client.obtainSession(parameters);

        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                session = response.body();
                apiCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }

    public Boolean userIsLogged(){
        return session != null ? true : false;
    }

    public String getSessionID(){
        return session.getSession_id();
    }

}