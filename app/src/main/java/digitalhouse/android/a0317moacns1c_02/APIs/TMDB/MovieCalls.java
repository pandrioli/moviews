package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieDetails.MovieDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieResults.MovieResultsAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 23/05/2017.
 */

public class MovieCalls {


    public static void obtainPopular(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResultsAPI> call = client.obtainPopularMovies(TMDBClient.API_KEY);
        call.enqueue(new MovieResultsCallBack(callback));
    }
    public static void obtainNowPlaying(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResultsAPI> call = client.obtainNowPlayingMovies(TMDBClient.API_KEY);
        call.enqueue(new MovieResultsCallBack(callback));
    }
    public static void obtainUpcoming(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResultsAPI> call = client.obtainUpcomingMovies(TMDBClient.API_KEY);
        call.enqueue(new MovieResultsCallBack(callback));
    }

    public static void obtainMovieDetails(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieDetailsAPI> call = client.obtainMovieDetails(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<MovieDetailsAPI>() {
            @Override
            public void onResponse(Call<MovieDetailsAPI> call, Response<MovieDetailsAPI> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetailsAPI> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private static class MovieResultsCallBack implements Callback<MovieResultsAPI> {
        private TMDBClient.APICallback callback;

        public MovieResultsCallBack(TMDBClient.APICallback callback) {
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

}
