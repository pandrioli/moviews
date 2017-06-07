package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.MovieResultsCallBack;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageData;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieImages;
import digitalhouse.android.a0317moacns1c_02.DAO.MovieDAO;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageMapper;

/**
 * Created by Pablo on 03/06/2017.
 */

public class MovieController extends ObtainerController {
    private MovieDAO movieDAO;
    private static MovieController instance;

    public static MovieController getInstance() {
        if (instance == null) instance = new MovieController();
        return instance;
    }

    public MovieController() {
        movieDAO = new MovieDAO();
    }

    public void getPopular(TMDBClient.APICallback callback) {
        movieDAO.obtainPopular(new MovieResultsCallBack(callback));
    }
    public void getNowPlaying(TMDBClient.APICallback callback) {
        movieDAO.obtainPopular(new MovieResultsCallBack(callback));
    }
    public void getUpcoming(TMDBClient.APICallback callback) {
        movieDAO.obtainUpcoming(new MovieResultsCallBack(callback));
    }

    public void getDetails(Integer id, TMDBClient.APICallback callback) {
        movieDAO.obtainDetails(id, callback);
    }

    public void getImages(Integer id, TMDBClient.APICallback callback) {
        movieDAO.obtainImages(id, callback);
    }

    public void getBackdropsURLs(Integer id, final TMDBClient.APICallback callback) {
        getImages(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                MovieImages movieImages = (MovieImages) result;
                List<ImageData> imageList = movieImages.getBackdrops();
                ArrayList<String> URLsArray = ImageMapper.map(imageList);
                callback.onSuccess(URLsArray);
            }
        });
    }

    public void getCredits(Integer id, TMDBClient.APICallback callback) {
        movieDAO.obtainCredits(id, callback);
    }

    public ArrayList<ImageListItem> getCastImageList(Credits credits) {
        ArrayList<ImageListItem> imageList = new ArrayList<>();
        for (Cast cast : credits.getCast()) {
            ImageListItem imageListItem = new ImageListItem();
            imageListItem.setId(cast.getId());
            imageListItem.setTitle(cast.getName());
            imageListItem.setSubtitle(cast.getCharacter());
            String url = ImageHelper.getProfileURL(cast.getProfile_path(), 1);
            imageListItem.setImageURL(url);
            imageList.add(imageListItem);
        }
        return imageList;
    }

}
