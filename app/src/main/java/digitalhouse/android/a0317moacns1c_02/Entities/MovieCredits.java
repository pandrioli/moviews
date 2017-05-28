package digitalhouse.android.a0317moacns1c_02.Entities;

import java.util.ArrayList;

/**
 * Created by Pablo on 25/05/2017.
 */

public class MovieCredits {
    private ArrayList<ImageListItem> castList;
    private ArrayList<ImageListItem> crewList;

    public ArrayList<ImageListItem> getCastList() {
        return castList;
    }

    public void setCastList(ArrayList<ImageListItem> castList) {
        this.castList = castList;
    }

    public ArrayList<ImageListItem> getCrewList() {
        return crewList;
    }

    public void setCrewList(ArrayList<ImageListItem> crewList) {
        this.crewList = crewList;
    }
}
