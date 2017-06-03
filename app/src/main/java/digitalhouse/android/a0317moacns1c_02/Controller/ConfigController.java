package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Model.DAO.ConfigDAO;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Configuration.Config;

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
como ConfigurationServiceImpl.API_KEY
*/

public class ConfigController {
    private static ConfigController instance;
    private ConfigDAO configDAO;
    private String imagesBaseURL;
    private ArrayList<String> posterSizes;
    private ArrayList<String> backdropSizes;
    private ArrayList<String> profileSizes;
    private ArrayList<String> changeKeys;


    private ConfigController() {
        this.configDAO = new ConfigDAO();
    }

    public String getImagesBaseURL() {
        return imagesBaseURL;
    }

    public ArrayList<String> getPosterSizes() {
        return posterSizes;
    }

    public ArrayList<String> getBackdropSizes() { return backdropSizes; }

    public ArrayList<String> getProfileSizes() {
        return profileSizes;
    }

    public ArrayList<String> getChangeKeys() {
        return changeKeys;
    }

    public static ConfigController getInstance() {
        if (instance == null) instance = new ConfigController();
        return instance;
    }

    public void loadConfigData(final TMDBClient.APICallback callback) {
        //obtener datos config
        configDAO.obtainConfigData(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                Config config = (Config) result;
                imagesBaseURL = config.getImages().getBase_url();
                posterSizes = config.getImages().getPoster_sizes();
                backdropSizes = config.getImages().getBackdrop_sizes();
                profileSizes = config.getImages().getProfile_sizes();
                changeKeys = config.getChange_keys();
                callback.onSuccess("OK");
            }
        });
    }
}
