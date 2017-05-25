package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieDetails.MovieDetails;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieResults.MovieResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 23/05/2017.
 */

public class MovieCalls {


    public static void obtainPopular(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResults> call = client.obtainPopularMovies(TMDBClient.API_KEY);
        call.enqueue(new MovieResultsCallBack(callback));
    }
    public static void obtainNowPlaying(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResults> call = client.obtainNowPlayingMovies(TMDBClient.API_KEY);
        call.enqueue(new MovieResultsCallBack(callback));
    }
    public static void obtainUpcoming(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResults> call = client.obtainUpcomingMovies(TMDBClient.API_KEY);
        call.enqueue(new MovieResultsCallBack(callback));
    }

    public static void obtainMovieDetails(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieDetails> call = client.obtainMovieDetails(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private static class MovieResultsCallBack implements Callback<MovieResults> {
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
