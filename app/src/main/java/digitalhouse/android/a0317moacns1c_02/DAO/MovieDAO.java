package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.TMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImagesContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieVideos;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Pablo on 03/06/2017.
 */

public class MovieDAO {
    private TMDBClient client;
    public MovieDAO() {
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }
    public void obtainPopular(ResultListener<MovieResultsContainer> resultListener) {
        Call<MovieResultsContainer> call = client.obtainPopularMovies(TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<MovieResultsContainer>(resultListener));
    }
    public void obtainNowPlaying(ResultListener<MovieResultsContainer> resultListener) {
        Call<MovieResultsContainer> call = client.obtainNowPlayingMovies(TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<MovieResultsContainer>(resultListener));
    }
    public void obtainUpcoming(ResultListener<MovieResultsContainer> resultListener) {
        Call<MovieResultsContainer> call = client.obtainUpcomingMovies(TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<MovieResultsContainer>(resultListener));
    }
    public void obtainDetails(Integer id, ResultListener<MovieDetails> resultListener) {
        Call<MovieDetails> call = client.obtainMovieDetails(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<MovieDetails>(resultListener));
    }
    public void obtainCredits(Integer id, ResultListener<Credits> resultListener) {
        Call<Credits> call = client.obtainMovieCredits(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<Credits>(resultListener));
    }
    public void obtainImages(Integer id, ResultListener<ImagesContainer> resultListener) {
        Call<ImagesContainer> call = client.obtainMovieImages(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<ImagesContainer>(resultListener));
    }
    public void obtainVideos(Integer id, ResultListener<MovieVideos> resultListener) {
        Call<MovieVideos> call = client.obtainMovieVideos(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<MovieVideos>(resultListener));
    }
}
