package digitalhouse.android.a0317moacns1c_02.DAO;

import java.io.IOException;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.OMDBClient;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.SeriesClient;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.TMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.ExternalIDs;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieOMDB;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.OMDBRequest;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SeasonDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieOmdb;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class SerieDAOInternet {
    private SeriesClient client;
    private OMDBClient omdbClient;
    private TMDBClient tmdbClient;

    public SerieDAOInternet() {
        this.tmdbClient = ServiceGenerator.getInstance().createService(TMDBClient.class, TMDBClient.BASE_URL);
        this.client = ServiceGenerator.getInstance().createService(SeriesClient.class, TMDBClient.BASE_URL);
        this.omdbClient = ServiceGenerator.getInstance().createService(OMDBClient.class, OMDBClient.BASE_URL);
    }

    //discover series
    public void discoverSeries(Map<String, String> queryMap, ResultListener<SerieResultsContainer> resultListener) {
        queryMap.put("api_key", TMDBClient.API_KEY);
        Call<SerieResultsContainer> call = tmdbClient.discoverSeries(queryMap);
        call.enqueue(new TMDBCallBack<SerieResultsContainer>(resultListener));
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

    public void obtainDetails(String ID, ResultListener<SerieDetails> resultListener){
        Call<SerieDetails> call = client.obtainDetails(ID, TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<SerieDetails>(resultListener));
    }

    public void obtainImages(String ID, ResultListener<ImageContainer> resultListener){
        Call<ImageContainer> call = client.obtainImages(ID, TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<ImageContainer>(resultListener));
    }

    public void obtainCredits(String ID, ResultListener<Credits> resultListener){
        Call<Credits> call = client.obtainCredits(ID, TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<Credits>(resultListener));
    }

    public void obtainVideos(String ID, ResultListener<VideoContainer> resultListener){
        Call<VideoContainer> call = client.obtainVideos(ID, TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<VideoContainer>(resultListener));
    }

    public VideoContainer obtainVideos(String ID){
        VideoContainer videoContainer;
        try {
            Call<VideoContainer> call = client.obtainVideos(ID, SeriesClient.API_KEY);
            videoContainer = call.execute().body();
            return videoContainer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoContainer = new VideoContainer();
        return videoContainer;
    }

    public ImageContainer obtainImages(String ID){
        ImageContainer imagesContainer;
        try {
            Call<ImageContainer> call = client.obtainImages(ID, SeriesClient.API_KEY);
            imagesContainer = call.execute().body();
            return imagesContainer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagesContainer = new ImageContainer();
        return imagesContainer;
    }

    public SerieDetails obtainDetails(String ID){
        SerieDetails serieDetails;
        try {
            Call<SerieDetails> call = client.obtainDetails(ID, SeriesClient.API_KEY);
            serieDetails = call.execute().body();
            return serieDetails;
        } catch (IOException e) {
            e.printStackTrace();
        }
        serieDetails = new SerieDetails();
        return serieDetails;
    }

    public Credits obtainCredits(String ID){
        Credits credits;
        try {
            Call<Credits> call = client.obtainCredits(ID, SeriesClient.API_KEY);
            credits = call.execute().body();
            return credits;
        } catch (IOException e) {
            e.printStackTrace();
        }
        credits = new Credits();
        return credits;
    }

    public SeasonDetails obtainSeasonDetails(String serieID, String seasonNumber){
        SeasonDetails seasonDetails;
        try{
            Call<SeasonDetails> call = client.obtainSeasonDetails(serieID, seasonNumber, SeriesClient.API_KEY);
            seasonDetails = call.execute().body();
            return seasonDetails;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public Credits obtainSeasonCredits(String serieID, String seasonNumber){
        Credits credits;
        try{
            Call<Credits> call = client.obtainSeasonCredits(serieID, seasonNumber, SeriesClient.API_KEY);
            credits = call.execute().body();
            return credits;
        } catch (IOException e){
            e.printStackTrace();
        }
        credits = new Credits();
        return credits;
    }

    public ImageContainer obtainSeasonImages(String serieID, String seasonNumber){
        ImageContainer images;
        try{
            Call<ImageContainer> call = client.obtainSeasonImages(serieID, seasonNumber, SeriesClient.API_KEY);
            images = call.execute().body();
            return images;
        } catch (IOException e){
            e.printStackTrace();
        }
        images = new ImageContainer();
        return images;
    }

    public VideoContainer obtainSeasonVideos(String serieID, String seasonNumber){
        VideoContainer videoContainer;
        try{
            Call<VideoContainer> call = client.obtainSeasonVideos(serieID, seasonNumber, SeriesClient.API_KEY);
            videoContainer = call.execute().body();
            return videoContainer;
        } catch (IOException e){
            e.printStackTrace();
        }
        videoContainer = new VideoContainer();
        return videoContainer;
    }

    public ExternalIDs obtainExternalIDs(String serieID){
        ExternalIDs externalIDs;
        try{
            Call<ExternalIDs> call = client.obtainSerieExternalIDs(serieID, SeriesClient.API_KEY);
            externalIDs = call.execute().body();
            return externalIDs;
        } catch (IOException e){
            e.printStackTrace();
        }
        externalIDs = new ExternalIDs();
        return externalIDs;
    }

    public SerieOmdb obtainDetailsShort(String imdbID){
        SerieOmdb serieOmdb = new SerieOmdb();
        try{
            OMDBRequest request = new OMDBRequest();
            request.setImdbId(imdbID);
            Call<SerieOmdb> callOMDB = omdbClient.obtainSerie(request.getQueryMap());
            serieOmdb = callOMDB.execute().body();
        } catch (Exception e){
            e.getStackTrace();
        }
        return serieOmdb;
    }



}
