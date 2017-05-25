package digitalhouse.android.a0317moacns1c_02.Entities.API.Configuration;

import java.util.ArrayList;

/**
 * Created by Pablo on 22/05/2017.
 */

// datos de las imagenes (base url y tamaños disponibles)

public class ImageConfigAPI {
    private String base_url;
    private ArrayList<String> poster_sizes;

    public String getBase_url() {
        return base_url;
    }

    public ArrayList<String> getPoster_sizes() {
        return poster_sizes;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public void setPoster_sizes(ArrayList<String> poster_sizes) {
        this.poster_sizes = poster_sizes;
    }
}
