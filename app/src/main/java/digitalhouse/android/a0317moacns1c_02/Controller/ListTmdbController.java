package digitalhouse.android.a0317moacns1c_02.Controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.SerieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.DAO.ListDAOLocal;
import digitalhouse.android.a0317moacns1c_02.DAO.MovieDAOInternet;
import digitalhouse.android.a0317moacns1c_02.DAO.SerieDAOInternet;
import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.NetworkHelper;
import digitalhouse.android.a0317moacns1c_02.Mappers.DTOListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Mappers.ListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.ListDTO;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieResultsContainer;

/**
 * Created by Pablo on 28/06/2017.
 */

// controller para listas traidas de TMDB

public class ListTmdbController {
    private static final Integer LATEST_DAY_RANGE = 30;
    private static final Integer UPCOMING_DAY_RANGE = 30;

    private Context context;

    private MovieDAOInternet movieDAOInternet;
    private SerieDAOInternet serieDAOInternet;
    private ListDAOLocal listDAOLocal;

    private String topsYearFrom;
    private String topsYearTo;

    private List<String> downloadedLists;

    private static ListTmdbController instance;
    public static ListTmdbController getInstance(Context context) {
        if (instance==null) instance = new ListTmdbController();
        instance.setContext(context);
        return instance;
    }

