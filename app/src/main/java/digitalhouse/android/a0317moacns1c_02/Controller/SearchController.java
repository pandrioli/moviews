package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.Callbacks.SerieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.DAO.SearchDAO;
import digitalhouse.android.a0317moacns1c_02.Helpers.ListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Model.General.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.MovieSearchRequest;
import digitalhouse.android.a0317moacns1c_02.Helpers.RequestsMapper;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.PersonSearchRequest;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.SerieSearchRequest;

/**
 * Created by dh3 on 29/05/17.
 */

public class SearchController {
    private static SearchController instance;
    private SearchDAO searchDAO;

    public SearchController() {
        this.searchDAO = new SearchDAO();
    }

    public static SearchController getInstance() {
        if (instance == null) instance = new SearchController();
        return instance;
    }

    public void searchMovies(MovieSearchRequest movieSearchRequest, final TMDBClient.APICallback callback) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", TMDBClient.API_KEY);
        parameters = RequestsMapper.map(movieSearchRequest, parameters);
        searchDAO.searchMovies(parameters, new MovieResultsCallback(callback));
    }

    public void searchSeries(SerieSearchRequest serieSearchRequest, final TMDBClient.APICallback callback) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", TMDBClient.API_KEY);
        parameters = RequestsMapper.map(serieSearchRequest, parameters);
        searchDAO.searchSeries(parameters, new SerieResultsCallback(callback));
    }

    public void searchPeople(PersonSearchRequest personSearchRequest, final TMDBClient.APICallback callback) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", TMDBClient.API_KEY);
        parameters = RequestsMapper.map(personSearchRequest, parameters);
        searchDAO.searchPeople(parameters, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                PersonResultsContainer personResultsContainer = (PersonResultsContainer) result;
                List<ListItem> listItems = ListItemMapper.map(personResultsContainer.getResults());
                callback.onSuccess(listItems);
            }
        });
    }

}
