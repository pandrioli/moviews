package digitalhouse.android.a0317moacns1c_02.Model.General;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by dh3 on 24/05/17.
 */

public class Country implements Serializable {
    private String iso_3166_1;
    private String name;


    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
