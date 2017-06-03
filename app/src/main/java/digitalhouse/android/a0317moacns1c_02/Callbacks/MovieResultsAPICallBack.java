package digitalhouse.android.a0317moacns1c_02.Callbacks;

/**
 * Created by dh3 on 29/05/17.
 */

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Movie.MovieResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieResultsAPICallBack implements Callback<MovieResults> {
    private TMDBClient.APICallback callback;

    public MovieResultsAPICallBack(TMDBClient.APICallback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(Call call, Response response) {
        callback.onSuccess(response.body());
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d("Error", t.getMessage());
    }
}