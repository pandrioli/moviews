package digitalhouse.android.a0317moacns1c_02.Helpers;


import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.DAO.Media.ImageData;
import digitalhouse.android.a0317moacns1c_02.Services.ConfigurationService;

/**
 * Created by Gregorio Martin on 28/5/2017.
 */

public class ImageMapper {

    public static ArrayList<String> map(List<ImageData> imageDataList){
        ArrayList<String> listaUrls = new ArrayList<>();
        for(ImageData imageData : imageDataList){
            listaUrls.add(map(imageData));
        }
        return listaUrls;
    }

    public static String map(ImageData imageData){
        String url = ConfigurationService.getInstance().getImagesBaseURL();
        url += ConfigurationService.getInstance().getBackdropSizes().get(1);
        url += imageData.getFile_path();
        return url;
    }
}
