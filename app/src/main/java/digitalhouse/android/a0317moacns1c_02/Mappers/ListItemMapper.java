package digitalhouse.android.a0317moacns1c_02.Mappers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.ListItemDTO;
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
        item.setGenres(GenreController.getInstance().getMovieGenresStringbyIds(movie.getGenre_ids(), ", "));
        item.setYear(movie.getYear());
        item.setRating(movie.getVote_average().toString());
        item.setImageURL(ImageHelper.getPosterURL(movie.getPoster_path(),1));
        item.setType("movie");
        return item;
    }
    // mapeo SerieResult a ListItem
    public static ListItem map(SerieResult serie){
        ListItem item = new ListItem();
        item.setId(serie.getId());
        item.setTitle(serie.getName());
        item.setGenres(GenreController.getInstance().getSerieGenresStringbyIds(serie.getGenreIds(), ", "));
        item.setYear(serie.getYear());
        DecimalFormat df = new DecimalFormat("#.0");
        item.setRating(df.format(serie.getVoteAverage()));
        item.setImageURL(ImageHelper.getPosterURL(serie.getPosterPath(),1));
        item.setType("serieDetails");
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
        item.setType("person");
        return item;
    }

    // mapeo ListItemDTO a ListItem
    public static ListItem map(ListItemDTO listItemDTO) {
        ListItem item = new ListItem();
        item.setId(listItemDTO.getId());
        item.setYear(listItemDTO.getYear());
        item.setGenres(listItemDTO.getGenres());
        item.setRating(listItemDTO.getRating());
        item.setImageURL(listItemDTO.getImageURL());
        item.setType(listItemDTO.getType());
        return item;
    }

    //mapeo de listas
    public static <T> ArrayList<ListItem> map(List<T> items) {
        ArrayList<ListItem> listItems = new ArrayList<>();
        for (T item : items) {
            if (item instanceof MovieResult) listItems.add(map((MovieResult)item));
            if (item instanceof SerieResult) listItems.add(map((SerieResult)item));
            if (item instanceof PersonResult) listItems.add(map((PersonResult)item));
            if (item instanceof ListItemDTO) listItems.add(map((ListItemDTO)item));
        }
        return listItems;
    }
}
