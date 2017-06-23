package digitalhouse.android.a0317moacns1c_02.DAO;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.OMDBClient;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Callbacks.TMDBCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.RateOmdb;
import digitalhouse.android.a0317moacns1c_02.Model.General.RatingsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieOMDB;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.OMDBRequest;
import digitalhouse.android.a0317moacns1c_02.Services.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by Pablo on 03/06/2017.
 */

public class MovieDAO {
    private TMDBClient tmdbClient;
    private OMDBClient omdbClient;

    public MovieDAO() {
        this.tmdbClient = ServiceGenerator.getInstance().createService(TMDBClient.class, TMDBClient.BASE_URL);
        this.omdbClient = ServiceGenerator.getInstance().createService(OMDBClient.class, OMDBClient.BASE_URL);
    }


    // obtener todos los datos de una pelicula (clase Movie)
    public void obtainMovie(Integer id, ResultListener<Movie> resultListener) {
        ObtainMovieTask obtainMovieTask = new ObtainMovieTask(resultListener);
        obtainMovieTask.execute(id.toString());
    }

    // obtencion de listas de peliculas (TODO: usar DISCOVER)
    public void obtainPopular(ResultListener<MovieResultsContainer> resultListener) {
        Call<MovieResultsContainer> call = tmdbClient.obtainPopularMovies(TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<MovieResultsContainer>(resultListener));
    }
    public void obtainNowPlaying(ResultListener<MovieResultsContainer> resultListener) {
        Call<MovieResultsContainer> call = tmdbClient.obtainNowPlayingMovies(TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<MovieResultsContainer>(resultListener));
    }
    public void obtainUpcoming(ResultListener<MovieResultsContainer> resultListener) {
        Call<MovieResultsContainer> call = tmdbClient.obtainUpcomingMovies(TMDBClient.API_KEY);
        call.enqueue(new TMDBCallBack<MovieResultsContainer>(resultListener));
    }

    public MovieOMDB obtainDetailsShort(String title){
        return mockMovieShort();
    }

    public MovieOMDB obtainDetailsLong(String title){
        return mockMovieLong();
    }


    // task para obtener todos los datos de una Movie
    private class ObtainMovieTask extends AsyncTask<String, Void, Movie> {

        private ResultListener<Movie> resultListener;

        public ObtainMovieTask(ResultListener<Movie> resultListener) {
            this.resultListener = resultListener;
        }

