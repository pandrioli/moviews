package digitalhouse.android.a0317moacns1c_02.Services;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.GenreCalls;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.MovieCalls;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Genres.GenreAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Genres.GenresAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieResults.MovieResultsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieResults.MovieResultsItemAPI;


public class MovieService {
    private static MovieService instance;
    private TMDBClient client;
    private ArrayList<GenreAPI> genreList;

    public MovieService() {
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }
    public static MovieService getInstance(){
        if (instance == null) instance = new MovieService();
        return instance;
    }

    // obtiene la lista de generos
    public void obtainGenres(final TMDBClient.APICallback callback) {
        GenreCalls.obtainGenres(client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                GenresAPI genres = (GenresAPI) result;
                genreList = genres.getGenres();
                String test = "GenresAPI = ";
                test += genreList.get(0).getName() + ", ";
                test += genreList.get(1).getName() + ", ";
                test += genreList.get(2).getName() + ", etc.";
                callback.onSuccess(test);
            }
        });
    }
    // buscar genero por id
    public String getGenreNameById(Integer id) {
        for (GenreAPI genre : genreList) {
            if (genre.getId().equals(id)) return genre.getName();
        }
        return "";
    }

    public void obtainMovieDetails(String id, final TMDBClient.APICallback callback){
        MovieCalls.obtainMovieDetails(id, client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                callback.onSuccess(result);
            }
        });
    }

    public void obtainPopularMovies(final TMDBClient.APICallback callback) {
        MovieCalls.obtainPopular(client, new MovieResultsCallBack(callback));
    }

    public void obtainNowPlayingMovies(final TMDBClient.APICallback callback) {
        MovieCalls.obtainNowPlaying(client, new MovieResultsCallBack(callback));
    }

    public void obtainUpcomingMovies(final TMDBClient.APICallback callback) {
        MovieCalls.obtainUpcoming(client, new MovieResultsCallBack(callback));
    }

    private List<MovieListItem> getMovieListItems(MovieResultsAPI movieResults) {
        List<MovieListItem> movieList = new ArrayList<>();
        for (MovieResultsItemAPI movieResultsItem : movieResults.getResults()) {
            MovieListItem movieListItem = new MovieListItem();
            movieListItem.setId(movieResultsItem.getId());
            String genres = "";
            for (Integer genreId : movieResultsItem.getGenre_ids()) {
                genres += getGenreNameById(genreId)+", ";
            }
            genres = genres.substring(0, genres.length()-2);
            movieListItem.setGenres(genres);
            movieListItem.setRating(movieResultsItem.getVote_average().toString());
            movieListItem.setTitle(movieResultsItem.getTitle());
            String url = ConfigurationService.getInstance().getImagesBaseURL();
            url += ConfigurationService.getInstance().getPosterSizes().get(0);
            url += movieResultsItem.getPoster_path();
            movieListItem.setPosterURL(url);
            movieListItem.setYear(movieResultsItem.getRelease_date().substring(0,4));
            movieList.add(movieListItem);
        }
        return movieList;
    }

    private class MovieResultsCallBack implements TMDBClient.APICallback {
        private TMDBClient.APICallback callback;

        public MovieResultsCallBack(TMDBClient.APICallback callback) {
            this.callback = callback;
        }

        @Override
        public void onSuccess(Object result) {
            MovieResultsAPI movieResults = (MovieResultsAPI) result;
            List<MovieListItem> movieList = getMovieListItems(movieResults);
            callback.onSuccess(movieList);
        }
    }

}
