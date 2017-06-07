package digitalhouse.android.a0317moacns1c_02.DAO;

import java.util.HashMap;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.SeriesClient;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.RetrofitTMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.PopularSeriesRequest;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Series;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class SeriesDAO {
    private SeriesClient client;

    public SeriesDAO() {
        this.client = ServiceGenerator.createService(SeriesClient.class);
    }

    public void obtainPopulars(PopularSeriesRequest request, TMDBClient.APICallback callback){
        HashMap<String, String> parametrosBusquedaSeriesPopulares = request.getHashMap();
        Call<Series> call = client.obtainPopulars(parametrosBusquedaSeriesPopulares);
        call.enqueue(new RetrofitTMDBCallBack<Series>(callback));
    }

}
