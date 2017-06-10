package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.RetrofitTMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class SerieDAO {
    private TMDBClient client;

    public SerieDAO() {
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }

    public void obtainPopular(TMDBClient.APICallback callback){
        Call<SerieResultsContainer> call = client.obtainPopularSeries(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<SerieResultsContainer>(callback));
    }

    public void obtainTopRated(TMDBClient.APICallback callback) {
        Call<SerieResultsContainer> call = client.obtainTopRatedSeries(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<SerieResultsContainer>(callback));
    }

    public void obtainAiringToday(TMDBClient.APICallback callback) {
        Call<SerieResultsContainer> call = client.obtainAiringTodaySeries(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<SerieResultsContainer>(callback));
    }

}
