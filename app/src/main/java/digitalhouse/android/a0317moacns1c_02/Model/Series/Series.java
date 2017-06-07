package digitalhouse.android.a0317moacns1c_02.Model.Series;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gregorio Martin on 4/6/2017.
 */

public class Series {

    @SerializedName("poster_path")
    private String posterPath;
    private Double popularity;
    private Integer id;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("vote_average")
    private Double voteAverage;
    private String overview;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("origin_country")
    private List<String> originCountries;
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("original_lenguage")
    private String originalLenguage;
    @SerializedName("vote_count")
    private Integer voteCount;
    private String name;
    @SerializedName("original_name")
    private String originalName;

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
        return firstAirDate;
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

    public String getOriginalLenguage() {
        return originalLenguage;
    }

    public void setOriginalLenguage(String originalLenguage) {
        this.originalLenguage = originalLenguage;
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
}
