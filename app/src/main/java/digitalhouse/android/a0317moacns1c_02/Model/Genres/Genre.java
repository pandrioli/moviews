package digitalhouse.android.a0317moacns1c_02.Model.Genres;

import java.io.Serializable;

/**
 * Created by Pablo on 22/05/2017.
 */

public class Genre implements Serializable {
    public static final String TYPE_MOVIES = "movies";
    public static final String TYPE_SERIES = "series";

    private Integer id;
    private String name;
    private String type;

    public Genre(){

    }

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        Genre genre = (Genre)obj;
        return genre.getId().equals(id);
    }
}

