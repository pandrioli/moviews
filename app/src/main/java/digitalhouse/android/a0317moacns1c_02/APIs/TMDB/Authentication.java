package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

/**
 * Created by Gregorio Martin on 21/5/2017.
 */

public interface Authentication {

     void getRequestToken(TMDBClient client, final TMDBClient.APICallback callback);

     void getSession(TMDBClient client, final TMDBClient.APICallback callback, String token);
}
