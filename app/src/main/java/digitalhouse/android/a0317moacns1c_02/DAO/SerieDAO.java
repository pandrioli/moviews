package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.SeriesClient;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.TMDBCallBack;
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

    public void obtainPopular(ResultListener<SerieResultsContainer> resultListener){
        Call<SerieResultsContainer> call = client.obtainPopularSeries(TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<SerieResultsContainer>(resultListener));
    }

    public void obtainTopRated(ResultListener<SerieResultsContainer> resultListener) {
        Call<SerieResultsContainer> call = client.obtainTopRatedSeries(TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<SerieResultsContainer>(resultListener));
    }

    public void obtainAiringToday(ResultListener<SerieResultsContainer> resultListener) {
        Call<SerieResultsContainer> call = client.obtainAiringTodaySeries(TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<SerieResultsContainer>(resultListener));
    }

    public void obtainDetails(String ID, ResultListener<Serie> resultListener){
        Call<Serie> call = client.obtainDetails(ID, TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<Serie>(resultListener));
    }

    public void obtainImages(String ID, ResultListener<ImagesContainer> resultListener){
        Call<ImagesContainer> call = client.obtainImages(ID, TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<ImagesContainer>(resultListener));
    }

    public void obtainCredits(String ID, ResultListener<Credits> resultListener){
        Call<Credits> call = client.obtainCredits(ID, TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<Credits>(resultListener));
    }

}
