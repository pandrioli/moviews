package digitalhouse.android.a0317moacns1c_02.Helpers;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Model.General.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResult;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieResult;

/**
 * Created by Pablo on 09/06/2017.
 */

public class ListItemMapper {
    // mapeo MovieResult a ListItem
    public static ListItem map(MovieResult movie){
        ListItem item = new ListItem();
        item.setId(movie.getId());
        item.setTitle(movie.getTitle());
        item.setGenres(GenreController.getInstance().getGenresStringbyIds(movie.getGenre_ids(), ", "));
        item.setYear(movie.getYear());
        item.setRating(movie.getVote_average().toString());
        item.setPosterURL(ImageHelper.getPosterURL(movie.getPoster_path(),1));
        item.setType("movie");
        return item;
    }
    // mapeo SerieResult a ListItem
    public static ListItem map(SerieResult serie){
        ListItem item = new ListItem();
        item.setId(serie.getId());
        item.setTitle(serie.getName());
        item.setGenres(GenreController.getInstance().getGenresStringbyIds(serie.getGenreIds(), ", "));
        item.setYear(serie.getYear());
        item.setRating(serie.getVoteAverage().toString());
        item.setPosterURL(ImageHelper.getPosterURL(serie.getPosterPath(),1));
        item.setType("serie");
        return item;
    }
    public static <T> List<ListItem> map(List<T> results) {
        List<ListItem> listItems = new ArrayList<>();
        for (T result : results) {
            if (result instanceof MovieResult) listItems.add(map((MovieResult)result));
            if (result instanceof SerieResult) listItems.add(map((SerieResult)result));
        }
        return listItems;
    }
}
