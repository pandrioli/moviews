package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 27/06/2017.
 */

public class ListItemDTO extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String title;
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

    @Override
    public boolean equals(Object obj) {
        ListItemDTO listItemDTO = (ListItemDTO) obj;
        return listItemDTO.getId().equals(id) && listItemDTO.getType().equals(type);
    }
}
