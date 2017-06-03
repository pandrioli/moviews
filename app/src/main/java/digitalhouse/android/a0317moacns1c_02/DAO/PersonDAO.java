package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.RetrofitTMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.DAO.Person.PersonDetails;
import digitalhouse.android.a0317moacns1c_02.DAO.Person.PersonImages;
import digitalhouse.android.a0317moacns1c_02.DAO.Person.PersonMovieCredits;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Pablo on 03/06/2017.
 */

public class PersonDAO {
    private TMDBClient client;
    public PersonDAO() {
        client = ServiceGenerator.createService(TMDBClient.class);
    }
    public void obtainDetails(Integer id, TMDBClient.APICallback callback) {
        Call<PersonDetails> call = client.obtainPersonDetails(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<PersonDetails>(callback));
    }
    public void obtainImages(Integer id, TMDBClient.APICallback callback) {
        Call<PersonImages> call = client.obtainPersonImages(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<PersonImages>(callback));
    }
    public void obtainMovieCredits(Integer id, TMDBClient.APICallback callback) {
        Call<PersonMovieCredits> call = client.obtainPersonMovieCredits(id.toString(), TMDBClient.API_KEY);
        call.enqueue(new RetrofitTMDBCallBack<PersonMovieCredits>(callback));
    }
}
