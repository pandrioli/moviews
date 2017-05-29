package digitalhouse.android.a0317moacns1c_02.Entities.API.Person;

import java.util.ArrayList;

/**
 * Created by Pablo on 28/05/2017.
 */

public class PersonMovieCreditsAPI {
    private Integer id;
    private ArrayList<PersonMovieCastCreditItemAPI> cast;
    private ArrayList<PersonMovieCrewCreditItemAPI> crew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<PersonMovieCastCreditItemAPI> getCast() {
        return cast;
    }

    public void setCast(ArrayList<PersonMovieCastCreditItemAPI> cast) {
        this.cast = cast;
    }

    public ArrayList<PersonMovieCrewCreditItemAPI> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<PersonMovieCrewCreditItemAPI> crew) {
        this.crew = crew;
    }
}
