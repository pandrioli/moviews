package digitalhouse.android.a0317moacns1c_02.DAO;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.OMDBClient;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.TMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.RateOmdb;
import digitalhouse.android.a0317moacns1c_02.Model.General.RatingsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.General.ReviewContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieOMDB;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.OMDBRequest;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo on 03/06/2017.
 */

public class MovieDAOInternet {
    private TMDBClient tmdbClient;
    private OMDBClient omdbClient;

    public MovieDAOInternet() {
        this.tmdbClient = ServiceGenerator.getInstance().createService(TMDBClient.class, TMDBClient.BASE_URL);
        this.omdbClient = ServiceGenerator.getInstance().createService(OMDBClient.class, OMDBClient.BASE_URL);
    }


    // obtener todos los datos de una pelicula (clase Movie)
    public void obtainMovie(Integer id, ResultListener<Movie> resultListener) {
        ObtainMovieTask obtainMovieTask = new ObtainMovieTask(resultListener);
        obtainMovieTask.execute(id.toString());
    }

    // discover
    public void discoverMovies(Map<String, String> queryMap, ResultListener<MovieResultsContainer> resultListener) {
        queryMap.put("api_key", TMDBClient.API_KEY);
        Call<MovieResultsContainer> call = tmdbClient.discoverMovies(queryMap);
        call.enqueue(new TMDBCallBack<MovieResultsContainer>(resultListener));
    }

    // reviews
    public void obtainReviews(Integer id, final ResultListener<ReviewContainer> resultListener) {
        Call<ReviewContainer> call = tmdbClient.obtainMovieReviews(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new Callback<ReviewContainer>() {
            @Override
            public void onResponse(Call<ReviewContainer> call, Response<ReviewContainer> response) {
                resultListener.finish(response.body());
            }

            @Override
            public void onFailure(Call<ReviewContainer> call, Throwable t) {

            }
        });
    }

    // task para obtener todos los datos de una Movie
    private class ObtainMovieTask extends AsyncTask<String, Void, Movie> {

        private ResultListener<Movie> resultListener;

        public ObtainMovieTask(ResultListener<Movie> resultListener) {
            this.resultListener = resultListener;
        }

        @Override
        protected Movie doInBackground(String... params) {
            String id = params[0];
            Movie movie = new Movie();
            Call<MovieDetails> callDetails = tmdbClient.obtainMovieDetails(id, TMDBClient.API_KEY);
            Call<Credits> callCredits = tmdbClient.obtainMovieCredits(id, TMDBClient.API_KEY);
            Call<ImageContainer> callImages = tmdbClient.obtainMovieImages(id, TMDBClient.API_KEY);
            Call<VideoContainer> callVideos = tmdbClient.obtainMovieVideos(id, TMDBClient.API_KEY);
            try {
                movie.setMovieDetails(callDetails.execute().body());
                movie.setCredits(callCredits.execute().body());
                movie.setImageContainer(callImages.execute().body());
                movie.setVideoContainer(callVideos.execute().body());
                if (movie.getMovieDetails().getImdb_id() != null) {
                    OMDBRequest request = new OMDBRequest();
                    request.setImdbId(movie.getMovieDetails().getImdb_id());
                    Call<MovieOMDB> callOMDB = omdbClient.obtainMovie(request.getQueryMap());
                    movie.setMovieOMDB(callOMDB.execute().body());
                }
                movie.calculateRatings();
                return movie;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);
            resultListener.finish(movie);
        }
    }
}
