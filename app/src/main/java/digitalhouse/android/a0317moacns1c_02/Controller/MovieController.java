package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallback;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.DAO.MovieDAOLocal;
import digitalhouse.android.a0317moacns1c_02.Mappers.DTOMovieMapper;
import digitalhouse.android.a0317moacns1c_02.Helpers.Toaster;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.MovieDTO;
import digitalhouse.android.a0317moacns1c_02.Model.General.Review;
import digitalhouse.android.a0317moacns1c_02.Model.General.ReviewContainer;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.DAO.MovieDAOInternet;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;

/**
 * Created by Pablo on 03/06/2017.
 */

public class MovieController {
    private MovieDAOInternet movieDAOInternet;
    private MovieDAOLocal movieDAOLocal;
    private static MovieController instance;

    public static MovieController getInstance() {
        if (instance == null) instance = new MovieController();
        return instance;
    }

    private MovieController() {
        movieDAOInternet = new MovieDAOInternet();
        movieDAOLocal = new MovieDAOLocal();
    }


    // Prueba de base de datos Realm
    // La primera vez que se entra a la movie carga de internet y guarda en la base de datos
    // Una vez que la movie esta en la base de datos, la proxima vez la trae de ahí
    public void getMovie(Integer id, final ResultListener<Movie> resultListener) {
        MovieDTO movieDTO = movieDAOLocal.obtainMovie(id);
        if (movieDTO==null) {
            // no esta en la base de datos local, pide movie a internet y guarda en base de datos local
            movieDAOInternet.obtainMovie(id, new ResultListener<Movie>() {
                @Override
                public void finish(Movie movie) {
                    if (movie!=null) {
                        movieDAOLocal.saveMovie(DTOMovieMapper.map(movie));
                        //Toaster.getInstance().toast("Datos traidos de internet");
                    }
                    resultListener.finish(movie);
                }
            });
        } else { // esta guardada en la base local, mapea movieDTO recibida y devuelve movie
            resultListener.finish(DTOMovieMapper.map(movieDTO));
            //Toaster.getInstance().toast("Datos en base de datos local");
        }
    }

    public void getReviews(Integer id, final ResultListener<List<Review>> resultListener) {
        movieDAOInternet.obtainReviews(id, new ResultListener<ReviewContainer>() {
            @Override
            public void finish(ReviewContainer result) {
                if (result!=null) {
                    if (result.getResults().isEmpty()) {
                        result.getResults().add(new Review());
                    }
                    resultListener.finish(result.getResults());
                }
                else resultListener.finish(new ArrayList<Review>());
            }
        });
    }


    public void saveMovie(Movie movie) {
        movieDAOLocal.saveMovie(DTOMovieMapper.map(movie));
    }
}
