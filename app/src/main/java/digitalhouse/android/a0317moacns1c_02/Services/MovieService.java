package digitalhouse.android.a0317moacns1c_02.Services;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieResults.MovieResults;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieResults.MovieResultsItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gregorio Martin on 20/5/2017.
 */

public class MovieServiceImpl implements MovieService {
    private static MovieServiceImpl instance;
    private TMDBClient client;
    public MovieServiceImpl() {
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }
    public static MovieServiceImpl getInstance(){
        if (instance == null) instance = new MovieServiceImpl();
        return instance;
    }


    public void getPopularMovies(final TMDBClient.APICallback apiCallback) {
        Call<MovieResults> call = client.obtainPopularMovies(TMDBClient.API_KEY);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults movieResults = response.body();
                List<MovieListItem> movieList = new ArrayList<MovieListItem>();
                for (MovieResultsItem movieResultsItem : movieResults.getResults()) {
                    MovieListItem movieListItem = new MovieListItem();
                    movieListItem.setId(movieResultsItem.getId());
                    movieListItem.setGenres("Géneros (no implementado)");
                    movieListItem.setRating(movieResultsItem.getVote_average().toString());
                    movieListItem.setTitle(movieResultsItem.getTitle());
                    String url = ConfigurationServiceImpl.getInstance().getImagesBaseURL();
                    url += ConfigurationServiceImpl.getInstance().getPosterSizes().get(0);
                    url += movieResultsItem.getPoster_path();
                    movieListItem.setPosterURL(url);
                    movieListItem.setYear(movieResultsItem.getRelease_date().substring(0,4));
                    movieList.add(movieListItem);
                }
                apiCallback.onSuccess(movieList);
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
