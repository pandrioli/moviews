package digitalhouse.android.a0317moacns1c_02.Entities.API.Genres;

/**
 * Created by Pablo on 22/05/2017.
 */

public class GenreAPI {
    private Integer id;
    private String name;

    public GenreAPI(){

    }

    public GenreAPI(Integer id, String name) {
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
}
