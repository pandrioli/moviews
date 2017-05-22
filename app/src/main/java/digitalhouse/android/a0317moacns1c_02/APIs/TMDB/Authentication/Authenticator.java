package digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;

/**
 * Created by Gregorio Martin on 21/5/2017.
 */

public interface Authenticator {

     void getRequestToken(final TMDBClient.APICallback apiCallback);

     void getSession(final TMDBClient.APICallback apiCallback);

     Boolean userIsLogged();

     String getSessionID();
}
