package digitalhouse.android.a0317moacns1c_02.Controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.DAO.ListDAOLocal;
import digitalhouse.android.a0317moacns1c_02.DAO.MovieDAOInternet;
import digitalhouse.android.a0317moacns1c_02.DAO.SerieDAOInternet;
import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.NetworkHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.Toaster;
import digitalhouse.android.a0317moacns1c_02.Mappers.DTOListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Mappers.ListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.ListDTO;
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
    }

    public void setContext(Context context) {
        this.context = context;
    }


    // movie lists

    public void getMoviesPopular(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.MOVIES_POPULAR)) {
            resultListener.finish(getLocalList(ListDTO.MOVIES_POPULAR));
        } else {
            movieDAOInternet.discoverMovies(new HashMap<String, String>(), new ListResultsManager(resultListener, ListDTO.MOVIES_POPULAR));
        }
    }

    public void getMoviesLatest(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.MOVIES_NOWPLAYING)) {
            resultListener.finish(getLocalList(ListDTO.MOVIES_NOWPLAYING));
        } else {
            Date dateFrom = DateHelper.todayOffset(-LATEST_DAY_RANGE);
            Date dateTo = DateHelper.todayOffset(0);
            movieDAOInternet.discoverMovies(getDateRangeQuery(dateFrom, dateTo),
                    new ListResultsManager(resultListener, ListDTO.MOVIES_NOWPLAYING));
        }
    }

    public void getMoviesUpcoming(ResultListener<ArrayList<ListItem>> resultListener) {
        if (offline(ListDTO.MOVIES_UPCOMING)) {
            resultListener.finish(getLocalList(ListDTO.MOVIES_UPCOMING));
        } else {
            Date dateFrom = DateHelper.todayOffset(1);
            Date dateTo = DateHelper.todayOffset(UPCOMING_DAY_RANGE);
            movieDAOInternet.discoverMovies(getDateRangeQuery(dateFrom, dateTo),
                    new ListResultsManager(resultListener, ListDTO.MOVIES_UPCOMING));
        }
    }


    // private methods



    private ArrayList<ListItem> getLocalList(String id) {
        ListDTO listDTO = listDAOLocal.obtainAppList(id);
        return ListItemMapper.map(listDTO.getList());
    }

    private Map<String,String> getDateRangeQuery(Date from, Date to) {
        Map<String, String> queryMap = new HashMap<>();
        String fromString = DateHelper.format(from, DateHelper.FORMAT_API);
        String toString = DateHelper.format(to, DateHelper.FORMAT_API);
        queryMap.put("primary_release_date.gte", fromString);
        queryMap.put("primary_release_date.lte", toString);
        return queryMap;
    }

    private Boolean offline(String id) {
        Boolean downloaded = downloadedLists.contains(id);
        Boolean connection = NetworkHelper.isNetworkAvailable(context);
        return downloaded || !connection;
    }

    // private classes

    private class ListResultsManager implements ResultListener {
        private ResultListener<ArrayList<ListItem>> resultListener;
        private String listID;

        public ListResultsManager(ResultListener<ArrayList<ListItem>> resultListener, String listID) {

            this.resultListener = resultListener;
            this.listID = listID;
        }

        @Override
        public void finish(Object result) {
            // agregar lista a las ya bajadas de la API
            downloadedLists.add(listID);
            // mapear resultados a listitems
            ArrayList<ListItem> listItems = new ArrayList<>();
            if (result instanceof MovieResultsContainer) listItems = ListItemMapper.map(((MovieResultsContainer)result).getResults());
            if (result instanceof SerieResultsContainer) listItems = ListItemMapper.map(((SerieResultsContainer)result).getResults());
            resultListener.finish(listItems);
            // guardar resultados en Realm
            listDAOLocal.saveAndReplaceAppList(listID, DTOListItemMapper.map(listItems));
        }
    }

}
