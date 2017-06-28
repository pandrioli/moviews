package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 27/06/2017.
 */

public class ListDTO extends RealmObject {
    public final static String MOVIES_POPULAR = "_movies_popular";
    public final static String MOVIES_NOWPLAYING = "_movies_nowplaying";
    public final static String MOVIES_UPCOMING = "_movies_upcoming";
    @PrimaryKey
    private String id;
    private Boolean isUserList;
    private RealmList<ListItemDTO> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getUserList() {
        return isUserList;
    }

    public void setUserList(Boolean userList) {
        isUserList = userList;
    }

    public RealmList<ListItemDTO> getList() {
        return list;
    }

    public void setList(RealmList<ListItemDTO> list) {
        this.list = list;
    }
}
