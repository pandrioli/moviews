package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 26/06/2017.
 */

public class MovieDTO extends RealmObject {

    //DETAILS
    @PrimaryKey
    private Integer id;
    private String backdropPath;
    private Integer budget;
    private String homepage;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Double popularity;
    private String posterPath;
    private String releaseDate;
    private Integer revenue;
    private Integer runtime;
    private String status;
    private String tagline;
    private String title;
    private Boolean video;

    //DETAILS LISTS
    private RealmList<GenreDTO> genres;
    private RealmList<RealmString> spokenLanguages;
    private RealmList<RealmString> productionCompanies;
    private RealmList<RealmString> productionCountries;


    //CREDITS
    private RealmList<CastDTO> cast;
    private RealmList<CrewDTO> crew;

    //MEDIA
    private RealmList<RealmString> posters;
    private RealmList<RealmString> backdrops;
    private RealmList<RealmString> videos;

    //RATINGS
    private Double ratingImdb;
    private Double ratingTmdb;
    private Double ratingRottenTomatoes;
    private Double ratingMetascore;
    private Double ratingMoviews;
    private Integer votesImdb;
    private Integer votesTmdb;

    //OMDB extra data
    private String omdbAwards;
    private String omdbRated;
    private String omdbPlot;
    private String omdbCountry;

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

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public RealmList<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<GenreDTO> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public RealmList<RealmString> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(RealmList<RealmString> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public RealmList<RealmString> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(RealmList<RealmString> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public RealmList<RealmString> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(RealmList<RealmString> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public RealmList<CastDTO> getCast() {
        return cast;
    }

    public void setCast(RealmList<CastDTO> cast) {
        this.cast = cast;
    }

    public RealmList<CrewDTO> getCrew() {
        return crew;
    }

    public void setCrew(RealmList<CrewDTO> crew) {
        this.crew = crew;
    }

    public RealmList<RealmString> getPosters() {
        return posters;
    }

    public void setPosters(RealmList<RealmString> posters) {
        this.posters = posters;
    }

    public RealmList<RealmString> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(RealmList<RealmString> backdrops) {
        this.backdrops = backdrops;
    }

    public RealmList<RealmString> getVideos() {
        return videos;
    }

    public void setVideos(RealmList<RealmString> videos) {
        this.videos = videos;
    }

    public Double getRatingImdb() {
        return ratingImdb;
    }

    public void setRatingImdb(Double ratingImdb) {
        this.ratingImdb = ratingImdb;
    }

    public Double getRatingTmdb() {
        return ratingTmdb;
    }

    public void setRatingTmdb(Double ratingTmdb) {
        this.ratingTmdb = ratingTmdb;
    }

    public Double getRatingRottenTomatoes() {
        return ratingRottenTomatoes;
    }

    public void setRatingRottenTomatoes(Double ratingRottenTomatoes) {
        this.ratingRottenTomatoes = ratingRottenTomatoes;
    }

    public Double getRatingMetascore() {
        return ratingMetascore;
    }

    public void setRatingMetascore(Double ratingMetascore) {
        this.ratingMetascore = ratingMetascore;
    }

    public Double getRatingMoviews() {
        return ratingMoviews;
    }

    public void setRatingMoviews(Double ratingMoviews) {
        this.ratingMoviews = ratingMoviews;
    }

    public Integer getVotesImdb() {
        return votesImdb;
    }

    public void setVotesImdb(Integer votesImdb) {
        this.votesImdb = votesImdb;
    }

    public Integer getVotesTmdb() {
        return votesTmdb;
    }

    public void setVotesTmdb(Integer votesTmdb) {
        this.votesTmdb = votesTmdb;
    }

    public String getOmdbAwards() {
        return omdbAwards;
    }

    public void setOmdbAwards(String omdbAwards) {
        this.omdbAwards = omdbAwards;
    }

    public String getOmdbRated() {
        return omdbRated;
    }

    public void setOmdbRated(String omdbRated) {
        this.omdbRated = omdbRated;
    }

    public String getOmdbPlot() {
        return omdbPlot;
    }

    public void setOmdbPlot(String omdbPlot) {
        this.omdbPlot = omdbPlot;
    }

    public String getOmdbCountry() {
        return omdbCountry;
    }

    public void setOmdbCountry(String omdbCountry) {
        this.omdbCountry = omdbCountry;
    }
}
