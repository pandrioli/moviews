package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 27/06/2017.
 */

public class ListDTO extends RealmObject {
    public final static String MOVIES_POPULAR = "appList:movies_popular";
    public final static String MOVIES_LATEST = "appList:movies_latest";
    public final static String MOVIES_UPCOMING = "appList:movies_upcoming";
    public final static String SERIES_POPULAR = "appList:series_popular";
    public final static String SERIES_LATEST = "appList:series_latest";
    public final static String SERIES_UPCOMING = "appList:series_upcoming";
    public final static String SERIES_AIRING_TODAY = "appList:airing_today";
    public final static String FAVORITES = "appList:favorites";
    public final static String BOOKMARKS = "appList:bookmarks";
    public final static String USER_LIST_TEST = "userListTest";

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

    public Boolean getIsUserList() {
        return isUserList;
    }

    public void setIsUserList(Boolean userList) {
        isUserList = userList;
    }

    public RealmList<ListItemDTO> getList() {
        return list;
    }

    public void setList(RealmList<ListItemDTO> list) {
        this.list = list;
    }
}
