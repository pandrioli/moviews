package digitalhouse.android.a0317moacns1c_02.Model.Configuration;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Model.DTO.RealmString;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 22/05/2017.
 */

// datos de las imagenes (base url y tamaños disponibles)

public class ImageConfig extends RealmObject {
    public static final String ID = "imageConfig";

    @PrimaryKey
    private String id = ID;
    private String base_url;
    private RealmList<RealmString> poster_sizes;
    private RealmList<RealmString> backdrop_sizes;
    private RealmList<RealmString> profile_sizes;

    public String getBase_url() {
        return base_url;
    }

    public RealmList<RealmString> getPoster_sizes() {
        return poster_sizes;
    }

    public RealmList<RealmString> getBackdrop_sizes() {
        return backdrop_sizes;
    }

    public RealmList<RealmString> getProfile_sizes() {
        return profile_sizes;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public void setPoster_sizes(RealmList<RealmString> poster_sizes) {
        this.poster_sizes = poster_sizes;
    }

    public void setBackdrop_sizes(RealmList<RealmString> backdrop_sizes) {
        this.backdrop_sizes = backdrop_sizes;
    }

    public void setProfile_sizes(RealmList<RealmString> profile_sizes) {
        this.profile_sizes = profile_sizes;
    }
}
