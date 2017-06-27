package digitalhouse.android.a0317moacns1c_02.Helpers;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Crew;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CastDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CrewDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.GenreDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.MovieDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.StringDTO;
import digitalhouse.android.a0317moacns1c_02.Model.General.Company;
import digitalhouse.android.a0317moacns1c_02.Model.General.Country;
import digitalhouse.android.a0317moacns1c_02.Model.General.Language;
import digitalhouse.android.a0317moacns1c_02.Model.General.RatingsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.Media.Image;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.Video;
import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieOMDB;

/**
 * Created by Pablo on 27/06/2017.
 */

public class DTO2POJOMapper {
    //MOVIE
    public static Movie map(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setMovieDetails(new MovieDetails());
        movie.setMovieOMDB(new MovieOMDB());
        movie.setCredits(new Credits());
        movie.setImageContainer(new ImageContainer());
        movie.setVideoContainer(new VideoContainer());
        movie.setRatingsContainer(new RatingsContainer());

        //Details
        movie.getMovieDetails().setId(movieDTO.getId());
        movie.getMovieDetails().setBackdrop_path(movieDTO.getBackdropPath());
        movie.getMovieDetails().setBudget(movieDTO.getBudget());
        movie.getMovieDetails().setHomepage(movieDTO.getHomepage());
        movie.getMovieDetails().setImdb_id(movieDTO.getImdbId());
        movie.getMovieDetails().setOriginal_language(movieDTO.getOriginalLanguage());
        movie.getMovieDetails().setOriginal_title(movieDTO.getOriginalTitle());
        movie.getMovieDetails().setOverview(movieDTO.getOverview());
        movie.getMovieDetails().setPopularity(movieDTO.getPopularity());
        movie.getMovieDetails().setPoster_path(movieDTO.getPosterPath());
        movie.getMovieDetails().setRelease_date(movieDTO.getReleaseDate());
        movie.getMovieDetails().setRevenue(movieDTO.getRevenue());
        movie.getMovieDetails().setRuntime(movieDTO.getRuntime());
        movie.getMovieDetails().setStatus(movieDTO.getStatus());
        movie.getMovieDetails().setTagline(movieDTO.getTagline());
        movie.getMovieDetails().setTitle(movieDTO.getTitle());
        movie.getMovieDetails().setVideo(movieDTO.getVideo());

        //Details lists

        ArrayList<Genre> genres = new ArrayList<>();
        for (GenreDTO genreDTO : movieDTO.getGenres()) {
            genres.add(map(genreDTO));
        }
        movie.getMovieDetails().setGenres(genres);

        ArrayList<Language> languages = new ArrayList<>();
        for (StringDTO stringDTO : movieDTO.getSpokenLanguages()) {
            Language language = new Language();
            language.setName(stringDTO.getValue());
            languages.add(language);
        }
        movie.getMovieDetails().setSpoken_languages(languages);

        ArrayList<Company> companies = new ArrayList<>();
        for (StringDTO stringDTO : movieDTO.getProductionCompanies()) {
            Company company = new Company();
            company.setName(stringDTO.getValue());
            companies.add(company);
        }
        movie.getMovieDetails().setProduction_companies(companies);

        ArrayList<Country> countries = new ArrayList<>();
        for (StringDTO stringDTO : movieDTO.getProductionCountries()) {
            Country country = new Country();
            country.setName(stringDTO.getValue());
            countries.add(country);
        }
        movie.getMovieDetails().setProduction_countries(countries);

        //Credits

        ArrayList<Cast> castList = new ArrayList<>();
        for (CastDTO castDTO : movieDTO.getCast()) {
            castList.add(map(castDTO));
        }
        movie.getCredits().setCast(castList);

        ArrayList<Crew> crewList = new ArrayList<>();
        for (CrewDTO crewDTO : movieDTO.getCrew()) {
            crewList.add(map(crewDTO));
        }
        movie.getCredits().setCrew(crewList);

        //Media

        ArrayList<Image> posters = new ArrayList<>();
        for (StringDTO stringDTO : movieDTO.getPosters()) {
            Image image = new Image();
            image.setFile_path(stringDTO.getValue());
            posters.add(image);
        }
        movie.getImageContainer().setPosters(posters);

        ArrayList<Image> backdrops = new ArrayList<>();
        for (StringDTO stringDTO : movieDTO.getBackdrops()) {
            Image image = new Image();
            image.setFile_path(stringDTO.getValue());
            backdrops.add(image);
        }
        movie.getImageContainer().setBackdrops(backdrops);

        ArrayList<Video> videos = new ArrayList<>();
        for (StringDTO stringDTO : movieDTO.getVideos()) {
            Video video = new Video();
            video.setKey(stringDTO.getValue());
            videos.add(video);
        }
        movie.getVideoContainer().setVideos(videos);

        //Ratings
        movie.getRatingsContainer().setImdb(movieDTO.getRatingImdb());
        movie.getRatingsContainer().setTmdb(movieDTO.getRatingTmdb());
        movie.getRatingsContainer().setRottenTomatoes(movieDTO.getRatingRottenTomatoes());
        movie.getRatingsContainer().setMetaScore(movieDTO.getRatingMetascore());
        movie.getRatingsContainer().setMoviews(movieDTO.getRatingMoviews());
        movie.getRatingsContainer().setImdbVotes(movieDTO.getVotesImdb());
        movie.getRatingsContainer().setTmdbVotes(movieDTO.getVotesTmdb());

        //OMDB extra data
        movie.getMovieOMDB().setAwards(movieDTO.getOmdbAwards());
        movie.getMovieOMDB().setRated(movieDTO.getOmdbRated());
        movie.getMovieOMDB().setPlot(movieDTO.getOmdbPlot());
        movie.getMovieOMDB().setCountry(movieDTO.getOmdbCountry());

        return movie;
    }


    //GENERAL

    public static Genre map(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }

    public static Cast map(CastDTO castDTO) {
        Cast cast = new Cast();
        cast.setId(castDTO.getId());
        cast.setName(castDTO.getName());
        cast.setCharacter(castDTO.getCharacter());
        cast.setProfile_path(castDTO.getProfilePath());
        cast.setOrder(castDTO.getOrder());
        return cast;
    }

    public static Crew map(CrewDTO crewDTO) {
        Crew crew = new Crew();
        crew.setId(crewDTO.getId());
        crew.setDepartment(crewDTO.getDepartment());
        crew.setJob(crewDTO.getJob());
        crew.setName(crewDTO.getName());
        crew.setProfile_path(crewDTO.getProfilePath());
        return crew;
    }

}
