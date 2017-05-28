package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Authentication.RequestToken;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Authentication.Session;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Configuration.ConfigAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Credits.CreditsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Genres.GenresAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Images.MovieImagesAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Images.PeopleImagesAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieDetails.MovieDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieResults.MovieResultsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Videos.VideosAPI;
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

    /* Explicacion:

    @GET para consumir un método de la API que devuelva algo, osea que vos esperes una respuesta
    lo que va entre parentesis después del @GET es la URL del método sin la BASE URL, esta esta en otra clase
    @Query significa que lo que vas a poner es un parametro, uno solo. Lo que está entre paréntesis al lado ("api_key") es
    el nombre de este parámetro. Después viene el tipo y nombre que recibe el método por este parametro que seteamos antes (poniendo
    @Query("api_key") en este caso el tipo y nombre son String y API_KEY respectivamente
    Más detalles -> https://api.themoviedb.org/3/authentication/token/new?api_key=91a255db2e1d0761c2dc886c0ed08709
    https://api.themoviedb.org/3/ es la BASE URL porque siempre va a ser igual para cualquier método de la API que quieras consumir
    authentication/token/new? es el método al cual le queres pegar -> lo que va entre parentesis despues del @GET
    api_key= es el nombre del parametro que le tenes que mandar al método lo que va entre parentesis, SIN EL IGUAL, después del @Query
    91a255db2e1d0761c2dc886c0ed08709 es el valor del parametro que le tenes que mandar al método como parametro, por ejenplo ->
    Call<RequestToken> callRequestToken = client.obtainRequestToken(91a255db2e1d0761c2dc886c0ed08709);

    Para más información mirar la clase Authentication por el momento.
     */
    @GET("authentication/token/new?")
    Call<RequestToken> obtainRequestToken(@Query("api_key") String API_KEY);


    /*
    En el caso en que un método de la API reciba varios parametros tenes que crear un HashMap<String, String>
    donde la clave sea el nombre del paramétro y el valor sea el valor del parametro
    por ejemplo: https://api.themoviedb.org/3/authentication/session/new?api_key=91a255db2e1d0761c2dc886c0ed08709&request_token=dc37f5982abc0fb5ad15c0a0aeddbb5cb3a4ee34

    Map<String, String> parameters = new HashMap<>();
    parameters.put("api_key", API_KEY);
    parameters.put("request_token", token);

    api_key -> es el primer parametro
    91a255db2e1d0761c2dc886c0ed08709 -> es el primer valor

    request_token -> es el segundo parametro
    dc37f5982abc0fb5ad15c0a0aeddbb5cb3a4ee34 -> es el segundo valor

    los valores no se deberían hardcodear.
     */

    @GET("authentication/session/new?")
    Call<Session> obtainSession(@QueryMap Map<String, String> options);

    @GET("search/movie?")
    Call<MovieResultsAPI> obtainMovies(@QueryMap Map<String, String> options);

    //Obtener la configuracion de las imagenes y un array change_keys que todavía no sé para qué es
    //pero por las dudas lo traigo
    @GET("configuration?")
    Call<ConfigAPI> obtainConfiguration(@Query("api_key") String API_KEY);
    //Obtener lista de géneros de películas
    @GET("genre/movie/list?")
    Call<GenresAPI> obtainGenreList(@Query("api_key") String API_KEY);

    //Obtener detalle de pelicula
    @GET("movie/{movie_id}?")
    Call<MovieDetailsAPI> obtainMovieDetails(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener imagenes de película
    @GET("movie/{movie_id}?")
    Call<MovieImagesAPI> obtainMovieImages(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener videos
    @GET("movie/{movie_id}?")
    Call<VideosAPI> obtainMovieVideos(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener créditos
    @GET("movie/{movie_id}/credits?")
    Call<CreditsAPI> obtainMovieCredits(@Path("movie_id") String movie_id, @Query("api_key") String API_KEY);

    //Obtener imagenes de persona
    @GET("person/{person_id}/images?")
    Call<PeopleImagesAPI> obtainPeopleImages(@Path("person_id") String person_id, @Query("api_key") String API_KEY);

    //Obtener lista de películas populares
    @GET("movie/popular?")
    Call<MovieResultsAPI> obtainPopularMovies(@Query("api_key") String API_KEY);
    //Obtener lista de películas en cartelera
    @GET("movie/now_playing?")
    Call<MovieResultsAPI> obtainNowPlayingMovies(@Query("api_key") String API_KEY);
    //Obtener lista de películas con próximo estreno
    @GET("movie/upcoming?")
    Call<MovieResultsAPI> obtainUpcomingMovies(@Query("api_key") String API_KEY);



    /*
    Hay casos en los que cuando llamas a algun método de la API este tarda más de lo que el programa
    en devolverte información. Como los llamados a estos servicios son Asincronicos todo lo que este
    debajo del llamado se va a ejecutar antes de que se recuperen datos, por lo tanto es MUY PROBABLE
    que se devuelvan objetos Nulos (No se obtuvieron datos para llenarlos). En esos casos cuando
    creamos nuevas llamadas a la api ponemos que uno de los parametros de estos métodos sea la
    interfaz que está abajo. Y al llamar al método creamos una nueva interfaz como parametro, dentro
    de esta ponemos lo que queremos que haga el método una vez obtenga datos, así evitamos errores
     Igualmente con esto solo no alcanza, falta agregar al método que consume realmente el servicio
     de la API un new Callback<T> dentro del método enqueue, este Callback nos obliga a sobreescribir
     los métodos de onResponse y onFailure. En el onResponse agregamos el método
     apiCallback.onSucces(Entidad Devuelta)
     Para más info mirar la clase Authentication, métodos: getSession y getRequestToken
     */

    interface APICallback{
        void onSuccess(Object result);
    }


}
