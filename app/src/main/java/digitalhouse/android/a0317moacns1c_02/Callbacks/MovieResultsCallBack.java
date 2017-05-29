package digitalhouse.android.a0317moacns1c_02.Callbacks;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieResultsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.Services.MovieService;

/**
 * Created by dh3 on 29/05/17.
 */

// Clase callback usada para los llamados que devuelven MovieResultsAPI
public class MovieResultsCallBack implements TMDBClient.APICallback {
    private TMDBClient.APICallback callback;

    public MovieResultsCallBack(TMDBClient.APICallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(Object result) {
        MovieResultsAPI movieResults = (MovieResultsAPI) result;
        List<MovieListItem> movieList = MovieService.getInstance().getMovieListItems(movieResults);
        callback.onSuccess(movieList);
    }
}