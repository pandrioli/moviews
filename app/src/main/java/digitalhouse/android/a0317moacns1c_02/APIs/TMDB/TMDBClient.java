package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.DAO.Genres.Genres;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieDetails;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Authentication.RequestToken;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Authentication.Session;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Configuration.ConfigAPI;
import digitalhouse.android.a0317moacns1c_02.DAO.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieImages;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieResults;
import digitalhouse.android.a0317moacns1c_02.DAO.Person.PersonDetails;
import digitalhouse.android.a0317moacns1c_02.DAO.Person.PersonImages;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieVideos;
import digitalhouse.android.a0317moacns1c_02.DAO.Person.PersonMovieCredits;
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
    Call<Genres> obtainGenreList(@Query("api_key") String API_KEY);

    // MOVIE

    //Obtener detalle de pelicula
    @GET("movie/{movie_id}?")
    Call<MovieDetails> obtainMovieDetails(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener imagenes de película
    @GET("movie/{movie_id}/images?")
    Call<MovieImages> obtainMovieImages(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener videos
    @GET("movie/{movie_id}?")
    Call<MovieVideos> obtainMovieVideos(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener créditos
    @GET("movie/{movie_id}/credits?")
    Call<Credits> obtainMovieCredits(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener lista de películas populares
    @GET("movie/popular?")
    Call<MovieResults> obtainPopularMovies(@Query("api_key") String API_KEY);
    //Obtener lista de películas en cartelera
    @GET("movie/now_playing?")
    Call<MovieResults> obtainNowPlayingMovies(@Query("api_key") String API_KEY);
    //Obtener lista de películas con próximo estreno
    @GET("movie/upcoming?")
    Call<MovieResults> obtainUpcomingMovies(@Query("api_key") String API_KEY);

    //PERSON

    //Obtener detalles de persona
    @GET("person/{person_id}?")
    Call<PersonDetails> obtainPersonDetails(@Path("person_id") String person_id, @Query("api_key") String API_KEY);

    //Obtener imagenes de persona
    @GET("person/{person_id}/images?")
    Call<PersonImages> obtainPersonImages(@Path("person_id") String person_id, @Query("api_key") String API_KEY);

    //Obtener peliculas en las que trabajo (movie credits)
    @GET("person/{person_id}/movie_credits?")
    Call<PersonMovieCredits> obtainPersonMovieCredits(@Path("person_id") String person_id, @Query("api_key") String API_KEY);

    //SEARCH
    @GET("search/movie?")
    Call<MovieResults> obtainMovies(@QueryMap Map<String, String> options);

    interface APICallback{
        void onSuccess(Object result);
    }


}
