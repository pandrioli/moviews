package digitalhouse.android.a0317moacns1c_02.Model.General;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;

/**
 * Created by forev on 15-Jun-17.
 */

public class RatingsContainer implements Serializable {
    private static final String IMDB_SOURCE = "Internet Movie Database";
    private static final String ROTTEN_TOMATOES_SOURCE = "Rotten Tomatoes";
    private static final String METACRITIC_SOURCE = "Metacritic";
    private static final String NO_RATE = "N/A";
    private static final Double TMDB_TOTAL = 10.0;
    private static final Double IMDB_TOTAL = 10.0;
    private static final Double ROTTEN_TOMATOES_TOTAL = 100.0;
    private static final Double METACRITIC_TOTAL = 100.0;
    private static final Integer TMDB_MIN_VOTES = 20;
    private static final Integer IMDB_MIN_VOTES = 100;
    private Double tmdb;
    private Double imdb;
    private Double rottenTomatoes;
    private Double metaScore;
    private Double moviews;

    public RatingsContainer(Movie movie) {
        validateTmdbRating(movie.getMovieDetails().getVote_average(), movie.getMovieDetails().getVote_count());
        parseOmdbRatings(movie.getMovieOMDB());
        calculateMoviewsRating();
    }

    public RatingsContainer(Serie serie) {
        validateTmdbRating(serie.getSerieDetails().getVoteAverage(), serie.getVoteCount());
        parseOmdbRatings(serie.getSerieOmdb());
        calculateMoviewsRating();
    }

    public Double getTmdb() {
        return tmdb;
    }

    public Double getImdb() {
        return imdb;
    }

    public Integer getRottenTomatoes() {
        return rottenTomatoes.intValue();
    }

    public Integer getMetaScore() {
        return metaScore.intValue();
    }

    public Integer getMoviews() {
        return moviews.intValue();
    }

    private void validateTmdbRating(Double voteAverage, Integer totalVotes) {
        if (totalVotes>TMDB_MIN_VOTES) tmdb = voteAverage;
    };

    private void parseOmdbRatings(OmdbBaseResponse omdbData) {
        if (omdbData==null) return;
        if (omdbData.getRatings()==null) return;
        List<RateOmdb> ratings = omdbData.getRatings();
        Integer imdbVotes = parseImdbVotes(omdbData.getImdbVotes());
        for (RateOmdb rating : ratings) {
            Double ratingValue = parseRating(rating.getValue());
            switch (rating.getSource()) {
                case IMDB_SOURCE:
                    if (imdbVotes>IMDB_MIN_VOTES) imdb = ratingValue;
                    break;
                case ROTTEN_TOMATOES_SOURCE:
                    rottenTomatoes = ratingValue;
                    break;
                case METACRITIC_SOURCE:
                    metaScore = ratingValue;
                    break;
            }
        }
    }

    private Integer parseImdbVotes(String imdbVotesString) {
        if (imdbVotesString==null) return 0;
        Integer commaPos = imdbVotesString.indexOf(",");
        if (commaPos>-1) {
            imdbVotesString = imdbVotesString.substring(0,commaPos) +
                    imdbVotesString.substring(commaPos+1, imdbVotesString.length());
        }
        Integer imdbVotes;
        try {
            imdbVotes = Integer.parseInt(imdbVotesString);
        } catch (Exception e) {
            imdbVotes = null;
        }
        return imdbVotes;
    }
    private Double parseRating(String ratingString) {
        Integer slashPos = ratingString.indexOf("/");
        Integer percentPos = ratingString.indexOf("%");
        if (slashPos!=-1) {
            ratingString = ratingString.substring(0, slashPos);
        }
        if (percentPos!=-1) {
            ratingString = ratingString.substring(0, percentPos);
        }
        Double ratingValue = null;
        try {
            ratingValue = Double.parseDouble(ratingString);
        } catch (Exception e) {
            ratingValue = null;
        }
        return ratingValue;
    }

    private void calculateMoviewsRating() {
        Integer ratingCounter = 0;
        Double ratingAccum = 0.0;
        if (tmdb!=null) {
            ratingCounter++;
            ratingAccum += tmdb/TMDB_TOTAL;
        }
        if (imdb!=null) {
            ratingCounter++;
            ratingAccum += imdb/IMDB_TOTAL;
        }
        if (rottenTomatoes!=null) {
            ratingCounter++;
            ratingAccum += rottenTomatoes/ROTTEN_TOMATOES_TOTAL;
        }
        if (metaScore !=null) {
            ratingCounter++;
            ratingAccum += metaScore /METACRITIC_TOTAL;
        }
        moviews = (double)Math.round(ratingAccum/ratingCounter*100);
    }

    public Boolean hasMetascoreAndRottenScore(){
        return metaScore !=null && rottenTomatoes!=null;
    }
}
