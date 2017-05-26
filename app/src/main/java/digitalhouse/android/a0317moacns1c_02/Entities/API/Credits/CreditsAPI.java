package digitalhouse.android.a0317moacns1c_02.Entities.API.Credits;

import java.util.ArrayList;

/**
 * Created by Pablo on 25/05/2017.
 */

public class CreditsAPI {
    private Integer id;
    private ArrayList<CastAPI> cast;
    private ArrayList<CrewAPI> crew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<CastAPI> getCast() {
        return cast;
    }

    public void setCast(ArrayList<CastAPI> cast) {
        this.cast = cast;
    }

    public ArrayList<CrewAPI> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<CrewAPI> crew) {
        this.crew = crew;
    }
}
