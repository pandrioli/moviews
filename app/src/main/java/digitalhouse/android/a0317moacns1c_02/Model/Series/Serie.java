package digitalhouse.android.a0317moacns1c_02.Model.Series;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Model.General.Network;
import digitalhouse.android.a0317moacns1c_02.Model.General.RateOmdb;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.Misc.Company;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonBase;

/**
 * Created by forev on 15-Jun-17.
 */

public class Serie {

    private SerieDetails serieDetails;
    private SerieOmdb serieOmdb;
    private String longPlot;
    private String shortPlot;

    public Serie(SerieDetails serieDetails, SerieOmdb serieOmdb){
        this.serieDetails = serieDetails;
        this.serieOmdb = serieOmdb;
    }

    public List<PersonBase> getCreatedBy() {
        return serieDetails.createdBy;
    }

    public List<Integer> getEpisodeRunTimes() {
        return serieDetails.episodeRunTimes;
    }

    public List<Genre> getGenres() {
        return serieDetails.genres;
    }

    public String getHomepage() {
        return serieDetails.homepage;
    }

    public Boolean getInProduction() {
        return serieDetails.inProduction;
    }

    public List<String> getLanguages() {
        return serieDetails.languages;
    }

    public String getLastAirDate() {
        return serieDetails.lastAirDate;
    }

    public List<Network> getNetworks() {
        return serieDetails.networks;
    }

    public String getNumberOfEpisodes() {
        if(serieDetails.numberOfEpisodes != null)
            return serieDetails.numberOfEpisodes.toString();
        else
            return "N/A";
    }

    public String getNumberOfSeasons() {
        if(serieDetails.numberOfSeasons != null)
            return serieDetails.numberOfSeasons.toString();
        else
            return "N/A";
    }

    public List<Company> getProductionCompanies() {
        return serieDetails.productionCompanies;
    }

    public List<Season> getSeasons() {
        return serieDetails.seasons;
    }

    public String getStatus() {
        return serieDetails.status;
    }

    public String getFirstAirDate() {
        String year = null;
        String month = null;
        String day = null;
        if(serieDetails.firstAirDate != null && serieDetails.firstAirDate.length() > 9){
            year = serieDetails.firstAirDate.substring(0,4);
            month = serieDetails.firstAirDate.substring(5,7);
            day = serieDetails.firstAirDate.substring(8,10);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(day).append("/");
        stringBuilder.append(month).append("/");
        stringBuilder.append(year);

        return stringBuilder.toString();
    }

    public List<String> getOriginCountries() {
        return serieDetails.originCountries;
    }

    public List<Integer> getGenreIds() {
        return serieDetails.genreIds;
    }

    public String getOriginalLanguage() {
        return serieDetails.originalLanguage;
    }

    public Integer getVoteCount() {
        return serieDetails.voteCount;
    }

    public String getTmdbRating(){
        return serieDetails.getVoteAverage().toString();
    }

    public String getName() {
        return serieDetails.name;
    }

    public String getOriginalName() {
        return serieDetails.originalName;
    }

    public String getYear() {
        if (serieDetails.firstAirDate==null) return "";
        if (serieDetails.firstAirDate.length()>4) return serieDetails.firstAirDate.substring(0,4);
        return "";
    }

    public String getMetascore() {
        return serieOmdb.getMetascore();
    }

    public String getYearPeriod() {
        return serieOmdb.getYear();
    }

    public String getRated() {
        return serieOmdb.getRated();
    }

    public String getRuntimeString() {
        return serieOmdb.getRuntime();
    }

    public String getGenreString() {
        return serieOmdb.getGenre();
    }

    public String getDirector() {
        return serieOmdb.getDirector();
    }

    public String getWriter() {
        return serieOmdb.getWriter();
    }

    public String getActorsString() {
        return serieOmdb.getActors();
    }

    public String getPlot() {
        return serieOmdb.getPlot();
    }

    public String getLanguagesString() {
        return serieOmdb.getLanguage();
    }

    public String getCountriesString() {
        return serieOmdb.getCountry();
    }

    public String getAwardsString() {
        return serieOmdb.getAwards();
    }

    public String getPosterURL() {
        return serieOmdb.getPoster();
    }

    public String getImdbRating() {
        return serieOmdb.getImdbRating();
    }

    public String getImdbNumberOfVotes() {
        return serieOmdb.getImdbVotes();
    }

    public String getImdbID() {
        return serieOmdb.getImdbID();
    }

    public List<RateOmdb> getRatings() {
        return serieOmdb.getRatings();
    }

}
