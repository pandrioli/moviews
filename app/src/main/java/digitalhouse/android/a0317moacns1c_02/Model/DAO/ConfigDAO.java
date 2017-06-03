package digitalhouse.android.a0317moacns1c_02.Model.DAO;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.RetrofitTMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Configuration.Config;
import retrofit2.Call;

/**
 * Created by Pablo on 03/06/2017.
 */

public class ConfigDAO {
    private TMDBClient client;
    public void obtainConfigData(TMDBClient.APICallback callback) {
        Call<Config> call = client.obtainConfiguration(TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<Config>(callback));
    }
}
