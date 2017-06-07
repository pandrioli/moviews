package digitalhouse.android.a0317moacns1c_02.Controller;

import java.io.Serializable;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;

/**
 * Created by Gregorio Martin on 7/6/2017.
 */

public abstract class ObtainerController implements Serializable {

    abstract void getPopular(TMDBClient.APICallback callback);

    abstract void getNowPlaying(TMDBClient.APICallback callback);

    abstract void getUpcoming(TMDBClient.APICallback callback);


}
