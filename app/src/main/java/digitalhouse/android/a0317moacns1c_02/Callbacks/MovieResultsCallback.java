package digitalhouse.android.a0317moacns1c_02.Callbacks;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Helpers.ListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.General.ListItem;

/**
 * Created by dh3 on 29/05/17.
 */

// Clase callback usada para los llamados que devuelven MovieResultsContainer
public class MovieResultsCallback implements TMDBClient.APICallback {
    private TMDBClient.APICallback callback;

    public MovieResultsCallback(TMDBClient.APICallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(Object result) {
        MovieResultsContainer movieResults = (MovieResultsContainer) result;
        List<ListItem> results = ListItemMapper.map(movieResults.getResults());
        callback.onSuccess(results);
    }
}
