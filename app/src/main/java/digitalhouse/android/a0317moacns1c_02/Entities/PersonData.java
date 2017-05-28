package digitalhouse.android.a0317moacns1c_02.Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;
import digitalhouse.android.a0317moacns1c_02.Services.ConfigurationService;

/**
 * Created by Pablo on 25/05/2017.
 */

public class PersonData {
    private Integer id;
    private String imdbId;
    private String name;
    private Date birthday;
    private Date deathday;
    private String placeOfBirth;
    private String biography;
    private Double popularity;
    private String profilePath;

    //obtener edad
    public Integer getAge() {
        return DateHelper.age(birthday);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDeathday() {
        return deathday;
    }

    public void setDeathday(Date deathday) {
        this.deathday = deathday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
