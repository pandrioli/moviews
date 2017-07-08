package digitalhouse.android.a0317moacns1c_02.Mappers;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Crew;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CastDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CrewDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.GenreDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.RealmString;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.SerieDTO;
import digitalhouse.android.a0317moacns1c_02.Model.General.Company;
import digitalhouse.android.a0317moacns1c_02.Model.General.ExternalIDs;
import digitalhouse.android.a0317moacns1c_02.Model.General.Network;
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

    //POJO A DTO
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
        serieDTO.setOriginalLenguage(serie.getSerieDetails().getOriginalLanguage());
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
        for (Network network : serie.getSerieDetails().getNetworks()) {
            serieDTO.getNetworks().add(new RealmString(network.getName()));
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


    //DTO a POJO
    public static Serie map(SerieDTO serieDTO) {
        Serie serie = new Serie();
        serie.setSerieDetails(new SerieDetails());
        serie.setCredits(new Credits());
        serie.setSerieOmdb(new SerieOmdb());
        serie.setImagesContainer(new ImageContainer());
        serie.setVideoContainer(new VideoContainer());
        serie.setRatingsContainer(new RatingsContainer());
        serie.setExternalIDs(new ExternalIDs());

        //DETAILS
        serie.getSerieDetails().setId(serieDTO.getId());
        serie.getSerieDetails().setName(serieDTO.getName());
        serie.getSerieDetails().setOverview(serieDTO.getOverview());
        serie.getSerieDetails().setPosterPath(serieDTO.getPosterPath());
        serie.getSerieDetails().setBackdropPath(serieDTO.getBackdropPath());
        serie.getSerieDetails().setVoteAverage(serieDTO.getVoteAverage());
        serie.getSerieDetails().setVoteCount(serieDTO.getVoteCount());
        serie.getSerieDetails().setFirstAirDate(serieDTO.getFirstAirDate());
        serie.getSerieDetails().setOriginalLanguage(serieDTO.getOriginalLenguage());
        serie.getSerieDetails().setOriginalName(serieDTO.getOriginalName());
        serie.getSerieDetails().setHomepage(serieDTO.getHomepage());
        serie.getSerieDetails().setNumberOfEpisodes(serieDTO.getNumberOfEpisodes());
        serie.getSerieDetails().setNumberOfSeasons(serieDTO.getNumberOfSeasons());
        serie.getSerieDetails().setStatus(serieDTO.getStatus());
        serie.getSerieDetails().setType(serieDTO.getType());

        //DETAILS LISTS
        List<Genre> genreList = new ArrayList<>();
        for (GenreDTO genreDTO: serieDTO.getGenres()) {
            genreList.add(DTOGeneralMapper.map(genreDTO));
        }
        serie.getSerieDetails().setGenres(genreList);

        serie.getSerieDetails().setOriginCountries(RealmStringMapper.map(serieDTO.getOriginCountries()));

        List<PersonBase> personList = new ArrayList<>();
        for (RealmString realmString : serieDTO.getCreatedBy()) {
            PersonBase personBase = new PersonBase();
            personBase.setName(realmString.getValue());
            personList.add(personBase);
        }
        serie.getSerieDetails().setCreatedBy(personList);

        List<Company> companyList = new ArrayList<>();
        for (RealmString realmString : serieDTO.getProductionCompanies()) {
            Company company = new Company();
            company.setName(realmString.getValue());
            companyList.add(company);
        }
        serie.getSerieDetails().setProductionCompanies(companyList);

        List<Network> networkList = new ArrayList<>();
        for (RealmString realmString : serieDTO.getNetworks()) {
            Network network = new Network();
            network.setName(realmString.getValue());
            networkList.add(network);
        }
        serie.getSerieDetails().setNetworks(networkList);

        //EXTERNAL ID
        serie.getExternalIDs().setImdb_id(serieDTO.getImdbId());

        //CREDITS
        ArrayList<Cast> castList = new ArrayList<>();
        for (CastDTO castDTO : serieDTO.getCast()) {
            castList.add(DTOGeneralMapper.map(castDTO));
        }
        serie.getCredits().setCast(castList);

        ArrayList<Crew> crewList = new ArrayList<>();
        for (CrewDTO crewDTO : serieDTO.getCrew()) {
            crewList.add(DTOGeneralMapper.map(crewDTO));
        }
        serie.getCredits().setCrew(crewList);

        //MEDIA
        ArrayList<Image> posters = new ArrayList<>();
        for (RealmString realmString : serieDTO.getPosters()) {
            Image image = new Image();
            image.setFile_path(realmString.getValue());
            posters.add(image);
        }
        serie.getImagesContainer().setPosters(posters);

        ArrayList<Image> backdrops = new ArrayList<>();
        for (RealmString realmString : serieDTO.getBackdrops()) {
            Image image = new Image();
            image.setFile_path(realmString.getValue());
            backdrops.add(image);
        }
        serie.getImagesContainer().setBackdrops(backdrops);

        ArrayList<Video> videos = new ArrayList<>();
        for (RealmString realmString : serieDTO.getVideos()) {
            Video video = new Video();
            video.setSite("YouTube");
            video.setKey(realmString.getValue());
            videos.add(video);
        }
        serie.getVideoContainer().setVideos(videos);

        //RATINGS
        serie.getRatingsContainer().setImdb(serieDTO.getRatingImdb());
        serie.getRatingsContainer().setTmdb(serieDTO.getRatingTmdb());
        serie.getRatingsContainer().setRottenTomatoes(serieDTO.getRatingRottenTomatoes());
        serie.getRatingsContainer().setMetaScore(serieDTO.getRatingMetascore());
        serie.getRatingsContainer().setMoviews(serieDTO.getRatingMoviews());
        serie.getRatingsContainer().setImdbVotes(serieDTO.getVotesImdb());
        serie.getRatingsContainer().setTmdbVotes(serieDTO.getVotesTmdb());

        //OMDB extra data
        serie.getSerieOmdb().setAwards(serieDTO.getOmdbAwards());
        serie.getSerieOmdb().setRated(serieDTO.getOmdbRated());
        serie.getSerieOmdb().setPlot(serieDTO.getOmdbPlot());
        serie.getSerieOmdb().setCountry(serieDTO.getOmdbCountry());

        return serie;
    }
}
