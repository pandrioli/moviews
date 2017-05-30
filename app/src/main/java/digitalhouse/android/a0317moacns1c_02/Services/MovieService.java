package digitalhouse.android.a0317moacns1c_02.Services;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.GenreCalls;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.MovieCalls;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Credits.CastAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Credits.CreditsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Credits.CrewAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Misc.GenreAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Misc.GenresAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Media.ImageItemAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieImagesAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Media.VideoItemAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Media.VideosAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieCredits;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieData;
import digitalhouse.android.a0317moacns1c_02.Entities.ImageData;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieResultsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Movie.MovieResultsItemAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Entities.CrewListItem;
import digitalhouse.android.a0317moacns1c_02.Entities.VideoData;
import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallBack;


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

    // carga la lista de generos de la API
    public void loadGenres(final TMDBClient.APICallback callback) {
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

    // buscar nombre de genero por id
    public String getGenreNameById(Integer id) {
        for (GenreAPI genre : genreList) {
            if (genre.getId().equals(id)) return genre.getName();
        }
        return "";
    }

    // Obtiene información de una película por id
    // El callback devuelve MovieData
    public void getMovieData(Integer id, final TMDBClient.APICallback callback) {
        MovieCalls.obtainDetails(id.toString(), client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                MovieDetailsAPI movieDetails = (MovieDetailsAPI) result;
                MovieData movieData = new MovieData();
                String genres = "";
                if (!movieDetails.getGenres().isEmpty()) {
                    genres = movieDetails.getGenres().get(0).getName();
                    for (int i=1; i<movieDetails.getGenres().size(); i++) {
                        genres += " | " + movieDetails.getGenres().get(i).getName();
                    }
                }
                movieData.setGenres(genres);
                movieData.setId(movieDetails.getId());
                movieData.setTitle(movieDetails.getTitle());
                if (movieDetails.getRelease_date().length()>4) {
                    movieData.setYear(movieDetails.getRelease_date().substring(0, 4));
                } else {
                    movieData.setYear("");
                }
                movieData.setOverview(movieDetails.getOverview());
                movieData.setRating(movieDetails.getVote_average());
                movieData.setTagline(movieDetails.getTagline());
                movieData.setRuntime(movieDetails.getRuntime());
                movieData.setPosterPath(movieDetails.getPoster_path());
                movieData.setBackdropPath(movieDetails.getBackdrop_path());
                callback.onSuccess(movieData);
            }
        });
    }

    // Obtiene los créditos de la película por id (cast y crew)
    // El callback devuelve MovieCredits
    public void getMovieCredits(Integer id, final TMDBClient.APICallback callback) {
        MovieCalls.obtainCredits(id.toString(), client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                CreditsAPI creditsAPI = (CreditsAPI) result;
                MovieCredits movieCredits = new MovieCredits();
                ArrayList<ImageListItem> castList = new ArrayList<>();
                ArrayList<CrewListItem> crewList = new ArrayList<>();
                for (CastAPI castAPI : creditsAPI.getCast()) {
                    ImageListItem cast = new ImageListItem();
                    cast.setId(castAPI.getId());
                    cast.setTitle(castAPI.getName());
                    cast.setSubtitle(castAPI.getCharacter());
                    String url = ConfigurationService.getInstance().getImagesBaseURL();
                    url += ConfigurationService.getInstance().getProfileSizes().get(1);
                    url += castAPI.getProfile_path();
                    cast.setImageURL(url);
                    castList.add(cast);
                }
                movieCredits.setCastList(castList);
                for (CrewAPI crewAPI : creditsAPI.getCrew()) {
                    CrewListItem crew = new CrewListItem();
                    crew.setId(crewAPI.getId());
                    crew.setName(crewAPI.getName());
                    crew.setDepartment(crewAPI.getDepartment());
                    crew.setJob(crewAPI.getJob());
                    crewList.add(crew);
                }
                movieCredits.setCastList(castList);
                movieCredits.setCrewList(crewList);
                callback.onSuccess(movieCredits);
            }
        });
    }


    // Obtiene los posters de una película por id
    // El callback devuelve ArrayList<ImageData>
    public void getMoviePosters(Integer id, final TMDBClient.APICallback callback) {
        MovieCalls.obtainImages(id.toString(), client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                MovieImagesAPI imagesAPI = (MovieImagesAPI) result;
                List<ImageData> imageList = new ArrayList<>();
                for (ImageItemAPI imageAPI : imagesAPI.getPosters()) {
                    ImageData image = new ImageData();
                    image.setAspectRatio(imageAPI.getAspect_ratio());
                    image.setFilePath(imageAPI.getFile_path());
                    image.setHeight(imageAPI.getHeight());
                    image.setWidth(imageAPI.getWidth());
                    imageList.add(image);
                }
                callback.onSuccess(imageList);
            }
        });
    }

    // Obtiene los backdrops de una película por id
    // El callback devuelve ArrayList<ImageData>
    public void getMovieBackdrops(Integer id, final TMDBClient.APICallback callback) {
        MovieCalls.obtainImages(id.toString(), client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                MovieImagesAPI imagesAPI = (MovieImagesAPI) result;
                List<ImageData> imageList = new ArrayList<>();
                for (ImageItemAPI imageAPI : imagesAPI.getBackdrops()) {
                    ImageData image = new ImageData();
                    image.setAspectRatio(imageAPI.getAspect_ratio());
                    image.setFilePath(imageAPI.getFile_path());
                    image.setHeight(imageAPI.getHeight());
                    image.setWidth(imageAPI.getWidth());
                    imageList.add(image);
                }
                callback.onSuccess(imageList);
            }
        });
    }

    // Obtiene los videos disponibles de la película por id
    // El callback devuelve ArrayList<VideoData>
    public void getMovieVideos(Integer id, final TMDBClient.APICallback callback) {
        MovieCalls.obtainVideos(id.toString(), client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                VideosAPI videosAPI = (VideosAPI) result;
                List<VideoData> videoList = new ArrayList<>();
                for (VideoItemAPI videoAPI : videosAPI.getResults()) {
                    VideoData video = new VideoData();
                    video.setId(videoAPI.getId());
                    video.setKey(videoAPI.getKey());
                    video.setName(videoAPI.getName());
                    video.setSite(videoAPI.getSite());
                    video.setType(videoAPI.getType());
                }
                callback.onSuccess(videoList);
            }
        });
    }

    // obtiene lista de películas populares, devuelve MovieResults
    public void getPopularMovies(final TMDBClient.APICallback callback) {
        MovieCalls.obtainPopular(client, new MovieResultsCallBack(callback));
    }

    // obtiene lista de películas en cartelera, devuelve MovieResults
    public void getNowPlayingMovies(final TMDBClient.APICallback callback) {
        MovieCalls.obtainNowPlaying(client, new MovieResultsCallBack(callback));
    }

    // obtiene lista de películas con próximo estreno, devuelve MovieResults
    public void getUpcomingMovies(final TMDBClient.APICallback callback) {
        MovieCalls.obtainUpcoming(client, new MovieResultsCallBack(callback));
    }


    // Matcheador de MovieResultsAPI a MovieListItem
    public List<MovieListItem> getMovieListItems(MovieResultsAPI movieResults) {
        List<MovieListItem> movieList = new ArrayList<>();
        for (MovieResultsItemAPI movieResultsItem : movieResults.getResults()) {
            MovieListItem movieListItem = new MovieListItem();
            movieListItem.setId(movieResultsItem.getId());
            String genres = "";
            if (!movieResultsItem.getGenre_ids().isEmpty()) {
                genres = getGenreNameById(movieResultsItem.getGenre_ids().get(0));
                for (int i=1; i<movieResultsItem.getGenre_ids().size(); i++) {
                    genres += ", " + getGenreNameById(movieResultsItem.getGenre_ids().get(i));
                }
            }
            movieListItem.setGenres(genres);
            movieListItem.setRating(movieResultsItem.getVote_average().toString());
            movieListItem.setTitle(movieResultsItem.getTitle());
            String url = ConfigurationService.getInstance().getImagesBaseURL();
            url += ConfigurationService.getInstance().getPosterSizes().get(0);
            url += movieResultsItem.getPoster_path();
            movieListItem.setPosterURL(url);
            if(movieResultsItem.getRelease_date().length() > 8)
                movieListItem.setYear(movieResultsItem.getRelease_date().substring(0,4));
            movieList.add(movieListItem);
        }
        return movieList;
    }
}
