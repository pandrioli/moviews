package digitalhouse.android.a0317moacns1c_02.Services;

import java.util.HashMap;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.SearchCalls;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallBack;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Requests.MovieSearchRequest;
import digitalhouse.android.a0317moacns1c_02.Helpers.RequestsMapper;

/**
 * Created by dh3 on 29/05/17.
 */

public class SearchService {
    private static SearchService instance;
    private TMDBClient client;

    public SearchService() {
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }

    public static SearchService getInstance() {
        if (instance == null) instance = new SearchService();
        return instance;
    }

    public void searchMovies(MovieSearchRequest movieSearchRequest, final TMDBClient.APICallback callback) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", TMDBClient.API_KEY);
        parameters = RequestsMapper.map(movieSearchRequest, parameters);
        SearchCalls.searchMovies(parameters, client, new MovieResultsCallBack(callback));
    }
}
