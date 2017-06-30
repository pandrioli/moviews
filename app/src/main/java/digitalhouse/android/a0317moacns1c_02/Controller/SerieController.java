package digitalhouse.android.a0317moacns1c_02.Controller;

import android.os.AsyncTask;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.SerieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.DAO.SerieDAOInternet;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.ExternalIDs;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Season;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SeasonDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieOmdb;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class SerieController {
    private SerieDAOInternet seriesDAO;
    private static SerieController instance;

    public static SerieController getInstance(){
        if(instance == null) instance = new SerieController();
        return instance;
    }

    protected SerieController(){
        this.seriesDAO = new SerieDAOInternet();
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

    public void getSeasonDetails(String serieId, Integer seasonNumber, ResultListener<SeasonDetails> rl){
        ObtainSeasonDetailsTask seasonTask = new ObtainSeasonDetailsTask(rl);
        if(serieId != null && seasonNumber != null){
            seasonTask.execute(serieId, seasonNumber.toString());
        }

    }


    public void getSerie(String ID, ResultListener<Serie> resultListener){
        ObtainSerieTask serieTask = new ObtainSerieTask(resultListener);
        if(ID != null)
        serieTask.execute(ID);
    }

    public SerieOmdb getSerieOmdb(String imdbID){
        return seriesDAO.obtainDetailsShort(imdbID);
    }

    private class ObtainSeasonDetailsTask extends AsyncTask<String, Void, SeasonDetails>{

        ResultListener<SeasonDetails> rl;

        ObtainSeasonDetailsTask(ResultListener<SeasonDetails> rl){
            this.rl = rl;
        }

        @Override
        protected SeasonDetails doInBackground(String... params) {
            String serieId = params[0];
            String numberOfSeason = params[1];
            SeasonDetails seasonDetails = seriesDAO.obtainSeasonDetails(serieId, numberOfSeason);
            return seasonDetails;
        }

        @Override
        protected void onPostExecute(SeasonDetails seasonDetails) {
            super.onPostExecute(seasonDetails);
            rl.finish(seasonDetails);
        }
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
            ExternalIDs externalIDs = getExternalIDs(ID);
            serie.setSerieOmdb(getSerieOmdb(externalIDs.getImdb_id()));
            serie.setExternalIDs(externalIDs);
            serie.setVideoContainer(seriesDAO.obtainVideos(ID));
            serie.setImagesContainer(seriesDAO.obtainImages(ID));
            serie.setSerieDetails(seriesDAO.obtainDetails(ID));
            serie.setCredits(seriesDAO.obtainCredits(ID));
            serie.setVideoContainer(seriesDAO.obtainVideos(ID));
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
