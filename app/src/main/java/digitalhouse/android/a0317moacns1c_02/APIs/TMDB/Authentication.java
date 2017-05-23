package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import digitalhouse.android.a0317moacns1c_02.Entities.Authentication.RequestToken;
import digitalhouse.android.a0317moacns1c_02.Entities.Authentication.Session;

/**
 * Created by Gregorio Martin on 21/5/2017.
 */

public interface Authentication {

     void getRequestToken(TMDBClient client, final TMDBClient.APICallback callback);

     void getSession(TMDBClient client, final TMDBClient.APICallback callback, String token);
}
