package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.DAO.GenreDAO;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genres;

/**
 * Created by Pablo on 03/06/2017.
 */

public class GenreController {
    private static GenreController instance;
    private List<Genre> genreList;
    private GenreController() {
        genreList = new ArrayList<>();
    }

    public static GenreController getInstance() {
        if (instance == null) instance = new GenreController();
        return instance;
    }

    public void loadMovieGenres(final ResultListener<String> resultListener) {
        GenreDAO genreDAO = new GenreDAO();
        genreDAO.obtainMovieGenres(new ResultListener<Genres>() {
            @Override
            public void finish(Genres genres) {
                genreList.addAll(genres.getGenres());
                resultListener.finish("Movie genres OK");
            }
        });
    }
    public void loadSerieGenres(final ResultListener<String> resultListener) {
        GenreDAO genreDAO = new GenreDAO();
        genreDAO.obtainSerieGenres(new ResultListener<Genres>() {
            @Override
            public void finish(Genres genres) {
                genreList.addAll(genres.getGenres());
                resultListener.finish("SerieDetails genres OK");
            }
        });
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
