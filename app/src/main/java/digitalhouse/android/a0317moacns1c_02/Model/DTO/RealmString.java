package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 26/06/2017.
 */

public class RealmString extends RealmObject {
    @PrimaryKey
    private String value;

    public RealmString(String value) {
        this.value = value;
    }

    public RealmString() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
