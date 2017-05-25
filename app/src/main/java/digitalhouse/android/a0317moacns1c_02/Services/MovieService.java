package digitalhouse.android.a0317moacns1c_02.Services;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.GenreCalls;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.MovieCalls;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Genres.GenreAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Genres.GenresAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Images.ImageItemAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Images.ImagesAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieDetails.MovieDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Videos.VideoItemAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Videos.VideosAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieData;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieImage;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieResults.MovieResultsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieResults.MovieResultsItemAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieVideo;


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
    public void getGenres(final TMDBClient.APICallback callback) {
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

    public void getMovieData(String id, final TMDBClient.APICallback callback) {
        MovieCalls.obtainDetails(id, client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                MovieDetailsAPI movieDetails = (MovieDetailsAPI) result;
                String genres = "";
                for (GenreAPI genre : movieDetails.getGenres()) {
                    genres += genre.getName()+", ";
                }
                genres = genres.substring(0, genres.length()-2);
                MovieData movieData = new MovieData();
                movieData.setGenres(genres);
                movieData.setId(movieDetails.getId());
                movieData.setTitle(movieDetails.getTitle());
                movieData.setYear(movieDetails.getRelease_date().substring(0,4));
                movieData.setReleaseDate(movieDetails.getRelease_date());
                movieData.setOverview(movieDetails.getOverview());
                movieData.setRating(movieDetails.getVote_average());
                movieData.setTagline(movieDetails.getTagline());
                movieData.setRuntime(movieDetails.getRuntime());
                movieData.setPoster(movieDetails.getPoster_path());
                movieData.setBackdrop(movieDetails.getBackdrop_path());
                callback.onSuccess(movieData);
            }
        });
    }

    public void getMoviePosters(String id, final TMDBClient.APICallback callback) {
        MovieCalls.obtainImages(id, client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                ImagesAPI imagesAPI = (ImagesAPI) result;
                List<MovieImage> imageList = new ArrayList<>();
                for (ImageItemAPI imageAPI : imagesAPI.getPosters()) {
                    MovieImage image = new MovieImage();
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

    public void getMovieBackdrops(String id, final TMDBClient.APICallback callback) {
        MovieCalls.obtainImages(id, client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                ImagesAPI imagesAPI = (ImagesAPI) result;
                List<MovieImage> imageList = new ArrayList<>();
                for (ImageItemAPI imageAPI : imagesAPI.getBackdrops()) {
                    MovieImage image = new MovieImage();
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

    public void getMovieVideos(String id, final TMDBClient.APICallback callback) {
        MovieCalls.obtainVideos(id, client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                VideosAPI videosAPI = (VideosAPI) result;
                List<MovieVideo> videoList = new ArrayList<>();
                for (VideoItemAPI videoAPI : videosAPI.getResults()) {
                    MovieVideo video = new MovieVideo();
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

    public void getPopularMovies(final TMDBClient.APICallback callback) {
        MovieCalls.obtainPopular(client, new MovieResultsCallBack(callback));
    }

    public void getNowPlayingMovies(final TMDBClient.APICallback callback) {
        MovieCalls.obtainNowPlaying(client, new MovieResultsCallBack(callback));
    }

    public void getUpcomingMovies(final TMDBClient.APICallback callback) {
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
