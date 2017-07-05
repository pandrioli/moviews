package digitalhouse.android.a0317moacns1c_02.Controller;

import android.content.Context;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.DAO.ConfigDAOInternet;
import digitalhouse.android.a0317moacns1c_02.DAO.ConfigDAOLocal;
import digitalhouse.android.a0317moacns1c_02.Helpers.NetworkHelper;
import digitalhouse.android.a0317moacns1c_02.Mappers.RealmStringMapper;
import digitalhouse.android.a0317moacns1c_02.Model.Configuration.Config;
import digitalhouse.android.a0317moacns1c_02.Model.Configuration.ImageConfig;

/**
 * Created by Pablo on 22/05/2017.
 */



public class ConfigController {
    private static ConfigController instance;
    private ConfigDAOInternet configDAOInternet;
    private ConfigDAOLocal configDAOLocal;
    private String imagesBaseURL;
    private ArrayList<String> posterSizes;
    private ArrayList<String> backdropSizes;
    private ArrayList<String> profileSizes;

    private ConfigController() {
        this.configDAOInternet = new ConfigDAOInternet();
        this.configDAOLocal = new ConfigDAOLocal();
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

    public static ConfigController getInstance() {
        if (instance == null) instance = new ConfigController();
        return instance;
    }

    public void loadConfigData(Context context, final ResultListener<Boolean> resultListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            //obtener datos de la API
            configDAOInternet.obtainConfigData(new ResultListener<Config>() {
                @Override
                public void finish(Config config) {
                    resultListener.finish(true);
                    setConfig(config.getImages());
                    configDAOLocal.saveConfigData(config.getImages());
                }
            });
        } else {
            //obtener datos locales
            ImageConfig imageConfig = configDAOLocal.obtainConfigData();
            if (imageConfig!=null) {
                setConfig(imageConfig);
                resultListener.finish(true);
            } else {
                resultListener.finish(false);
            }
        }
    }

    private void setConfig(ImageConfig imageConfig) {
        imagesBaseURL = imageConfig.getBase_url();
        posterSizes = RealmStringMapper.map(imageConfig.getPoster_sizes());
        backdropSizes = RealmStringMapper.map(imageConfig.getBackdrop_sizes());
        profileSizes = RealmStringMapper.map(imageConfig.getProfile_sizes());
    }
}
