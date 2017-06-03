package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsAPICallBack;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Movie.MovieResults;
import retrofit2.Call;

/**
 * Created by dh3 on 29/05/17.
 */

public class SearchCalls {
    public static void searchMovies(Map parameters, TMDBClient client, final TMDBClient.APICallback callback){
        Call<MovieResults> call = client.obtainMovies(parameters);
        call.enqueue(new MovieResultsAPICallBack(callback));
    }
}
