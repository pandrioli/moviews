package digitalhouse.android.a0317moacns1c_02.Entities.API.Person;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Media.ImageItemAPI;

/**
 * Created by Pablo on 25/05/2017.
 */

public class PersonImagesAPI {
    private Integer id;
    private ArrayList<ImageItemAPI> profiles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<ImageItemAPI> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<ImageItemAPI> profiles) {
        this.profiles = profiles;
    }
}
