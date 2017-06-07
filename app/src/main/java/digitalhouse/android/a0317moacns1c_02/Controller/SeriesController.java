package digitalhouse.android.a0317moacns1c_02.Controller;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallBack;
import digitalhouse.android.a0317moacns1c_02.DAO.SeriesDAO;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.PopularSeriesRequest;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class SeriesController extends ObtainerController {
    private SeriesDAO seriesDAO;
    private static SeriesController instance;

    public SeriesController getInstance(){
        if(instance == null) instance = new SeriesController();
        return instance;
    }

    protected SeriesController(){
        this.seriesDAO = new SeriesDAO();
    }

    public void getPopular(TMDBClient.APICallback callback) {
        PopularSeriesRequest request = new PopularSeriesRequest();
        seriesDAO.obtainPopulars(request, new MovieResultsCallBack(callback));
    }

    @Override
    void getNowPlaying(TMDBClient.APICallback callback) {

    }

    @Override
    void getUpcoming(TMDBClient.APICallback callback) {

    }

}
