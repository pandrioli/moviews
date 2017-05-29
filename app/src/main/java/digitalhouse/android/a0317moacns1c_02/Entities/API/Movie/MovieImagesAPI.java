package digitalhouse.android.a0317moacns1c_02.Entities.API.Movie;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Media.ImageItemAPI;

/**
 * Created by Pablo on 25/05/2017.
 */

public class MovieImagesAPI {
    private Integer id;
    private ArrayList<ImageItemAPI> backdrops;
    private ArrayList<ImageItemAPI> posters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<ImageItemAPI> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<ImageItemAPI> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<ImageItemAPI> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<ImageItemAPI> posters) {
        this.posters = posters;
    }
}
