package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmObject;

/**
 * Created by Pablo on 27/06/2017.
 */

public class ListItemDTO extends RealmObject {
    private Integer id;
    private String year;
    private String genres;
    private String rating;
    private String imageURL;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
