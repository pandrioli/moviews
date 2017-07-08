package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dh3 on 07/07/17.
 */

public class SerieDTO extends RealmObject {

    @PrimaryKey
    private Integer id;

    //DETAILS
    private String name;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private Double voteAverage;
    private Integer voteCount;
    private String firstAirDate;
    private String originalLenguage;
    private String originalName;
    private String homepage;
    private Integer numberOfEpisodes;
    private Integer numberOfSeasons;
    private String status;
    private String type;

    //DETAILS LISTS
    private RealmList<GenreDTO> genres = new RealmList<>();
    private RealmList<RealmString> originCountries = new RealmList<>();
    private RealmList<RealmString> createdBy = new RealmList<>();
    private RealmList<RealmString> productionCompanies = new RealmList<>();

    //EXTERNAL IDS
    private String imdbId;

    //CREDITS
    private RealmList<CastDTO> cast = new RealmList<>();
    private RealmList<CrewDTO> crew = new RealmList<>();

    //MEDIA
    private RealmList<RealmString> posters = new RealmList<>();
    private RealmList<RealmString> backdrops = new RealmList<>();
    private RealmList<RealmString> videos = new RealmList<>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getOriginalLenguage() {
        return originalLenguage;
    }

    public void setOriginalLenguage(String originalLenguage) {
        this.originalLenguage = originalLenguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public RealmList<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<GenreDTO> genres) {
        this.genres = genres;
    }

    public RealmList<RealmString> getOriginCountries() {
        return originCountries;
    }

    public void setOriginCountries(RealmList<RealmString> originCountries) {
        this.originCountries = originCountries;
    }

    public RealmList<RealmString> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(RealmList<RealmString> createdBy) {
        this.createdBy = createdBy;
    }

    public RealmList<RealmString> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(RealmList<RealmString> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
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

