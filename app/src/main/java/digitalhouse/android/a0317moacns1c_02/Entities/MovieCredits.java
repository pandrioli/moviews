package digitalhouse.android.a0317moacns1c_02.Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Pablo on 25/05/2017.
 */

public class MovieCredits implements Serializable {
    public static final String tag = "movieCredits";
    private ArrayList<ImageListItem> castList;
    private ArrayList<CrewListItem> crewList;

    public ArrayList<ImageListItem> getCastList() {
        return castList;
    }

    public void setCastList(ArrayList<ImageListItem> castList) {
        this.castList = castList;
    }

    public ArrayList<CrewListItem> getCrewList() {
        return crewList;
    }

    public void setCrewList(ArrayList<CrewListItem> crewList) {
        this.crewList = crewList;
    }
}
