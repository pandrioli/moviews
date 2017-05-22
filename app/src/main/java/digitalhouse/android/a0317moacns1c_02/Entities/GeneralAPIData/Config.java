package digitalhouse.android.a0317moacns1c_02.Entities.GeneralAPIData;

/**
 * Created by Pablo on 21/05/2017.
 */

// configuracion de imagenes y change keys

public class Config {
    private ImageConfig images; // la API devuelve un objeto con la config de las imagenes
    private String[] change_keys;

    public ImageConfig getImages() {
        return images;
    }

    public void setImages(ImageConfig images) {
        this.images = images;
    }
}
