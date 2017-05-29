package digitalhouse.android.a0317moacns1c_02.APIs.TMDB;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Credits.CreditsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieImagesAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieResultsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Media.VideosAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Requests.MovieSearchRequest;
import digitalhouse.android.a0317moacns1c_02.Helpers.RequestsMapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 23/05/2017.
 */

public class MovieCalls {

    public static void obtainMovies(MovieSearchRequest movieSearchRequest, TMDBClient client, final TMDBClient.APICallback callback){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", TMDBClient.API_KEY);

        parameters = RequestsMapper.map(movieSearchRequest, parameters);

        Call<MovieResultsAPI> call = client.obtainMovies(parameters);
        call.enqueue(new MovieResultsCallBack(callback));
    }
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

    public static void obtainDetails(String id, TMDBClient client, final TMDBClient.APICallback callback) {
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

    public static void obtainCredits(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<CreditsAPI> call = client.obtainMovieCredits(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<CreditsAPI>() {
            @Override
            public void onResponse(Call<CreditsAPI> call, Response<CreditsAPI> response) {
                callback.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<CreditsAPI> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void obtainImages(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<MovieImagesAPI> call = client.obtainMovieImages(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<MovieImagesAPI>() {
            @Override
            public void onResponse(Call<MovieImagesAPI> call, Response<MovieImagesAPI> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieImagesAPI> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void obtainVideos(String id, TMDBClient client, final TMDBClient.APICallback callback) {
        Call<VideosAPI> call = client.obtainMovieVideos(id, TMDBClient.API_KEY);
        call.enqueue(new Callback<VideosAPI>() {
            @Override
            public void onResponse(Call<VideosAPI> call, Response<VideosAPI> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<VideosAPI> call, Throwable t) {
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
