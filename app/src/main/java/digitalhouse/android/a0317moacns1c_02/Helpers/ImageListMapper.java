package digitalhouse.android.a0317moacns1c_02.Helpers;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ImageListItem;

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
                String url = ImageHelper.getProfileURL(cast.getProfile_path(), 1);
                imageListItem.setImageURL(url);
                imageList.add(imageListItem);
            }
        }
        return imageList;
    }
}
