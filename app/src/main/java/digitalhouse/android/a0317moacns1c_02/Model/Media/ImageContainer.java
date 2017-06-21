package digitalhouse.android.a0317moacns1c_02.Model.Media;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Helpers.ImageMapper;

/**
 * Created by Pablo on 25/05/2017.
 */

public class ImageContainer implements Serializable {
    private Integer id;
    private ArrayList<Image> backdrops;
    private ArrayList<Image> posters;

    public ImageContainer(){
        backdrops = new ArrayList<>();
        posters = new ArrayList<>();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<Image> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<Image> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<Image> posters) {
        this.posters = posters;
    }

    public List<String> getURLsList() {
        ArrayList<String> URLsArray = ImageMapper.map(backdrops);
        return URLsArray;
    }
}