    private ListTmdbController () {
        movieDAOInternet = new MovieDAOInternet();
        serieDAOInternet = new SerieDAOInternet();
        listDAOLocal = new ListDAOLocal();
        downloadedLists = new ArrayList<>();
        topsYearFrom = "1950";
        topsYearTo = String.format("%1d", Calendar.getInstance().get(Calendar.YEAR));
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTopsYearFrom() {
        return topsYearFrom;
    }

    public void setTopsYearFrom(String topsYearFrom) {
        this.topsYearFrom = topsYearFrom;
    }

    public String getTopsYearTo() {
        return topsYearTo;
    }

    public void setTopsYearTo(String topsYearTo) {
        this.topsYearTo = topsYearTo;
    }

    // movie lists

    public void getMoviesPopular(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.MOVIES_POPULAR)) {
            resultListener.finish(getLocalList(ListDTO.MOVIES_POPULAR));
        } else {
            movieDAOInternet.discoverMovies(new HashMap<String, String>(),
                    new ListResultsManager<MovieResultsContainer>(resultListener, ListDTO.MOVIES_POPULAR));
        }
    }

    public void getMoviesLatest(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.MOVIES_LATEST)) {
            resultListener.finish(getLocalList(ListDTO.MOVIES_LATEST));
        } else {
            Date dateFrom = DateHelper.todayOffset(-LATEST_DAY_RANGE);
            Date dateTo = DateHelper.todayOffset(0);
            movieDAOInternet.discoverMovies(getMovieDateRangeQuery(dateFrom, dateTo),
                    new ListResultsManager<MovieResultsContainer>(resultListener, ListDTO.MOVIES_LATEST));
        }
    }

    public void getMoviesUpcoming(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.MOVIES_UPCOMING)) {
            resultListener.finish(getLocalList(ListDTO.MOVIES_UPCOMING));
        } else {
            Date dateFrom = DateHelper.todayOffset(1);
            Date dateTo = DateHelper.todayOffset(UPCOMING_DAY_RANGE);
            movieDAOInternet.discoverMovies(getMovieDateRangeQuery(dateFrom, dateTo),
                    new ListResultsManager<MovieResultsContainer>(resultListener, ListDTO.MOVIES_UPCOMING));
        }
    }

    // series list

    public void getSeriesPopular(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.SERIES_POPULAR))  {
            resultListener.finish(getLocalList(ListDTO.SERIES_POPULAR));
        } else {
            serieDAOInternet.discoverSeries(new HashMap<String, String>(),
                    new ListResultsManager<SerieResultsContainer>(resultListener, ListDTO.SERIES_POPULAR));
        }
    }

    public void getSeriesLatest(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.SERIES_LATEST)) {
            resultListener.finish(getLocalList(ListDTO.SERIES_LATEST));
        } else {
            Date dateFrom = DateHelper.todayOffset(-LATEST_DAY_RANGE);
            Date dateTo = DateHelper.todayOffset(0);
            serieDAOInternet.discoverSeries(getSerieDateRangeQuery(dateFrom, dateTo),
                    new ListResultsManager<SerieResultsContainer>(resultListener, ListDTO.SERIES_LATEST));
        }
    }

    public void getSeriesUpcoming(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.SERIES_UPCOMING)) {
            resultListener.finish(getLocalList(ListDTO.SERIES_UPCOMING));
        } else {
            Date dateFrom = DateHelper.todayOffset(1);
            Date dateTo = DateHelper.todayOffset(UPCOMING_DAY_RANGE);
            serieDAOInternet.discoverSeries(getSerieDateRangeQuery(dateFrom, dateTo),
                    new ListResultsManager<SerieResultsContainer>(resultListener, ListDTO.SERIES_UPCOMING));
        }
    }


    public void getSeriesAiringToday(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.SERIES_AIRING_TODAY)) {
            resultListener.finish(getLocalList(ListDTO.SERIES_AIRING_TODAY));
        } else {
            Date today = new Date();
            serieDAOInternet.discoverSeries(getSerieEpisodesDateRangeQuery(today, today),
                    new ListResultsManager<SerieResultsContainer>(resultListener, ListDTO.SERIES_AIRING_TODAY));
        }
    }

    // Tops

    public void getTopMovies(String yearFrom, String yearTo, List<Genre> genres, ResultListener<ArrayList<ListItem>> resultListener) {
        Map<String, String> queryMap = getTopsQuery(genres, 200);
        Date from = DateHelper.parse(yearFrom+"-01-01", DateHelper.FORMAT_API);
        Date to = DateHelper.parse(yearTo+"-12-31", DateHelper.FORMAT_API);
        queryMap.putAll(getMovieDateRangeQuery(from, to));
        queryMap.put("with_runtime.gte", "60");
        movieDAOInternet.discoverMovies(queryMap, new MovieResultsCallback(resultListener));
    }
    public void getTopSeries(String yearFrom, String yearTo, List<Genre> genres, ResultListener<ArrayList<ListItem>> resultListener) {
        Map<String, String> queryMap = getTopsQuery(genres, 100);
        Date from = DateHelper.parse(yearFrom+"-01-01", DateHelper.FORMAT_API);
        Date to = DateHelper.parse(yearTo+"-12-31", DateHelper.FORMAT_API);
        queryMap.putAll(getSerieDateRangeQuery(from, to));
        serieDAOInternet.discoverSeries(queryMap, new SerieResultsCallback(resultListener));
    }




    // private methods


    private ArrayList<ListItem> getLocalList(String id) {
        ListDTO listDTO = listDAOLocal.obtainAppList(id);
        return DTOListItemMapper.map(listDTO.getList());
    }

    private Map<String, String> getTopsQuery(List<Genre> genres, Integer minVotes) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("sort_by", "vote_average.desc");
        queryMap.put("vote_count.gte", minVotes.toString());
        //queryMap.put("vote_average.gte", "7.5");
        String genresString = "";
        for (Genre genre : genres) {
            genresString+=genre.getId().toString()+",";
        }
        if (!genresString.isEmpty()) queryMap.put("with_genres",genresString);
        return queryMap;
    }

    private Map<String,String> getMovieDateRangeQuery(Date from, Date to) {
        String fromString = DateHelper.format(from, DateHelper.FORMAT_API);
        String toString = DateHelper.format(to, DateHelper.FORMAT_API);
        return getMovieDateRangeQuery(fromString, toString);
    }

    private Map<String,String> getSerieDateRangeQuery(Date from, Date to) {
        String fromString = DateHelper.format(from, DateHelper.FORMAT_API);
        String toString = DateHelper.format(to, DateHelper.FORMAT_API);
        return getSerieDateRangeQuery(fromString, toString);
    }

    private Map<String,String> getMovieDateRangeQuery(String from, String to) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("primary_release_date.gte", from);
        queryMap.put("primary_release_date.lte", to);
        return queryMap;
    }

    private Map<String,String> getSerieDateRangeQuery(String from, String to) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("first_air_date.gte", from);
        queryMap.put("first_air_date.lte", to);
        return queryMap;
    }

    private Map<String, String> getSerieEpisodesDateRangeQuery(Date from, Date to) {
        Map<String, String> queryMap = new HashMap<>();
        String fromString = DateHelper.format(from, DateHelper.FORMAT_API);
        String toString = DateHelper.format(to, DateHelper.FORMAT_API);
        queryMap.put("air_date.gte", fromString);
        queryMap.put("air_date.lte", toString);
        return queryMap;
    }

    private Boolean offline(String id) {
        Boolean downloaded = downloadedLists.contains(id);
        Boolean connection = NetworkHelper.isNetworkAvailable(context);
        return downloaded || !connection;
    }

    // private classes

    private class ListResultsManager<T> implements ResultListener<T> {
        private ResultListener<ArrayList<ListItem>> resultListener;
        private String listID;

        public ListResultsManager(ResultListener<ArrayList<ListItem>> resultListener, String listID) {

            this.resultListener = resultListener;
            this.listID = listID;
        }

        @Override
        public void finish(T result) {
            // agregar lista a las ya bajadas de la API
            downloadedLists.add(listID);
            // mapear resultados a listitems
            ArrayList<ListItem> listItems = new ArrayList<>();
            if (result instanceof MovieResultsContainer) listItems = ListItemMapper.map(((MovieResultsContainer)result).getResults());
            if (result instanceof SerieResultsContainer) listItems = ListItemMapper.map(((SerieResultsContainer)result).getResults());
            resultListener.finish(listItems);
            // guardar lista en Realm
            listDAOLocal.saveAndReplaceAppList(listID, DTOListItemMapper.map(listItems));

            // guardar peliculas
            /*

            lo probé y no sólo es lento sino que se interrumpe luego de cierta cantidad de peticiones
            debido a una limitacion de la API

            if (result instanceof MovieResultsContainer) {
                for (ListItem listItem : listItems) {
                    MovieController.getInstance().getMovie(listItem.getId(), new ResultListener<Movie>() {
                        @Override
                        public void finish(Movie result) {
                            MovieController.getInstance().saveMovie(result);
                        }
                    });
                }
            }*/
        }
    }

}
