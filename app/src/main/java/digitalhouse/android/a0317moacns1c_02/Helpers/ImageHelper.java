package digitalhouse.android.a0317moacns1c_02.Helpers;

import digitalhouse.android.a0317moacns1c_02.Controller.ConfigController;

/**
 * Created by Pablo on 03/06/2017.
 */

public class ImageHelper {
    public static String getPosterURL(String filePath, Integer size) {
        String url = ConfigController.getInstance().getImagesBaseURL();
        url += ConfigController.getInstance().getPosterSizes().get(size);
        url += filePath;
        return url;
    }
    public static String getBackdropURL(String filePath, Integer size) {
        String url = ConfigController.getInstance().getImagesBaseURL();
        url += ConfigController.getInstance().getBackdropSizes().get(size);
        url += filePath;
        return url;
    }
    public static String getProfileURL(String filePath, Integer size) {
        String url = ConfigController.getInstance().getImagesBaseURL();
        url += ConfigController.getInstance().getProfileSizes().get(size);
        url += filePath;
        return url;
    }

}
