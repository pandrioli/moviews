package digitalhouse.android.a0317moacns1c_02.Entities;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gregorio Martin on 20/5/2017.
 */

public class MovieListItem implements Parcelable {

    private Integer id;
    private String title;
    private String year;
    private String genres;
    private String rating;
    private String posterURL;

    public MovieListItem() {

    }

    public MovieListItem(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.year = movie.getReleaseDate().substring(0,4);
        this.genres = movie.getGenres();
        this.rating = movie.getVoteAverage().toString();
        this.posterURL = movie.getPosterPath();
    }

    public MovieListItem(Parcel in) {
        readFromParcel(in);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenres() {
        return genres;
    }

    public String getRating() {
        return rating;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public static final Creator<MovieListItem> CREATOR = new Creator<MovieListItem>() {
        @Override
        public MovieListItem createFromParcel(Parcel in) {
            return new MovieListItem(in);
        }

        @Override
        public MovieListItem[] newArray(int size) {
            return new MovieListItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(genres);
        dest.writeString(rating);
        dest.writeString(posterURL);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        year = in.readString();
        genres = in.readString();
        rating = in.readString();
        posterURL = in.readString();
    }
}
