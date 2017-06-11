package digitalhouse.android.a0317moacns1c_02.DAO;

import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.RetrofitTMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Pablo on 03/06/2017.
 */

public class SearchDAO {
    private TMDBClient client;

    public SearchDAO() {
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }

    public void searchMovies(Map<String, String> parameters, TMDBClient.APICallback callback) {
        Call<MovieResultsContainer> call = client.obtainMovies(parameters);
        call.enqueue(new RetrofitTMDBCallBack<MovieResultsContainer>(callback));
    }

    public void searchSeries(Map<String, String> parameters, TMDBClient.APICallback callback) {
        Call<SerieResultsContainer> call = client.obtainSeries(parameters);
        call.enqueue(new RetrofitTMDBCallBack<SerieResultsContainer>(callback));
    }

    public void searchPeople(Map<String, String> parameters, TMDBClient.APICallback callback) {
        Call<PersonResultsContainer> call = client.obtainPeople(parameters);
        call.enqueue(new RetrofitTMDBCallBack<PersonResultsContainer>(callback));
    }
}
