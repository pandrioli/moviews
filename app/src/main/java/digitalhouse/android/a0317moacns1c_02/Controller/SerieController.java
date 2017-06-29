package digitalhouse.android.a0317moacns1c_02.Controller;

import android.os.AsyncTask;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.SerieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.DAO.SerieDAO;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.ExternalIDs;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Season;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieOmdb;

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

    //public void getSeasonAndFragmentInstance()

    public void getPopular(ResultListener<ArrayList<ListItem>> resultListener) {
        seriesDAO.obtainPopular(new SerieResultsCallback(resultListener));
    }
    public void getTopRated(ResultListener<ArrayList<ListItem>> resultListener) {
        seriesDAO.obtainTopRated(new SerieResultsCallback(resultListener));
    }
    public void getAiringToday(ResultListener<ArrayList<ListItem>> resultListener) {
        seriesDAO.obtainAiringToday(new SerieResultsCallback(resultListener));
    }
    public void getDetails(String ID, ResultListener<SerieDetails> resultListener){
        seriesDAO.obtainDetails(ID, resultListener);
    }

    public void getImages(String ID, ResultListener<ImageContainer> resultListener){
        seriesDAO.obtainImages(ID, resultListener);
    }

    public void getCredits(String ID, ResultListener<Credits> resultListener){
        seriesDAO.obtainCredits(ID, resultListener);
    }

    public void getVideos(String ID, ResultListener<VideoContainer> resultListener){
        seriesDAO.obtainVideos(ID, resultListener);
    }

    public ExternalIDs getExternalIDs(String ID){
        return seriesDAO.obtainExternalIDs(ID);
    }


    public void getSerie(String ID, ResultListener<Serie> resultListener){
        ObtainSerieTask serieTask = new ObtainSerieTask(resultListener);
        serieTask.execute(ID);
    }

    public SerieOmdb getSerieOmdb(String imdbID){
        return seriesDAO.obtainDetailsShort(imdbID);
    }

    private class ObtainSerieTask extends AsyncTask<String, Void, Serie> {

        ResultListener<Serie> rl;

        ObtainSerieTask(ResultListener<Serie> rl){
            this.rl = rl;
        }

        @Override
        protected Serie doInBackground(String... params) {
            String ID = params[0];
            Serie serie = new Serie();
            Season season = new Season();
            ExternalIDs externalIDs = getExternalIDs(ID);
            serie.setSerieOmdb(getSerieOmdb(externalIDs.getImdb_id()));
            serie.setExternalIDs(externalIDs);
            season.setSeasonDetails(seriesDAO.obtainSeasonDetails(ID, "1"));
            serie.setVideoContainer(seriesDAO.obtainVideos(ID));
            serie.setImagesContainer(seriesDAO.obtainImages(ID));
            serie.setSerieDetails(seriesDAO.obtainDetails(ID));
            serie.setCredits(seriesDAO.obtainCredits(ID));
            serie.setVideoContainer(seriesDAO.obtainVideos(ID));
            serie.putSeason(season);
            serie.calculateRatings();
            return serie;
        }

        @Override
        protected void onPostExecute(Serie serie) {
            super.onPostExecute(serie);
            rl.finish(serie);
        }
    }
}
