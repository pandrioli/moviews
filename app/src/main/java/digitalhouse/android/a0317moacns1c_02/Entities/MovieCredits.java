package digitalhouse.android.a0317moacns1c_02.Entities;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Services.MovieService;

/**
 * Created by Pablo on 25/05/2017.
 */

public class MovieCredits {
    private ArrayList<PersonListItem> castList;
    private ArrayList<PersonListItem> crewList;

    public ArrayList<PersonListItem> getCastList() {
        return castList;
    }

    public void setCastList(ArrayList<PersonListItem> castList) {
        this.castList = castList;
    }

    public ArrayList<PersonListItem> getCrewList() {
        return crewList;
    }

    public void setCrewList(ArrayList<PersonListItem> crewList) {
        this.crewList = crewList;
    }
}
