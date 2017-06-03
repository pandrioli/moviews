package digitalhouse.android.a0317moacns1c_02.DAO.Movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Pablo on 22/05/2017.
 */

public class MovieResultsItem implements Parcelable {
    private String poster_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private ArrayList<Integer> genre_ids;
    private Integer id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private Double popularity;
    private Integer vote_count;
    private Boolean video;
    private Double vote_average;

    protected MovieResultsItem(Parcel in) {
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        original_title = in.readString();
        original_language = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(original_title);
        dest.writeString(original_language);
        dest.writeString(title);
        dest.writeString(backdrop_path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieResultsItem> CREATOR = new Creator<MovieResultsItem>() {
        @Override
        public MovieResultsItem createFromParcel(Parcel in) {
            return new MovieResultsItem(in);
        }

        @Override
        public MovieResultsItem[] newArray(int size) {
            return new MovieResultsItem[size];
        }
    };

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    // custom methods

    public String getYear() {
        if (release_date.length()>4)
        return release_date.substring(0,4);
        else return "";
    }

}
