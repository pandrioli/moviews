package digitalhouse.android.a0317moacns1c_02.Model.Series;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class SerieResult implements Serializable {

    @SerializedName("poster_path")
    protected String posterPath;
    protected Double popularity;
    protected Integer id;
    @SerializedName("backdrop_path")
    protected String backdropPath;
    @SerializedName("vote_average")
    protected Double voteAverage;
    protected String overview;
    @SerializedName("first_air_date")
    protected String firstAirDate;
    @SerializedName("origin_country")
    protected List<String> originCountries;
    @SerializedName("genre_ids")
    protected List<Integer> genreIds;
    @SerializedName("original_language")
    protected String originalLanguage;
    @SerializedName("vote_count")
    protected Integer voteCount;
    protected String name;
    @SerializedName("original_name")
    protected String originalName;

    public SerieResult(){

    }


    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return DateHelper.apiDateToString(firstAirDate);
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<String> getOriginCountries() {
        return originCountries;
    }

    public void setOriginCountries(List<String> originCountries) {
        this.originCountries = originCountries;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLenguage) {
        this.originalLanguage = originalLenguage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getYear() {
        if (firstAirDate==null) return "";
        if (firstAirDate.length()>4) return firstAirDate.substring(0,4);
        return "";
    }

}
