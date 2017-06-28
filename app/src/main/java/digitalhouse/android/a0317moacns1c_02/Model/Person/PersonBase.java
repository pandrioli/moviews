package digitalhouse.android.a0317moacns1c_02.Model.Person;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Gregorio Martin on 11/6/2017.
 */

public class PersonBase implements Serializable {

    protected Integer id;
    protected Integer gender;
    protected String profile_path;
    protected String name;

    public PersonBase(){

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
