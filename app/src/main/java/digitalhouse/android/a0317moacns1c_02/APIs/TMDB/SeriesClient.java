package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImagesContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieResultsContainer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gregorio Martin on 6/6/2017.
 */

public interface SeriesClient {

    String API_KEY = "91a255db2e1d0761c2dc886c0ed08709";


    //Obtener series populares
    @GET("tv/popular?")
    Call<SerieResultsContainer> obtainPopularSeries(@Query("api_key") String API_KEY);
    @GET("tv/top_rated?")
    Call<SerieResultsContainer> obtainTopRatedSeries(@Query("api_key") String API_KEY);
    @GET("tv/airing_today?")
    Call<SerieResultsContainer> obtainAiringTodaySeries(@Query("api_key") String API_KEY);
    @GET("tv/{tv_id}?")
    Call<Serie> obtainDetails(@Path("tv_id") String serie_id, @Query("api_key") String API_KEY);
    @GET("tv/{tv_id}/images")
    Call<ImagesContainer> obtainImages(@Path("tv_id") String serie_id, @Query("api_key") String API_KEY);
    @GET("tv/{tv_id}/credits")
    Call<Credits> obtainCredits(@Path("tv_id") String serie_id, @Query("api_key") String API_KEY);

}
