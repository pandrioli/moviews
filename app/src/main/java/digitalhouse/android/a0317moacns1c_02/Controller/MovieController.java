package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallBack;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieDAO;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieResults;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieResultsItem;

/**
 * Created by Pablo on 03/06/2017.
 */

public class MovieController {
    private MovieDAO movieDAO;
    private static MovieController instance;

    public static MovieController getInstance() {
        if (instance == null) instance = new MovieController();
        return instance;
    }

    public MovieController() {
        movieDAO = new MovieDAO();
    }

    public void getPopular(TMDBClient.APICallback callback) {
        movieDAO.obtainPopular(new MovieResultsCallBack(callback));
    }
    public void getNowPlaying(TMDBClient.APICallback callback) {
        movieDAO.obtainPopular(new MovieResultsCallBack(callback));
    }
    public void getUpcoming(TMDBClient.APICallback callback) {
        movieDAO.obtainUpcoming(new MovieResultsCallBack(callback));
    }

    public void getDetails(Integer id, TMDBClient.APICallback callback) {
        movieDAO.obtainDetails(id, callback);
    }

    public void getCredits(Integer id, TMDBClient.APICallback callback) {
        movieDAO.obtainCredits(id, callback);
    }

    public void getImages(Integer id, TMDBClient.APICallback callback) {
        movieDAO.obtainImages(id, callback);
    }


}
