package digitalhouse.android.a0317moacns1c_02.Mappers;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonCastCreditItem;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonCredits;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonCrewCreditItem;

/**
 * Created by Pablo on 21/06/2017.
 */

public class ImageListMapper {
    public static ArrayList<ImageListItem> map(Credits credits) {
        ArrayList<ImageListItem> imageList = new ArrayList<>();
        for (Cast cast : credits.getCast()) {
            if (cast.getProfile_path() != null) {
                ImageListItem imageListItem = new ImageListItem();
                imageListItem.setId(cast.getId());
                imageListItem.setTitle(cast.getName());
                imageListItem.setSubtitle(cast.getCharacter());
                imageListItem.setImagePath(cast.getProfile_path());
                if (!cast.getProfile_path().isEmpty() && !cast.getProfile_path().equals("null"))
                {
                    String url = ImageHelper.getProfileURL(cast.getProfile_path(), 1);
                    imageListItem.setImageURL(url);
                }
                imageList.add(imageListItem);
            }
        }
        return imageList;
    }
    public static ArrayList<ImageListItem> map(PersonCredits personCredits) {
        ArrayList<ImageListItem> imageList = new ArrayList<ImageListItem>();
        for (PersonCastCreditItem cast : personCredits.getCast()) {
            ImageListItem imageListItem = new ImageListItem();
            imageListItem.setId(cast.getId());
            imageListItem.setTitle(cast.getTitle());
            imageListItem.setSubtitle(cast.getCharacter());
            imageListItem.setImageURL(ImageHelper.getPosterURL(cast.getPoster_path(), 1));
            imageList.add(imageListItem);
        }
        for (PersonCrewCreditItem crew : personCredits.getCrew()) {
            ImageListItem imageListItem = new ImageListItem();
            imageListItem.setId(crew.getId());
            imageListItem.setTitle(crew.getTitle());
            imageListItem.setSubtitle(crew.getJob());
            imageListItem.setImageURL(ImageHelper.getPosterURL(crew.getPoster_path(), 1));
            imageList.add(imageListItem);
        }
        return imageList;
    }

}
