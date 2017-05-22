package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;
import android.widget.Toast;

import digitalhouse.android.a0317moacns1c_02.Entities.GeneralAPIData.Config;
import digitalhouse.android.a0317moacns1c_02.Entities.GeneralAPIData.Genre;
import digitalhouse.android.a0317moacns1c_02.Entities.GeneralAPIData.Genres;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 22/05/2017.
 */

/*
El objetivo de esta clase es obtener y contener datos generales que se obtienen de la API
y que van a ser utilizados durante toda la aplicación
Por un lado está la config que contiene la baseurl de las imagenes y los diferentes tamaños,
datos que se usan para construir la url final de donde obtener las imagenes.
También trae un array change_keys que todavía no sé para que se utiliza pero ya que está
lo guardo también.
Y como los resultados de DISCOVER traen los géneros de las películas como array de ids integer,
sin poner el nombre de los mismos, me pareció útil traer el listado de géneros y tenerlos en la
memoria, lo que va a servir para ponerlos en el listview usando sólo el resultado de DISCOVER,
sin tener que traer todos los datos de cada película (cuando se pide el detalle de la película
los géneros sí figuran con id y nombre)
En cuanto a la API_KEY, opino que debería ir acá y ser llamada en el resto del código
como GeneralAPIData.API_KEY
*/

public class GeneralAPIData {
    public static final String API_KEY = "91a255db2e1d0761c2dc886c0ed08709";
    private static GeneralAPIData instance;
    private TMDBClient client;
    private Config config; //configuración de imágenes y change keys
    private Genres genres; //lista de géneros de películas

    private GeneralAPIData() {
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }

    public static GeneralAPIData getInstance() {
        if (instance == null) instance = new GeneralAPIData();
        return instance;
    }

    public void loadData(final TMDBClient.APICallback apiCallback) {
        //obtener datos config
        Call<Config> configCall = client.obtainConfiguration(API_KEY);
        configCall.enqueue(new Callback<Config>() {
            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                config = response.body();
                String test = "img_base_url = " + config.getImages().getBase_url();
                apiCallback.onSuccess("Config OK : " + test);
            }

            @Override
            public void onFailure(Call<Config> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
        //obtener lista generos
        Call<Genres> genreCall = client.obtainGenreList(API_KEY);
        genreCall.enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                genres = response.body();
                String test = genres.getGenres()[0].getName() + ", " +
                        genres.getGenres()[1].getName() + ", " +
                        genres.getGenres()[2].getName() + ", etc.";
                apiCallback.onSuccess("Genres OK : " + test);
            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public Config getConfig() {
        return config;
    }
}
