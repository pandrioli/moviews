package digitalhouse.android.a0317moacns1c_02.Mappers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.ListItemDTO;
import digitalhouse.android.a0317moacns1c_02.Model.General.RatingsContainer;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResult;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonResult;
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
        if (movie.getVote_count()>RatingsContainer.TMDB_MIN_VOTES)
            item.setRating(movie.getVote_average().toString());
        else
            item.setRating("");
        item.setImageURL(ImageHelper.getPosterURL(movie.getPoster_path(),1));
        item.setType(ListItem.TYPE_MOVIE);
        return item;
    }
    // mapeo SerieResult a ListItem
    public static ListItem map(SerieResult serie){
        ListItem item = new ListItem();
        item.setId(serie.getId());
        item.setTitle(serie.getName());
        item.setGenres(GenreController.getInstance().getGenresStringbyIds(serie.getGenreIds(), ", "));
        item.setYear(serie.getYear());
        DecimalFormat df = new DecimalFormat("#.0");
        if (serie.getVoteCount()>RatingsContainer.TMDB_MIN_VOTES)
            item.setRating(df.format(serie.getVoteAverage()));
        else
            item.setRating("");
        item.setImageURL(ImageHelper.getPosterURL(serie.getPosterPath(),1));
        item.setType(ListItem.TYPE_SERIE);
        return item;
    }
    // mapeo PersonResult a ListItem
    public static ListItem map(PersonResult person) {
        ListItem item = new ListItem();
        item.setId(person.getId());
        item.setTitle(person.getName());
        DecimalFormat df = new DecimalFormat("#.0");
        item.setRating(df.format(person.getPopularity()));
        item.setImageURL(ImageHelper.getProfileURL(person.getProfile_path(),1));
        item.setType(ListItem.TYPE_PERSON);
        return item;
    }


    //mapeo de listas
    public static <T> ArrayList<ListItem> map(List<T> items) {
        ArrayList<ListItem> listItems = new ArrayList<>();
        for (T item : items) {
            if (item instanceof MovieResult) listItems.add(map((MovieResult)item));
            if (item instanceof SerieResult) listItems.add(map((SerieResult)item));
            if (item instanceof PersonResult) listItems.add(map((PersonResult)item));
        }
        ArrayList<ListItem> listItemsWithImage = new ArrayList<>();
        for (ListItem item : listItems) {
            if (!item.getImageURL().contains("null"))
                    listItemsWithImage.add(item);
        }
        return listItemsWithImage;
    }
}
