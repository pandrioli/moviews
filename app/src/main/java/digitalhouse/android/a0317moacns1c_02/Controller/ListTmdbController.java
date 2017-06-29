package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.DAO.ListDAOLocal;
import digitalhouse.android.a0317moacns1c_02.DAO.MovieDAOInternet;
import digitalhouse.android.a0317moacns1c_02.DAO.SerieDAOInternet;
import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;

/**
 * Created by Pablo on 28/06/2017.
 */

// controller para listas traidas de TMDB

public class ListTmdbController {
    private static final Integer LATEST_DAY_RANGE = 30;
    private static final Integer UPCOMING_DAY_RANGE = 30;

    private MovieDAOInternet movieDAOInternet;
    private SerieDAOInternet serieDAOInternet;
    private ListDAOLocal listDAOLocal;

    private static ListTmdbController instance;
    public static ListTmdbController getInstance() {
        if (instance==null) instance = new ListTmdbController();
        return instance;
    }

    private ListTmdbController () {
        movieDAOInternet = new MovieDAOInternet();
        serieDAOInternet = new SerieDAOInternet();
        listDAOLocal = new ListDAOLocal();
    }

    // movie lists

    public void getMoviesPopular(ResultListener<ArrayList<ListItem>> resultListener) {
        movieDAOInternet.discoverMovies(new HashMap<String, String>(), new MovieResultsCallback(resultListener));
    }

    public void getMoviesLatest(ResultListener<ArrayList<ListItem>> resultListener) {
        Date dateFrom = DateHelper.todayOffset(-LATEST_DAY_RANGE);
        Date dateTo = DateHelper.todayOffset(0);
        movieDAOInternet.discoverMovies(getDateRangeQuery(dateFrom, dateTo),
                new MovieResultsCallback(resultListener));
    }

    public void getMoviesUpcoming(ResultListener<ArrayList<ListItem>> resultListener) {
        Date dateFrom = DateHelper.todayOffset(1);
        Date dateTo = DateHelper.todayOffset(UPCOMING_DAY_RANGE);
        movieDAOInternet.discoverMovies(getDateRangeQuery(dateFrom,dateTo),
                new MovieResultsCallback(resultListener));
    }


    // private methods
    
    private Map<String,String> getDateRangeQuery(Date from, Date to) {
        Map<String, String> queryMap = new HashMap<>();
        String fromString = DateHelper.format(from, DateHelper.FORMAT_API);
        String toString = DateHelper.format(to, DateHelper.FORMAT_API);
        queryMap.put("primary_release_date.gte", fromString);
        queryMap.put("primary_release_date.lte", toString);
        return queryMap;
    }

}
