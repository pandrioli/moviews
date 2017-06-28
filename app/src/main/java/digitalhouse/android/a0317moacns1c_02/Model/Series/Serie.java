package digitalhouse.android.a0317moacns1c_02.Model.Series;import java.io.Serializable;import java.util.ArrayList;import java.util.List;import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;import digitalhouse.android.a0317moacns1c_02.Model.General.ExternalIDs;import digitalhouse.android.a0317moacns1c_02.Model.General.RatingsContainer;import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;import digitalhouse.android.a0317moacns1c_02.Model.General.Network;import digitalhouse.android.a0317moacns1c_02.Model.General.RateOmdb;import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;import digitalhouse.android.a0317moacns1c_02.Model.General.Company;import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonBase;/** * Created by forev on 15-Jun-17. */public class Serie implements Serializable {    private SerieDetails serieDetails;    private SerieOmdb serieOmdb;    private List<Season> seasons = new ArrayList<>();    private ImageContainer imagesContainer;    private Credits credits;    private VideoContainer videoContainer;    private RatingsContainer ratingsContainer;    private ExternalIDs externalIDs;    public void putSeason(Season season){        seasons.add(season);    }    public Season getSeason(Integer position) {        Season seasonRtn = seasons.get(position);        if(seasonRtn != null) return seasonRtn;        return seasons.get(1);    }    public SerieDetails getSerieDetails() {        return serieDetails;    }    public void setSerieDetails(SerieDetails serieDetails) {        this.serieDetails = serieDetails;    }    public SerieOmdb getSerieOmdb() {        return serieOmdb;    }    public void setSerieOmdb(SerieOmdb serieOmdb) {        this.serieOmdb = serieOmdb;    }    public ImageContainer getImagesContainer() {        return imagesContainer;    }    public void setImagesContainer(ImageContainer imagesContainer) {        this.imagesContainer = imagesContainer;    }    public Credits getCredits() {        return credits;    }    public void setCredits(Credits credits) {        this.credits = credits;    }    public VideoContainer getVideoContainer() {        return videoContainer;    }    public void setVideoContainer(VideoContainer videoContainer) {        this.videoContainer = videoContainer;    }    public Integer getID(){        return serieDetails.getId();    }    public String getPosterPath(){        return serieDetails.getPosterPath();    }    public String getBackDropPath(){        return serieDetails.getBackdropPath();    }    public List<Genre> getGenres() {        return serieDetails.genres;    }    public List<Network> getNetworks() {        return serieDetails.networks;    }    public String getNumberOfEpisodes() {        if(serieDetails.numberOfEpisodes != null)            return serieDetails.numberOfEpisodes.toString();        else            return "N/A";    }    public String getNumberOfSeasons() {        if(serieDetails.numberOfSeasons != null)            return serieDetails.numberOfSeasons.toString();        else            return "N/A";    }    public String getStatus() {        return serieDetails.status;    }    public String getFirstAirDate() {        return serieDetails.getFirstAirDate();    }    public Integer getVoteCount() {        return serieDetails.voteCount;    }    public String getName() {        return serieDetails.name;    }    public String getYear() {        if (serieDetails.firstAirDate==null) return "";        if (serieDetails.firstAirDate.length()>4) return serieDetails.firstAirDate.substring(0,4);        return "";    }    public String getOverview(){        return serieDetails.getOverview();    }    public RatingsContainer getRatingsContainer() {        return ratingsContainer;    }    public void calculateRatings() {        this.ratingsContainer = new RatingsContainer(this);    }    public void setExternalIDs(ExternalIDs externalIDs) {        this.externalIDs = externalIDs;    }}