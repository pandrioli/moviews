package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.SerieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.DAO.SerieDAO;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImagesContainer;
import digitalhouse.android.a0317moacns1c_02.Model.General.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;

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

    public void getPopular(ResultListener<ArrayList<ListItem>> resultListener) {
        seriesDAO.obtainPopular(new SerieResultsCallback(resultListener));
    }
    public void getTopRated(ResultListener<ArrayList<ListItem>> resultListener) {
        seriesDAO.obtainTopRated(new SerieResultsCallback(resultListener));
    }
    public void getAiringToday(ResultListener<ArrayList<ListItem>> resultListener) {
        seriesDAO.obtainAiringToday(new SerieResultsCallback(resultListener));
    }

    public void getDetails(String ID, ResultListener<Serie> resultListener){
        seriesDAO.obtainDetails(ID, resultListener);
    }

    public void getImages(String ID, ResultListener<ImagesContainer> resultListener){
        seriesDAO.obtainImages(ID, resultListener);
    }

    public void getCredits(String ID, ResultListener<Credits> resultListener){
        seriesDAO.obtainCredits(ID, resultListener);
    }

}
