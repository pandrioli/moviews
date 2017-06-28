package digitalhouse.android.a0317moacns1c_02.Model.Person;

import java.io.Serializable;
import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Model.Media.Image;

/**
 * Created by Pablo on 25/05/2017.
 */

public class PersonImages implements Serializable {
    private Integer id;
    private ArrayList<Image> profiles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Image> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<Image> profiles) {
        this.profiles = profiles;
    }
}
