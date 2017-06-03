package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Media.ImageData;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Media.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Person.PersonCastCreditItem;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Person.PersonCrewCreditItem;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Person.PersonImages;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Person.PersonMovieCredits;
import digitalhouse.android.a0317moacns1c_02.Model.DAO.PersonDAO;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;

/**
 * Created by Pablo on 03/06/2017.
 */

public class PersonController {
    private PersonDAO personDAO;
    private static PersonController instance;

    public static PersonController getInstance() {
        if (instance == null) instance = new PersonController();
        return instance;
    }

    public PersonController() {personDAO = new PersonDAO();}

    public void getDetails(Integer id, TMDBClient.APICallback callback) {
        personDAO.obtainDetails(id, callback);
    }

    public void getImageList(Integer id, final TMDBClient.APICallback callback) {
        personDAO.obtainImages(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                PersonImages personImages = (PersonImages) result;
                List<ImageListItem> imageList = new ArrayList<>();
                for (ImageData imageData : personImages.getProfiles()) {
                    ImageListItem imageListItem = new ImageListItem();
                    imageListItem.setImageURL(ImageHelper.getProfileURL(imageData.getFile_path(), 1));
                    imageListItem.setId(0);
                    imageList.add(imageListItem);
                }
                // saca la primer imagen porque es igual a la de la foto de perfil
                if (imageList.size()>0) imageList.remove(0);
                callback.onSuccess(imageList);
            }
        });
    }

    public void getMovieCreditsImageList(Integer id, final TMDBClient.APICallback callback) {
        personDAO.obtainMovieCredits(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                PersonMovieCredits movieCredits = (PersonMovieCredits) result;
                List<ImageListItem> imageList = new ArrayList<ImageListItem>();
                for (PersonCastCreditItem cast : movieCredits.getCast()) {
                    ImageListItem imageListItem = new ImageListItem();
                    imageListItem.setId(cast.getId());
                    imageListItem.setTitle(cast.getTitle());
                    imageListItem.setSubtitle(cast.getCharacter());
                    imageListItem.setImageURL(ImageHelper.getPosterURL(cast.getPoster_path(), 1));
                    imageList.add(imageListItem);
                }
                for (PersonCrewCreditItem crew : movieCredits.getCrew()) {
                    ImageListItem imageListItem = new ImageListItem();
                    imageListItem.setId(crew.getId());
                    imageListItem.setTitle(crew.getTitle());
                    imageListItem.setSubtitle(crew.getJob());
                    imageListItem.setImageURL(ImageHelper.getPosterURL(crew.getPoster_path(), 1));
                    imageList.add(imageListItem);
                }
                callback.onSuccess(imageList);
            }
        });
    }


}
