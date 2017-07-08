package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Mappers.ImageListMapper;
import digitalhouse.android.a0317moacns1c_02.Model.Media.Image;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonCastCreditItem;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonCrewCreditItem;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonImages;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonCredits;
import digitalhouse.android.a0317moacns1c_02.DAO.PersonDAO;
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

    public void getDetails(Integer id, ResultListener<PersonDetails> resultListener) {
        personDAO.obtainDetails(id, resultListener);
    }

    public void getImageList(Integer id, final ResultListener<ArrayList<ImageListItem>> resultListener) {
        personDAO.obtainImages(id, new ResultListener<PersonImages>() {
            @Override
            public void finish(PersonImages personImages) {
                ArrayList<ImageListItem> imageList = new ArrayList<>();
                for (Image image : personImages.getProfiles()) {
                    ImageListItem imageListItem = new ImageListItem();
                    imageListItem.setImagePath(image.getFile_path());
                    imageListItem.setImageURL(ImageHelper.getProfileURL(image.getFile_path(), 1));
                    imageListItem.setId(0);
                    imageList.add(imageListItem);
                }
                // saca la primer imagen porque es igual a la de la foto de perfil
                if (imageList.size()>0) imageList.remove(0);
                resultListener.finish(imageList);
            }
        });
    }

    public void getMovieCreditsImageList(Integer id, final ResultListener<ArrayList<ImageListItem>> resultListener) {
        personDAO.obtainMovieCredits(id, new ResultListener<PersonCredits>() {
            @Override
            public void finish(PersonCredits movieCredits) {
                resultListener.finish(ImageListMapper.map(movieCredits));
            }
        });
    }
    public void getTVCreditsImageList(Integer id, final ResultListener<ArrayList<ImageListItem>> resultListener) {
        personDAO.obtainTVCredits(id, new ResultListener<PersonCredits>() {
            @Override
            public void finish(PersonCredits tvCredits) {
                resultListener.finish(ImageListMapper.map(tvCredits));
            }
        });
    }


}
