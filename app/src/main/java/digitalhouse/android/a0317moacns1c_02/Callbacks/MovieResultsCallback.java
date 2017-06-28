package digitalhouse.android.a0317moacns1c_02.Callbacks;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Mappers.ListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;

/**
 * Created by dh3 on 29/05/17.
 */

// Clase callback usada para los llamados que devuelven MovieResultsContainer
public class MovieResultsCallback implements ResultListener<MovieResultsContainer> {
    private ResultListener<ArrayList<ListItem>> resultListener;

    public MovieResultsCallback(ResultListener<ArrayList<ListItem>> resultListener) {
        this.resultListener = resultListener;
    }

    @Override
    public void finish(MovieResultsContainer movieResults) {
        resultListener.finish(ListItemMapper.map(movieResults.getResults()));
    }
}
