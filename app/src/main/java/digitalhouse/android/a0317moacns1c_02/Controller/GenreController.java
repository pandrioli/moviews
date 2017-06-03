package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Model.DAO.GenreDAO;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Genres.Genres;

/**
 * Created by Pablo on 03/06/2017.
 */

public class GenreController {
    private static GenreController instance;
    private List<Genre> genreList;
    public GenreController() {

    }

    public static GenreController getInstance() {
        if (instance == null) instance = new GenreController();
        return instance;
    }

    public void loadGenres(final TMDBClient.APICallback callback) {
        GenreDAO genreDAO = new GenreDAO();
        genreDAO.obtainGenres(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                Genres genres = (Genres) result;
                genreList = genres.getGenres();
                callback.onSuccess("OK");
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
