package digitalhouse.android.a0317moacns1c_02.Mappers;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Crew;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CastDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CrewDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.GenreDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.MovieDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.RealmString;
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
import io.realm.RealmList;

/**
 * Created by Pablo on 26/06/2017.
 */

public class DTOMovieMapper {

    //POJO TO DTO MAPPER
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
            genres.add(DTOGeneralMapper.map(genre));
        }
        movieDTO.setGenres(genres);

        RealmList<RealmString> productionCompanies = new RealmList<>();
        for (Company company : movie.getMovieDetails().getProduction_companies()) {
            productionCompanies.add(new RealmString(company.getName()));
        }
        movieDTO.setProductionCountries(productionCompanies);

        RealmList<RealmString> productionCountries = new RealmList<>();
        for (Country country : movie.getMovieDetails().getProduction_countries()) {
            productionCountries.add(new RealmString(country.getName()));
        }
        movieDTO.setProductionCountries(productionCountries);

        RealmList<RealmString> spokenLanguages = new RealmList<>();
        for (Language language : movie.getMovieDetails().getSpoken_languages()) {
            spokenLanguages.add(new RealmString(language.getName()));
        }
        movieDTO.setSpokenLanguages(spokenLanguages);


        //Cast
        RealmList<CastDTO> castList = new RealmList<>();
        for (Cast cast : movie.getCredits().getCast()) {
            castList.add(DTOGeneralMapper.map(cast));
        }
        movieDTO.setCast(castList);

        //Crew
        RealmList<CrewDTO> crewList = new RealmList<>();
        for (Crew crew : movie.getCredits().getCrew()) {
            crewList.add(DTOGeneralMapper.map(crew));
        }
        movieDTO.setCrew(crewList);

        //Posters
        RealmList<RealmString> posters = new RealmList<>();
        for (Image image : movie.getImageContainer().getPosters()) {
            posters.add(new RealmString(image.getFile_path()));
        }
        movieDTO.setPosters(posters);

        //Backdrops
        RealmList<RealmString> backdrops = new RealmList<>();
        for (Image image : movie.getImageContainer().getBackdrops()) {
            backdrops.add(new RealmString(image.getFile_path()));
        }
        movieDTO.setBackdrops(backdrops);

        //Videos
        RealmList<RealmString> videos = new RealmList<>();
        for (Video video : movie.getVideoContainer().getVideos()) {
            videos.add(new RealmString(video.getKey()));
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
        if (movie.getMovieOMDB()!=null) {
            movieDTO.setOmdbAwards(movie.getMovieOMDB().getAwards());
            movieDTO.setOmdbRated(movie.getMovieOMDB().getRated());
            movieDTO.setOmdbPlot(movie.getMovieOMDB().getPlot());
            movieDTO.setOmdbCountry(movie.getMovieOMDB().getCountry());
        }

        return movieDTO;
    }




    //MOVIE DTO TO POJO MAPPER
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
            genres.add(DTOGeneralMapper.map(genreDTO));
        }
        movie.getMovieDetails().setGenres(genres);

        ArrayList<Language> languages = new ArrayList<>();
        for (RealmString stringDTO : movieDTO.getSpokenLanguages()) {
            Language language = new Language();
            language.setName(stringDTO.getValue());
            languages.add(language);
        }
        movie.getMovieDetails().setSpoken_languages(languages);

        ArrayList<Company> companies = new ArrayList<>();
        for (RealmString stringDTO : movieDTO.getProductionCompanies()) {
            Company company = new Company();
            company.setName(stringDTO.getValue());
            companies.add(company);
        }
        movie.getMovieDetails().setProduction_companies(companies);

        ArrayList<Country> countries = new ArrayList<>();
        for (RealmString stringDTO : movieDTO.getProductionCountries()) {
            Country country = new Country();
            country.setName(stringDTO.getValue());
            countries.add(country);
        }
        movie.getMovieDetails().setProduction_countries(countries);

        //Credits

        ArrayList<Cast> castList = new ArrayList<>();
        for (CastDTO castDTO : movieDTO.getCast()) {
            castList.add(DTOGeneralMapper.map(castDTO));
        }
        movie.getCredits().setCast(castList);

        ArrayList<Crew> crewList = new ArrayList<>();
        for (CrewDTO crewDTO : movieDTO.getCrew()) {
            crewList.add(DTOGeneralMapper.map(crewDTO));
        }
        movie.getCredits().setCrew(crewList);

        //Media

        ArrayList<Image> posters = new ArrayList<>();
        for (RealmString stringDTO : movieDTO.getPosters()) {
            Image image = new Image();
            image.setFile_path(stringDTO.getValue());
            posters.add(image);
        }
        movie.getImageContainer().setPosters(posters);

        ArrayList<Image> backdrops = new ArrayList<>();
        for (RealmString stringDTO : movieDTO.getBackdrops()) {
            Image image = new Image();
            image.setFile_path(stringDTO.getValue());
            backdrops.add(image);
        }
        movie.getImageContainer().setBackdrops(backdrops);

        ArrayList<Video> videos = new ArrayList<>();
        for (RealmString stringDTO : movieDTO.getVideos()) {
            Video video = new Video();
            video.setSite("YouTube");
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


}
