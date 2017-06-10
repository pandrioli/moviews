package digitalhouse.android.a0317moacns1c_02.Callbacks;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Helpers.ListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Model.General.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieResultsContainer;

/**
 * Created by Pablo on 10/06/2017.
 */

public class SerieResultsCallback implements TMDBClient.APICallback {
    private TMDBClient.APICallback callback;

    public SerieResultsCallback(TMDBClient.APICallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(Object result) {
        SerieResultsContainer serieResults = (SerieResultsContainer) result;
        List<ListItem> results = ListItemMapper.map(serieResults.getResults());
        callback.onSuccess(results);
    }
}
