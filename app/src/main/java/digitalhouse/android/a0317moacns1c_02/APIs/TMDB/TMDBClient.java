package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Authentication.RequestToken;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Authentication.Session;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Configuration.ConfigAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Credits.CreditsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Misc.GenresAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieImagesAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Person.PersonDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Person.PersonImagesAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieResultsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Media.VideosAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Person.PersonMovieCreditsAPI;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Gregorio Martin on 20/5/2017.
 */

public interface TMDBClient {

    String API_KEY = "91a255db2e1d0761c2dc886c0ed08709";

    @GET("authentication/token/new?")
    Call<RequestToken> obtainRequestToken(@Query("api_key") String API_KEY);

    @GET("authentication/session/new?")
    Call<Session> obtainSession(@QueryMap Map<String, String> options);

    //Obtener la configuracion de las imagenes y un array change_keys que todavía no sé para qué es
    //pero por las dudas lo traigo
    @GET("configuration?")
    Call<ConfigAPI> obtainConfiguration(@Query("api_key") String API_KEY);
    //Obtener lista de géneros de películas
    @GET("genre/movie/list?")
    Call<GenresAPI> obtainGenreList(@Query("api_key") String API_KEY);

    // MOVIE

    //Obtener detalle de pelicula
    @GET("movie/{movie_id}?")
    Call<MovieDetailsAPI> obtainMovieDetails(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener imagenes de película
    @GET("movie/{movie_id}/images?")
    Call<MovieImagesAPI> obtainMovieImages(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener videos
    @GET("movie/{movie_id}?")
    Call<VideosAPI> obtainMovieVideos(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener créditos
    @GET("movie/{movie_id}/credits?")
    Call<CreditsAPI> obtainMovieCredits(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener lista de películas populares
    @GET("movie/popular?")
    Call<MovieResultsAPI> obtainPopularMovies(@Query("api_key") String API_KEY);
    //Obtener lista de películas en cartelera
    @GET("movie/now_playing?")
    Call<MovieResultsAPI> obtainNowPlayingMovies(@Query("api_key") String API_KEY);
    //Obtener lista de películas con próximo estreno
    @GET("movie/upcoming?")
    Call<MovieResultsAPI> obtainUpcomingMovies(@Query("api_key") String API_KEY);

    //PERSON

    //Obtener detalles de persona
    @GET("person/{person_id}?")
    Call<PersonDetailsAPI> obtainPersonDetails(@Path("person_id") String person_id, @Query("api_key") String API_KEY);

    //Obtener imagenes de persona
    @GET("person/{person_id}/images?")
    Call<PersonImagesAPI> obtainPersonImages(@Path("person_id") String person_id, @Query("api_key") String API_KEY);

    //Obtener peliculas en las que trabajo (movie credits)
    @GET("person/{person_id}/movie_credits?")
    Call<PersonMovieCreditsAPI> obtainPersonMovieCredits(@Path("person_id") String person_id, @Query("api_key") String API_KEY);

    //SEARCH
    @GET("search/movie?")
    Call<MovieResultsAPI> obtainMovies(@QueryMap Map<String, String> options);

    interface APICallback{
        void onSuccess(Object result);
    }


}
