package digitalhouse.android.a0317moacns1c_02.Controller;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.SerieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.DAO.SerieDAO;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class SerieController {
    private SerieDAO seriesDAO;
    private static SerieController instance;

    public static SerieController getInstance(){
        if(instance == null) instance = new SerieController();
        return instance;
    }

    protected SerieController(){
        this.seriesDAO = new SerieDAO();
    }

    public void getPopular(TMDBClient.APICallback callback) {
        seriesDAO.obtainPopular(new SerieResultsCallback(callback));
    }
    public void getTopRated(TMDBClient.APICallback callback) {
        seriesDAO.obtainTopRated(new SerieResultsCallback(callback));
    }
    public void getAiringToday(TMDBClient.APICallback callback) {
        seriesDAO.obtainAiringToday(new SerieResultsCallback(callback));
    }

    public void getDetails(String ID, TMDBClient.APICallback callback){
        seriesDAO.obtainDetails(ID, callback);
    }

    public void getImages(String ID, TMDBClient.APICallback callback){
        seriesDAO.obtainImages(ID, callback);
    }

    public void getCredits(String ID, TMDBClient.APICallback callback){
        seriesDAO.obtainCredits(ID, callback);
    }

}
