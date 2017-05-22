package digitalhouse.android.a0317moacns1c_02.Entities.GeneralAPIData;

/**
 * Created by Pablo on 22/05/2017.
 */

// datos de las imagenes (base url y tamaños disponibles)

public class ImageConfig {
    private String base_url;
    private String[] poster_sizes;

    public String getBase_url() {
        return base_url;
    }

    public String[] getPoster_sizes() {
        return poster_sizes;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public void setPoster_sizes(String[] poster_sizes) {
        this.poster_sizes = poster_sizes;
    }
}
