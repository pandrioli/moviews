package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmObject;

/**
 * Created by Pablo on 26/06/2017.
 */

public class GenreDTO extends RealmObject {
    private Integer id;
    private String name;

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
