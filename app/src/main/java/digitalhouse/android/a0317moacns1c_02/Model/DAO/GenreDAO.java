package digitalhouse.android.a0317moacns1c_02.Model.DAO;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.RetrofitTMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Genres.Genres;
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
    public void obtainGenres(TMDBClient.APICallback callback) {
        Call<Genres> call = client.obtainGenreList(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<Genres>(callback));
    }
}
