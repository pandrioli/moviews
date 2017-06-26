package digitalhouse.android.a0317moacns1c_02.Helpers;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Model.General.ImageListItem;

/**
 * Created by Pablo on 25/06/2017.
 */

public class ImageViewMapper {
    public static String map(String url) {
        if (url.contains("http")) {
            url = ImageHelper.getImagePathFromFullURL(url);
        }
        return ImageHelper.getOriginalSizeURL(url);
    }
    public static String map(ImageListItem imageListItem) {
        String url = imageListItem.getImagePath();
        return ImageHelper.getOriginalSizeURL(url);
    }

    public static <T> ArrayList<String> map(List<T> items) {
        ArrayList<String> urlResults = new ArrayList<>();
        for (T item : items) {
            if (item instanceof String) urlResults.add(map((String)item));
            if (item instanceof ImageListItem) urlResults.add(map((ImageListItem) item));
        }
        return urlResults;
    }
}
