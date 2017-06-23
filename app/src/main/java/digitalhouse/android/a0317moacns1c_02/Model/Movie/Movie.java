package digitalhouse.android.a0317moacns1c_02.Model.Movie;

import java.io.Serializable;

import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.RatingsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;

/**
 * Created by Pablo on 21/06/2017.
 */

public class Movie implements Serializable {
    private MovieDetails movieDetails;
    private MovieOMDB movieOMDB;
    private Credits credits;
    private ImageContainer imageContainer;
    private VideoContainer videoContainer;
    private RatingsContainer ratingsContainer;

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    public MovieOMDB getMovieOMDB() {
        return movieOMDB;
    }

    public void setMovieOMDB(MovieOMDB movieOMDB) {
        this.movieOMDB = movieOMDB;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public ImageContainer getImageContainer() {
        return imageContainer;
    }

    public void setImageContainer(ImageContainer imageContainer) {
        this.imageContainer = imageContainer;
    }

    public VideoContainer getVideoContainer() {
        return videoContainer;
    }

    public void setVideoContainer(VideoContainer videoContainer) {
        this.videoContainer = videoContainer;
    }

    public RatingsContainer getRatingsContainer() {
        return ratingsContainer;
    }

    public void calculateRatings() {
        this.ratingsContainer = new RatingsContainer(this);
    }
}
