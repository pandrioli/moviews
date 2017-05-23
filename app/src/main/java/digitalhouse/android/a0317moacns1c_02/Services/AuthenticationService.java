package digitalhouse.android.a0317moacns1c_02.Services;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.Authentication.RequestToken;

/**
 * Created by dh3 on 22/05/17.
 */

public interface AuthenticationService {

    void logIn();

    void logIn(TMDBClient.APICallback callback);

    void obtainRequestToken(TMDBClient.APICallback callback);

    boolean userIsLogged();
}
