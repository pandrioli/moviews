package digitalhouse.android.a0317moacns1c_02.Helpers;


import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Model.Media.Image;
import digitalhouse.android.a0317moacns1c_02.Controller.ConfigController;

/**
 * Created by Gregorio Martin on 28/5/2017.
 */

public class ImageMapper {

    public static ArrayList<String> map(List<Image> imageList){
        ArrayList<String> listaUrls = new ArrayList<>();
        for(Image image : imageList){
            listaUrls.add(map(image));
        }
        return listaUrls;
    }

    public static String map(Image image){
        String url = ConfigController.getInstance().getImagesBaseURL();
        url += ConfigController.getInstance().getBackdropSizes().get(1);
        url += image.getFile_path();
        return url;
    }
}
