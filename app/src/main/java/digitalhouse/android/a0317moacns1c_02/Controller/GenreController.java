package digitalhouse.android.a0317moacns1c_02.Controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.DAO.GenreDAOInternet;
import digitalhouse.android.a0317moacns1c_02.DAO.GenreDAOLocal;
import digitalhouse.android.a0317moacns1c_02.Helpers.NetworkHelper;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genres;

/**
 * Created by Pablo on 03/06/2017.
 */

public class GenreController {
    private static GenreController instance;
    private List<Genre> genreList;
    private List<Genre> genreListMovies  = new ArrayList<>();
    private List<Genre> genreListSeries  = new ArrayList<>();
    private List<Genre> selectedMovieGenres;
    private List<Genre> selectedSerieGenres;
    private GenreDAOInternet genreDAOInternet;
    private GenreDAOLocal genreDAOLocal;

    public static GenreController getInstance() {
        if (instance==null) instance = new GenreController();
        return instance;
    }

    private GenreController() {
        genreList = new ArrayList<>();
        genreListMovies = new ArrayList<>();
        genreListSeries = new ArrayList<>();
        selectedMovieGenres = new ArrayList<>();
        selectedSerieGenres = new ArrayList<>();
        genreDAOInternet = new GenreDAOInternet();
        genreDAOLocal = new GenreDAOLocal();
        genreList = genreDAOLocal.obtainGenres();
        if (genreList != null) separateGenres();
    }

    public void loadGenres(final ResultListener<Boolean> resultListener) {
        if (NetworkController.getInstance().isNetworkAvailable()) {
            genreDAOInternet.obtainGenres(new ResultListener<List<Genre>>() {
                @Override
                public void finish(List<Genre> result) {
                    genreList = result;
                    separateGenres();
                    genreDAOLocal.saveGenres(genreList);
                    resultListener.finish(true);
                }
            });
        } else {
            resultListener.finish(genreList!=null);
        }
    }

    public void separateGenres() {
        genreListMovies = new ArrayList<>();
        genreListSeries = new ArrayList<>();
        for (Genre genre : genreList) {

            if(Genre.TYPE_MOVIES.equals(genre.getType())) {
                genreListMovies.add(genre);
            }

            if(Genre.TYPE_SERIES.equals(genre.getType())) {
                genreListSeries.add(genre);
            }
        }
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public List<Genre> getGenreListMovies() {
        return genreListMovies;
    }

    public List<Genre> getGenreListSeries() {
        return genreListSeries;
    }

    public List<Genre> getSelectedMovieGenres() {
        return selectedMovieGenres;
    }

    public void setSelectedMovieGenres(List<Genre> selectedMovieGenres) {
        this.selectedMovieGenres = selectedMovieGenres;
    }

    public List<Genre> getSelectedSerieGenres() {
        return selectedSerieGenres;
    }

    public void setSelectedSerieGenres(List<Genre> selectedSerieGenres) {
        this.selectedSerieGenres = selectedSerieGenres;
    }

    public String getGenreNameById(Integer id) {
        for (Genre genre : genreList) {
            if (genre.getId().equals(id)) return genre.getName();
        }
        return "";
    }

    public String getGenresString(List<Genre> genreList, String separator) {
        String genres = "";
        if (!genreList.isEmpty()) {
            genres = genreList.get(0).getName();
            for (int i=1; i<genreList.size(); i++) {
                genres += separator + genreList.get(i).getName();
            }
        }
        return genres;
    }

    public String getGenresStringbyIds(List<Integer> genreIdList, String separator) {
        List<Genre> genreList = new ArrayList<>();
        for (Integer id : genreIdList) {
            Genre genre = new Genre();
            genre.setId(id);
            genre.setName(getGenreNameById(id));
            genreList.add(genre);
        }
        return getGenresString(genreList, separator);
    }
}
