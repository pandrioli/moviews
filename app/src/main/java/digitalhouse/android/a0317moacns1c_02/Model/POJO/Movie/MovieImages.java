package digitalhouse.android.a0317moacns1c_02.Model.POJO.Movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Model.POJO.Media.ImageData;

/**
 * Created by Pablo on 25/05/2017.
 */

public class MovieImages implements Parcelable {
    private Integer id;
    private ArrayList<ImageData> backdrops;
    private ArrayList<ImageData> posters;

    protected MovieImages(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieImages> CREATOR = new Creator<MovieImages>() {
        @Override
        public MovieImages createFromParcel(Parcel in) {
            return new MovieImages(in);
        }

        @Override
        public MovieImages[] newArray(int size) {
            return new MovieImages[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<ImageData> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<ImageData> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<ImageData> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<ImageData> posters) {
        this.posters = posters;
    }
}
