package digitalhouse.android.a0317moacns1c_02.Entities.MovieDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregorio Martin on 25/5/2017.
 */

public class MovieDetailsTitle implements Serializable {

    private String title;
    private String year;
    private String movieRating;
    private List<String> genres;
    private String duration;
    private String earlyDesciption;

    public MovieDetailsTitle(String title, String year, String movieRating, ArrayList<String> genres, String duration, String earlyDesciption) {
        this.title = title;
        this.year = year;
        this.movieRating = movieRating;
        this.genres = genres;
        this.duration = duration;
        this.earlyDesciption = earlyDesciption;
    }

    public MovieDetailsTitle(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getGenres() {
        StringBuilder retorno = new StringBuilder();

        retorno.append(genres.get(0));
        for(int i = 1; i < genres.size(); i++)
        {
            retorno.append(" | ");
            retorno.append(genres.get(i));
        }
        return retorno.toString();
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEarlyDesciption() {
        return earlyDesciption;
    }

    public void setEarlyDesciption(String earlyDesciption) {
        this.earlyDesciption = earlyDesciption;
    }
}
