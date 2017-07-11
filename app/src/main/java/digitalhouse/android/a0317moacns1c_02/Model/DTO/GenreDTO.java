package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 26/06/2017.
 */

public class GenreDTO extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String name;
    private String type;

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
}
