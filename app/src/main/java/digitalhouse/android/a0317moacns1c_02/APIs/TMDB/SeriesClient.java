package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Model.Series.Series;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Gregorio Martin on 6/6/2017.
 */

public interface SeriesClient {

    String API_KEY = "91a255db2e1d0761c2dc886c0ed08709";

    @GET("tv/popular")
    Call<Series> obtainPopulars(@QueryMap Map<String, String> parameters);
}
