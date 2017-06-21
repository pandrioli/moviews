package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genres;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieOMDB;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieOmdb;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Pablo on 21/06/2017.
 */

public interface OMDBClient {
    String BASE_URL = "http://www.omdbapi.com/";
    String API_KEY = "digHouse";
    @GET("?")
    Call<MovieOMDB> obtainMovie (@QueryMap Map<String, String> queryMap);
    @GET("?")
    Call<SerieOmdb> obtainSerie (@QueryMap Map<String, String> queryMap);
}
