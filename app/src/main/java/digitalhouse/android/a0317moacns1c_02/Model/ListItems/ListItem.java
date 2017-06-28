package digitalhouse.android.a0317moacns1c_02.Model.ListItems;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Pablo on 22/05/2017.
 */

public class ListItem implements Serializable {
    public static final String TYPE_MOVIE = "movie";
    public static final String TYPE_SERIE = "serie";
    public static final String TYPE_PERSON = "person";

    private Integer id;
    private String title;
    private String year;
    private String genres;
    private String rating;
    private String imageURL;
    private String type;

    public ListItem() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
