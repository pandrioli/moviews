package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.General.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Media.Image;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.DAO.MovieDAO;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageMapper;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieDetails;

/**
 * Created by Pablo on 03/06/2017.
 */

public class MovieController {
    private MovieDAO movieDAO;
    private static MovieController instance;

    public static MovieController getInstance() {
        if (instance == null) instance = new MovieController();
        return instance;
    }

    public MovieController() {
        movieDAO = new MovieDAO();
    }


    //nuevo llamado para obtener los datos de una pelicula de una vez (clase Movie)
    public void getMovie(Integer id, ResultListener<Movie> resultListener) {
        movieDAO.obtainMovie(id, resultListener);
    }

    //llamados a listas de peliculas
    public void getPopular(ResultListener<ArrayList<ListItem>> resultListener) {
        movieDAO.obtainPopular(new MovieResultsCallback(resultListener));
    }
    public void getNowPlaying(ResultListener<ArrayList<ListItem>> resultListener) {
        movieDAO.obtainNowPlaying(new MovieResultsCallback(resultListener));
    }
    public void getUpcoming(ResultListener<ArrayList<ListItem>> resultListener) {
        movieDAO.obtainUpcoming(new MovieResultsCallback(resultListener));
    }
}
