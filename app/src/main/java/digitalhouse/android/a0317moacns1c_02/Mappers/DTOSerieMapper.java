package digitalhouse.android.a0317moacns1c_02.Mappers;

import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Crew;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.GenreDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.RealmString;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.SerieDTO;
import digitalhouse.android.a0317moacns1c_02.Model.General.Company;
import digitalhouse.android.a0317moacns1c_02.Model.General.Country;
import digitalhouse.android.a0317moacns1c_02.Model.General.ExternalIDs;
import digitalhouse.android.a0317moacns1c_02.Model.General.RatingsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.Media.Image;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.Video;
import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonBase;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieOmdb;

/**
 * Created by dh3 on 07/07/17.
 */

public class DTOSerieMapper {
    public static SerieDTO map(Serie serie) {
        SerieDTO serieDTO = new SerieDTO();

        //DETAILS
        serieDTO.setId(serie.getID());
        serieDTO.setName(serie.getName());
        serieDTO.setOverview(serie.getSerieDetails().getOverview());
        serieDTO.setPosterPath(serie.getSerieDetails().getPosterPath());
        serieDTO.setBackdropPath(serie.getSerieDetails().getBackdropPath());
        serieDTO.setVoteAverage(serie.getSerieDetails().getVoteAverage());
        serieDTO.setVoteCount(serie.getSerieDetails().getVoteCount());
        serieDTO.setFirstAirDate(serie.getSerieDetails().getFirstAirDate());
        serieDTO.setOriginalLenguage(serie.getSerieDetails().getFirstAirDate());
        serieDTO.setOriginalName(serie.getSerieDetails().getOriginalName());
        serieDTO.setHomepage(serie.getSerieDetails().getHomepage());
        serieDTO.setNumberOfEpisodes(serie.getSerieDetails().getNumberOfEpisodes());
        serieDTO.setNumberOfSeasons(serie.getSerieDetails().getNumberOfSeasons());
        serieDTO.setStatus(serie.getSerieDetails().getStatus());

        //DETAILS LISTS
        for (Genre genre : serie.getSerieDetails().getGenres()) {
            serieDTO.getGenres().add(DTOGeneralMapper.map(genre));
        }
        serieDTO.setOriginCountries(RealmStringMapper.map(serie.getSerieDetails().getOriginCountries()));
        for (PersonBase personBase : serie.getSerieDetails().getCreatedBy()) {
            serieDTO.getCreatedBy().add(new RealmString(personBase.getName()));
        }
        for (Company company : serie.getSerieDetails().getProductionCompanies()) {
            serieDTO.getProductionCompanies().add(new RealmString(company.getName()));
        }

        //IMDB ID
        serieDTO.setImdbId(serie.getExternalIDs().getImdb_id());

        //CREDITS
        for (Cast cast : serie.getCredits().getCast() ) {
            serieDTO.getCast().add(DTOGeneralMapper.map(cast));
        }
        for (Crew crew : serie.getCredits().getCrew()) {
            serieDTO.getCrew().add(DTOGeneralMapper.map(crew));
        }

        //MEDIA
        for (Image image : serie.getImagesContainer().getPosters()) {
            serieDTO.getPosters().add(new RealmString(image.getFile_path()));
        }
        for (Image image : serie.getImagesContainer().getBackdrops()) {
            serieDTO.getBackdrops().add(new RealmString(image.getFile_path()));
        }
        for (Video video : serie.getVideoContainer().getVideos()) {
            serieDTO.getVideos().add(new RealmString(video.getKey()));
        }


        //RATINGS
        serieDTO.setRatingImdb(serie.getRatingsContainer().getImdb());
        serieDTO.setRatingTmdb(serie.getRatingsContainer().getTmdb());
        serieDTO.setRatingRottenTomatoes(serie.getRatingsContainer().getRottenTomatoes());
        serieDTO.setRatingMetascore(serie.getRatingsContainer().getMetaScore());
        serieDTO.setRatingMoviews(serie.getRatingsContainer().getMoviews());
        serieDTO.setVotesImdb(serie.getRatingsContainer().getImdbVotes());
        serieDTO.setVotesTmdb(serie.getRatingsContainer().getTmdbVotes());

        //OMDB
        if (serie.getSerieOmdb() != null) {
            serieDTO.setOmdbAwards(serie.getSerieOmdb().getAwards());
            serieDTO.setOmdbCountry(serie.getSerieOmdb().getCountry());
            serieDTO.setOmdbPlot(serie.getSerieOmdb().getPlot());
            serieDTO.setOmdbRated(serie.getSerieOmdb().getRated());
        }

        return serieDTO;

    }

    public static Serie map(SerieDTO serieDTO) {
        Serie serie = new Serie();
        serie.setSerieDetails(new SerieDetails());
        serie.setCredits(new Credits());
        serie.setSerieOmdb(new SerieOmdb());
        serie.setImagesContainer(new ImageContainer());
        serie.setVideoContainer(new VideoContainer());
        serie.setRatingsContainer(new RatingsContainer());
        serie.setExternalIDs(new ExternalIDs());
        return serie;
    }
}
