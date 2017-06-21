package digitalhouse.android.a0317moacns1c_02.Model.Media;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Pablo on 25/05/2017.
 */

public class Image implements Serializable {
    private Double aspect_ratio;
    private String file_path;
    private Integer height;
    private String iso_639_1;
    private Double vote_average;
    private Double vote_count;
    private Integer width;

    protected Image(Parcel in) {
        file_path = in.readString();
        iso_639_1 = in.readString();
    }

    public Double getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(Double aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public Double getVote_count() {
        return vote_count;
    }

    public void setVote_count(Double vote_count) {
        this.vote_count = vote_count;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
