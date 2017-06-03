package digitalhouse.android.a0317moacns1c_02.Callbacks;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Movie.MovieResults;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Movie.MovieResultsItem;

/**
 * Created by dh3 on 29/05/17.
 */

// Clase callback usada para los llamados que devuelven MovieResults
public class MovieResultsCallBack implements TMDBClient.APICallback {
    private TMDBClient.APICallback callback;

    public MovieResultsCallBack(TMDBClient.APICallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(Object result) {
        MovieResults movieResults = (MovieResults) result;
        List<MovieResultsItem> results = movieResults.getResults();
        callback.onSuccess(results);
    }
}
