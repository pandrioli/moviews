package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.RetrofitTMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genres;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Pablo on 03/06/2017.
 */

public class GenreDAO {
    private TMDBClient client;
    public GenreDAO() {
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }
    public void obtainMovieGenres(TMDBClient.APICallback callback) {
        Call<Genres> call = client.obtainMovieGenres(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<Genres>(callback));
    }
    public void obtainSerieGenres(TMDBClient.APICallback callback) {
        Call<Genres> call = client.obtainSerieGenres(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<Genres>(callback));
    }
}
