package digitalhouse.android.a0317moacns1c_02.Entities;


/**
 * Created by Gregorio Martin on 20/5/2017.
 */

public class MovieListItem {

    private Movie movie;

    public MovieListItem(Movie movie) {
        this.movie = movie;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public String getYear() {
        return movie.getReleaseDate().substring(0,4);
    }

    public String getGenres() {
        return movie.getGenres();
    }

    public String getRating() {
        return movie.getVoteAverage().toString();
    }

}
