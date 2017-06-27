package digitalhouse.android.a0317moacns1c_02.Helpers;

import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Crew;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CastDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CrewDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.GenreDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.MovieDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.StringDTO;
import digitalhouse.android.a0317moacns1c_02.Model.General.Company;
import digitalhouse.android.a0317moacns1c_02.Model.General.Country;
import digitalhouse.android.a0317moacns1c_02.Model.General.Language;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.Media.Image;
import digitalhouse.android.a0317moacns1c_02.Model.Media.Video;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import io.realm.RealmList;

/**
 * Created by Pablo on 26/06/2017.
 */

public class POJO2DTOMapper {

    //MOVIE
    public static MovieDTO map(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();

        //Details
        movieDTO.setId(movie.getMovieDetails().getId());
        movieDTO.setBackdropPath(movie.getMovieDetails().getBackdrop_path());
        movieDTO.setBudget(movie.getMovieDetails().getBudget());
        movieDTO.setHomepage(movie.getMovieDetails().getHomepage());
        movieDTO.setImdbId(movie.getMovieDetails().getImdb_id());
        movieDTO.setOriginalLanguage(movie.getMovieDetails().getOriginal_language());
        movieDTO.setOriginalTitle(movie.getMovieDetails().getOriginal_title());
        movieDTO.setOverview(movie.getMovieDetails().getOverview());
        movieDTO.setPopularity(movie.getMovieDetails().getPopularity());
        movieDTO.setPosterPath(movie.getMovieDetails().getPoster_path());
        movieDTO.setReleaseDate(movie.getMovieDetails().getRelease_date());
        movieDTO.setRevenue(movie.getMovieDetails().getRevenue());
        movieDTO.setRuntime(movie.getMovieDetails().getRuntime());
        movieDTO.setStatus(movie.getMovieDetails().getStatus());
        movieDTO.setTagline(movie.getMovieDetails().getTagline());
        movieDTO.setTitle(movie.getMovieDetails().getTitle());
        movieDTO.setVideo(movie.getMovieDetails().getVideo());

        //Details lists
        RealmList<GenreDTO> genres = new RealmList<>();
        for (Genre genre : movie.getMovieDetails().getGenres()) {
            genres.add(map(genre));
        }
        movieDTO.setGenres(genres);

        RealmList<StringDTO> productionCompanies = new RealmList<>();
        for (Company company : movie.getMovieDetails().getProduction_companies()) {
            StringDTO stringDTO = new StringDTO();
            stringDTO.setValue(company.getName());
            productionCompanies.add(stringDTO);
        }
        movieDTO.setProductionCountries(productionCompanies);

        RealmList<StringDTO> productionCountries = new RealmList<>();
        for (Country country : movie.getMovieDetails().getProduction_countries()) {
            StringDTO stringDTO = new StringDTO();
            stringDTO.setValue(country.getName());
            productionCountries.add(stringDTO);
        }
        movieDTO.setProductionCountries(productionCountries);

        RealmList<StringDTO> spokenLanguages = new RealmList<>();
        for (Language language : movie.getMovieDetails().getSpoken_languages()) {
            StringDTO stringDTO = new StringDTO();
            stringDTO.setValue(language.getName());
            spokenLanguages.add(stringDTO);
        }
        movieDTO.setSpokenLanguages(spokenLanguages);


        //Cast
        RealmList<CastDTO> castList = new RealmList<>();
        for (Cast cast : movie.getCredits().getCast()) {
            castList.add(map(cast));
        }
        movieDTO.setCast(castList);

        //Crew
        RealmList<CrewDTO> crewList = new RealmList<>();
        for (Crew crew : movie.getCredits().getCrew()) {
            crewList.add(map(crew));
        }
        movieDTO.setCrew(crewList);

        //Posters
        RealmList<StringDTO> posters = new RealmList<>();
        for (Image image : movie.getImageContainer().getPosters()) {
            StringDTO stringDTO = new StringDTO();
            stringDTO.setValue(image.getFile_path());
            posters.add(stringDTO);
        }
        movieDTO.setPosters(posters);

        //Backdrops
        RealmList<StringDTO> backdrops = new RealmList<>();
        for (Image image : movie.getImageContainer().getBackdrops()) {
            StringDTO stringDTO = new StringDTO();
            stringDTO.setValue(image.getFile_path());
            backdrops.add(stringDTO);
        }
        movieDTO.setBackdrops(backdrops);

        //Videos
        RealmList<StringDTO> videos = new RealmList<>();
        for (Video video : movie.getVideoContainer().getVideos()) {
            StringDTO stringDTO = new StringDTO();
            stringDTO.setValue(video.getKey());
            videos.add(stringDTO);
        }
        movieDTO.setVideos(videos);

        //Ratings
        movieDTO.setRatingImdb(movie.getRatingsContainer().getImdb());
        movieDTO.setRatingTmdb(movie.getRatingsContainer().getTmdb());
        movieDTO.setRatingRottenTomatoes(movie.getRatingsContainer().getRottenTomatoes());
        movieDTO.setRatingMetascore(movie.getRatingsContainer().getMetaScore());
        movieDTO.setRatingMoviews(movie.getRatingsContainer().getMoviews());
        movieDTO.setVotesImdb(movie.getRatingsContainer().getImdbVotes());
        movieDTO.setVotesTmdb(movie.getRatingsContainer().getTmdbVotes());

        //OMDB extra data
        movieDTO.setOmdbAwards(movie.getMovieOMDB().getAwards());
        movieDTO.setOmdbRated(movie.getMovieOMDB().getRated());
        movieDTO.setOmdbPlot(movie.getMovieOMDB().getPlot());
        movieDTO.setOmdbCountry(movie.getMovieOMDB().getCountry());

        return movieDTO;
    }


    //GENERAL
    public static GenreDTO map(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

    public static CastDTO map(Cast cast) {
        CastDTO castDTO = new CastDTO();
        castDTO.setId(cast.getId());
        castDTO.setName(cast.getName());
        castDTO.setCharacter(cast.getCharacter());
        castDTO.setProfilePath(cast.getProfile_path());
        castDTO.setOrder(cast.getOrder());
        return castDTO;
    }

    public static CrewDTO map(Crew crew) {
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setId(crew.getId());
        crewDTO.setDepartment(crew.getDepartment());
        crewDTO.setJob(crew.getJob());
        crewDTO.setName(crew.getName());
        crewDTO.setProfilePath(crew.getProfile_path());
        return crewDTO;
    }

}
