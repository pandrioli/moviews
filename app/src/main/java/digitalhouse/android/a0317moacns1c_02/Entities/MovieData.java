package digitalhouse.android.a0317moacns1c_02.Entities;

import java.util.List;

/**
 * Created by Pablo on 24/05/2017.
 */

public class MovieData {
    private Integer id;
    private String title;
    private String year;
    private String releaseDate;
    private String overview;
    private String genres;
    private String director;
    private Double rating;
    private String tagline;
    private Integer runtime;
    private String poster;
    private String backdrop;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    @Override
    public String toString() {
        return "MovieData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", overview='" + overview + '\'' +
                ", genres='" + genres + '\'' +
                ", director='" + director + '\'' +
                ", rating=" + rating +
                ", tagline='" + tagline + '\'' +
                ", runtime=" + runtime +
                ", poster='" + poster + '\'' +
                ", backdrop='" + backdrop + '\'' +
                '}';
    }
}