        @Override
        protected Movie doInBackground(String... params) {
            String id = params[0];
            Movie movie = new Movie();
            Call<MovieDetails> callDetails = tmdbClient.obtainMovieDetails(id, TMDBClient.API_KEY);
            Call<Credits> callCredits = tmdbClient.obtainMovieCredits(id, TMDBClient.API_KEY);
            Call<ImageContainer> callImages = tmdbClient.obtainMovieImages(id, TMDBClient.API_KEY);
            Call<VideoContainer> callVideos = tmdbClient.obtainMovieVideos(id, TMDBClient.API_KEY);
            try {
                movie.setMovieDetails(callDetails.execute().body());
                movie.setCredits(callCredits.execute().body());
                movie.setImageContainer(callImages.execute().body());
                movie.setVideoContainer(callVideos.execute().body());
                if (movie.getMovieDetails().getImdb_id() != null) {
                    OMDBRequest request = new OMDBRequest();
                    request.setImdbId(movie.getMovieDetails().getImdb_id());
                    Call<MovieOMDB> callOMDB = omdbClient.obtainMovie(request.getQueryMap());
                    movie.setMovieOMDB(callOMDB.execute().body());
                }
                movie.calculateRatings();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return movie;
        }

        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);
            resultListener.finish(movie);
        }
    }


    private MovieOMDB mockMovieLong(){
        MovieOMDB mock = new MovieOMDB();
        mock.setTitle("Wonder Woman");
        mock.setYear("2017");
        mock.setRated("PG-13");
        mock.setReleased("02 Jun 2017");
        mock.setRuntime("141 min");
        mock.setGenre("Action, Adventure, Fantasy");
        mock.setDirector("Patty Jenkins");
        mock.setWriter("Allan Heinberg");
        mock.setActors("Gal Gadot, Chris Pine, Connie Nielsen, Robin Wright");
        mock.setPlot("Before she was Wonder Woman, she was Diana, princess of the Amazons, trained to be an unconquerable warrior. Raised on a sheltered island paradise, when a pilot crashes on their shores and tells of a massive conflict raging in the outside world, Diana leaves her home, convinced she can stop the threat. Fighting alongside man in a war to end all wars, Diana will discover her full powers and her true destiny.");
        mock.setLanguage("English, German");
        mock.setCountry("USA, China, Hong Kong");
        mock.setAwards("1 nomination.");
        mock.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BNDFmZjgyMTEtYTk5MC00NmY0LWJhZjktOWY2MzI5YjkzODNlXkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_SX300.jpg");
        mock.setMetascore("76");
        mock.setImdbRating("8.3");
        mock.setImdbVotes("61,125");
        mock.setImdbID("tt0451279");
        mock.setType("movie");
        mock.setDvd("N/A");
        mock.setBoxOffice("$103,251,471");
        mock.setProduction("Warner Bros. Pictures");
        mock.setWebsite("http://wonderwomanfilm.com/");
        ArrayList<RateOmdb> listRatings = new ArrayList<>();
        RateOmdb rate = new RateOmdb();
        rate.setSource("Internet Movie Database");
        rate.setValue("8.3/10");
        listRatings.add(rate);
        rate = new RateOmdb();
        rate.setSource("Rotten Tomatoes");
        rate.setValue("93%");
        listRatings.add(rate);
        rate = new RateOmdb();
        rate.setSource("Metacritic");
        rate.setValue("76/100");
        listRatings.add(rate);
        return mock;
    }

    private MovieOMDB mockMovieShort(){
        MovieOMDB mock = new MovieOMDB();
        mock.setTitle("Wonder Woman");
        mock.setYear("2017");
        mock.setRated("PG-13");
        mock.setReleased("02 Jun 2017");
        mock.setRuntime("141 min");
        mock.setGenre("Action, Adventure, Fantasy");
        mock.setDirector("Patty Jenkins");
        mock.setWriter("Allan Heinberg");
        mock.setActors("Gal Gadot, Chris Pine, Connie Nielsen, Robin Wright");
        mock.setPlot("Before she was Wonder Woman she was Diana, princess of the Amazons, trained warrior. When a pilot crashes and tells of conflict in the outside world, she leaves home to fight a war to end all wars, discovering her full powers and true destiny.");
        mock.setLanguage("English, German");
        mock.setCountry("USA, China, Hong Kong");
        mock.setAwards("1 nomination.");
        mock.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BNDFmZjgyMTEtYTk5MC00NmY0LWJhZjktOWY2MzI5YjkzODNlXkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_SX300.jpg");
        mock.setMetascore("76");
        mock.setImdbRating("8.3");
        mock.setImdbVotes("61,125");
        mock.setImdbID("tt0451279");
        mock.setType("movie");
        mock.setDvd("N/A");
        mock.setBoxOffice("$103,251,471");
        mock.setProduction("Warner Bros. Pictures");
        mock.setWebsite("http://wonderwomanfilm.com/");
        ArrayList<RateOmdb> listRatings = new ArrayList<>();
        RateOmdb rate = new RateOmdb();
        rate.setSource("Internet Movie Database");
        rate.setValue("8.3/10");
        listRatings.add(rate);
        rate = new RateOmdb();
        rate.setSource("Rotten Tomatoes");
        rate.setValue("93%");
        listRatings.add(rate);
        rate = new RateOmdb();
        rate.setSource("Metacritic");
        rate.setValue("76/100");
        listRatings.add(rate);
        return mock;
    }
}
