package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.RetrofitTMDBCallBack;
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
    public void obtainPopular(TMDBClient.APICallback callback) {
        Call<MovieResultsContainer> call = client.obtainPopularMovies(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<MovieResultsContainer>(callback));
    }
    public void obtainNowPlaying(TMDBClient.APICallback callback) {
        Call<MovieResultsContainer> call = client.obtainNowPlayingMovies(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<MovieResultsContainer>(callback));
    }
    public void obtainUpcoming(TMDBClient.APICallback callback) {
        Call<MovieResultsContainer> call = client.obtainUpcomingMovies(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<MovieResultsContainer>(callback));
    }
    public void obtainDetails(Integer id, TMDBClient.APICallback callback) {
        Call<MovieDetails> call = client.obtainMovieDetails(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<MovieDetails>(callback));
    }
    public void obtainCredits(Integer id, TMDBClient.APICallback callback) {
        Call<Credits> call = client.obtainMovieCredits(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<Credits>(callback));
    }
    public void obtainImages(Integer id, TMDBClient.APICallback callback) {
        Call<ImagesContainer> call = client.obtainMovieImages(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<ImagesContainer>(callback));
    }
    public void obtainVideos(Integer id, TMDBClient.APICallback callback) {
        Call<MovieVideos> call = client.obtainMovieVideos(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<MovieVideos>(callback));
    }
}
