package digitalhouse.android.a0317moacns1c_02.Controller;

import android.os.AsyncTask;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.SerieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.DAO.SerieDAOInternet;
import digitalhouse.android.a0317moacns1c_02.DAO.SerieDAOLocal;
import digitalhouse.android.a0317moacns1c_02.Helpers.Toaster;
import digitalhouse.android.a0317moacns1c_02.Mappers.DTOSeasonMapper;
import digitalhouse.android.a0317moacns1c_02.Mappers.DTOSerieMapper;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.SeasonDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.SerieDTO;
import digitalhouse.android.a0317moacns1c_02.Model.General.ExternalIDs;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SeasonDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieOmdb;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class SerieController {
    private SerieDAOInternet seriesDAOInternet;
    private SerieDAOLocal serieDAOLocal;
    private static SerieController instance;

    public static SerieController getInstance(){
        if(instance == null) instance = new SerieController();
        return instance;
    }

    protected SerieController(){
        this.seriesDAOInternet = new SerieDAOInternet();
        this.serieDAOLocal = new SerieDAOLocal();
    }

    //public void getSeasonAndFragmentInstance()


    public void getSerie(String id, final ResultListener<Serie> resultListener){
        SerieDTO serieDTO = serieDAOLocal.obtainSerie(Integer.parseInt(id));
        if (serieDTO == null) {
            ObtainSerieTask serieTask = new ObtainSerieTask(new ResultListener<Serie>() {
                @Override
                public void finish(Serie result) {
                    if (result!=null) {
                        serieDAOLocal.saveSerie(DTOSerieMapper.map(result));
                        Toaster.getInstance().toast("Datos traidos de Internet");
                    }
                    resultListener.finish(result);
                }
            });
            serieTask.execute(id);
        } else {
            resultListener.finish(DTOSerieMapper.map(serieDTO));
            Toaster.getInstance().toast("Datos en base local");
        }
    }

    public ExternalIDs getExternalIDs(String ID){
        return seriesDAOInternet.obtainExternalIDs(ID);
    }

    public SerieOmdb getSerieOmdb(String imdbID){
        return seriesDAOInternet.obtainDetailsShort(imdbID);
    }

    public void getSeasonDetails(String serieId, Integer seasonNumber, final ResultListener<SeasonDetails> rl){
        if(serieId != null && seasonNumber != null){
            SeasonDTO seasonDTO = serieDAOLocal.obtainSeason(Integer.parseInt(serieId), seasonNumber);
            if (seasonDTO==null) {
                ObtainSeasonDetailsTask seasonTask = new ObtainSeasonDetailsTask(new ResultListener<SeasonDetails>() {
                    @Override
                    public void finish(SeasonDetails result) {
                        if (result!=null) serieDAOLocal.saveSeason(DTOSeasonMapper.map(result));
                        rl.finish(result);
                    }
                });
                seasonTask.execute(serieId, seasonNumber.toString());
            } else {
                rl.finish(DTOSeasonMapper.map(seasonDTO));
            }
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
            serie.setVideoContainer(seriesDAOInternet.obtainVideos(ID));
            serie.setImagesContainer(seriesDAOInternet.obtainImages(ID));
            serie.setSerieDetails(seriesDAOInternet.obtainDetails(ID));
            serie.setCredits(seriesDAOInternet.obtainCredits(ID));
            serie.setVideoContainer(seriesDAOInternet.obtainVideos(ID));
            if (serie.getSerieDetails().getId()!=null){
                serie.calculateRatings();
                return serie;
            } else return null;
    }

        @Override
        protected void onPostExecute(Serie serie) {
            super.onPostExecute(serie);
            rl.finish(serie);
        }
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
            SeasonDetails seasonDetails = seriesDAOInternet.obtainSeasonDetails(serieId, numberOfSeason);
            if (seasonDetails!=null) {
                seasonDetails.setSerieId(Integer.parseInt(serieId));
                return seasonDetails;
            } else return null;
        }

        @Override
        protected void onPostExecute(SeasonDetails seasonDetails) {
            super.onPostExecute(seasonDetails);
            rl.finish(seasonDetails);
        }
    }
}
