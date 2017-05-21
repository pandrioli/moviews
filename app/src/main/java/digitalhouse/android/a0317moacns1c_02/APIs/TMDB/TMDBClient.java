package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.AuthenticationEntities.RequestToken;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.AuthenticationEntities.Session;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Gregorio Martin on 20/5/2017.
 */

public interface TMDBClient {

    @GET("authentication/token/new?")
    Call<RequestToken> obtainRequestToken(@Query("api_key") String API_KEY);

    @GET("authentication/session/new?")
    Call<Session> obtainSession(@QueryMap Map<String, String> options);


    public interface APICallback{
        void onSuccess(Object result);
    }


}
