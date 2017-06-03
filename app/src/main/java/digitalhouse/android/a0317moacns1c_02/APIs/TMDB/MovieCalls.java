package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsAPICallBack;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieDetails;
import digitalhouse.android.a0317moacns1c_02.DAO.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieImages;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieResults;
import digitalhouse.android.a0317moacns1c_02.DAO.Movie.MovieVideos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 23/05/2017.
 */

public class MovieCalls {

    public static void obtainPopular(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResults> call = client.obtainPopularMovies(TMDBClient.API_KEY);
        call.enqueue(new MovieResultsAPICallBack(callback));
    }
    public static void obtainNowPlaying(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResults> call = client.obtainNowPlayingMovies(TMDBClient.API_KEY);
        call.enqueue(new MovieResultsAPICallBack(callback));
    }
    public static void obtainUpcoming(TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieResults> call = client.obtainUpcomingMovies(TMDBClient.API_KEY);
        call.enqueue(new MovieResultsAPICallBack(callback));
    }

    public static void obtainDetails(String id, TMDBClient client, final TMDBClient.APICallback callback) {
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

    public static void obtainCredits(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<Credits> call = client.obtainMovieCredits(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                callback.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<Credits> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void obtainImages(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieImages> call = client.obtainMovieImages(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<MovieImages>() {
            @Override
            public void onResponse(Call<MovieImages> call, Response<MovieImages> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieImages> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void obtainVideos(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieVideos> call = client.obtainMovieVideos(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<MovieVideos>() {
            @Override
            public void onResponse(Call<MovieVideos> call, Response<MovieVideos> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieVideos> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
