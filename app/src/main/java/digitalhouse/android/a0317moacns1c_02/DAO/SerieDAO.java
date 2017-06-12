package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.SeriesClient;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.RetrofitTMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImagesContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class SerieDAO {
    private SeriesClient client;

    public SerieDAO() {
        this.client = ServiceGenerator.createService(SeriesClient.class);
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

    public void obtainDetails(String ID, TMDBClient.APICallback callback){
        Call<Serie> call = client.obtainDetails(ID, TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<Serie>(callback));
    }

    public void obtainImages(String ID, TMDBClient.APICallback callback){
        Call<ImagesContainer> call = client.obtainImages(ID, TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<ImagesContainer>(callback));
    }

    public void obtainCredits(String ID, TMDBClient.APICallback callback){
        Call<Credits> call = client.obtainCredits(ID, TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<Credits>(callback));
    }

}
