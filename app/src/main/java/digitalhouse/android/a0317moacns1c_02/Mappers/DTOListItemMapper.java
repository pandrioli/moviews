package digitalhouse.android.a0317moacns1c_02.Mappers;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.ListItemDTO;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import io.realm.RealmList;

/**
 * Created by Pablo on 27/06/2017.
 */

public class DTOListItemMapper {
    //mapeo ListItem a ListItemDTO
    public static ListItemDTO map(ListItem listItem) {
        ListItemDTO listItemDTO = new ListItemDTO();
        listItemDTO.setId(listItem.getId());
        listItemDTO.setTitle(listItem.getTitle());
        listItemDTO.setYear(listItem.getYear());
        listItemDTO.setGenres(listItem.getGenres());
        listItemDTO.setRating(listItem.getRating());
        listItemDTO.setImageURL(listItem.getImageURL());
        listItemDTO.setType(listItem.getType());
        return listItemDTO;
    }
    //mapeo list de ListItems a RealmList de ListItemsDTO
    public static RealmList<ListItemDTO> map(List<ListItem> listItems) {
        RealmList<ListItemDTO> listItemDTOs = new RealmList<>();
        for (ListItem listItem : listItems) {
            listItemDTOs.add(map(listItem));
        }
        return listItemDTOs;
    }

    // mapeo ListItemDTO a ListItem
    public static ListItem map(ListItemDTO listItemDTO) {
        ListItem item = new ListItem();
        item.setId(listItemDTO.getId());
        item.setTitle(listItemDTO.getTitle());
        item.setYear(listItemDTO.getYear());
        item.setGenres(listItemDTO.getGenres());
        item.setRating(listItemDTO.getRating());
        item.setImageURL(listItemDTO.getImageURL());
        item.setType(listItemDTO.getType());
        return item;
    }

    //mapeo list de RealmList de ListItemDTO a Arraylist de ListItems
    public static ArrayList<ListItem> map(RealmList<ListItemDTO> listItemsDTO) {
        ArrayList<ListItem> listItems = new ArrayList<>();
        for (ListItemDTO listItemDTO : listItemsDTO) {
            listItems.add(map(listItemDTO));
        }
        return listItems;
    }


    //mapeo de Movie a ListItemDTO
    public static ListItemDTO map(Movie movie) {
        ListItemDTO listItemDTO = new ListItemDTO();
        listItemDTO.setId(movie.getMovieDetails().getId());
        listItemDTO.setTitle(movie.getMovieDetails().getTitle());
        listItemDTO.setYear(movie.getMovieDetails().getYear());
        listItemDTO.setGenres(GenreController.getInstance().getGenresString(movie.getMovieDetails().getGenres(), ", "));
        Double rating = movie.getRatingsContainer().getTmdb();
        if (rating!=null) listItemDTO.setRating(rating.toString());
        else listItemDTO.setRating("");
        listItemDTO.setImageURL(ImageHelper.getPosterURL(movie.getMovieDetails().getPoster_path(),1));
        listItemDTO.setType(ListItem.TYPE_MOVIE);
        return listItemDTO;
    }

    //mapeo de Serie a ListItemDTO
    public static ListItemDTO map(Serie serie) {
        ListItemDTO listItemDTO = new ListItemDTO();
        listItemDTO.setId(serie.getSerieDetails().getId());
        listItemDTO.setTitle(serie.getSerieDetails().getName());
        listItemDTO.setYear(serie.getSerieDetails().getYear());
        listItemDTO.setGenres(GenreController.getInstance().getGenresString(serie.getSerieDetails().getGenres(), ", "));
        Double rating = serie.getRatingsContainer().getTmdb();
        if (rating!=null) listItemDTO.setRating(rating.toString());
        else listItemDTO.setRating("");
        listItemDTO.setImageURL(ImageHelper.getPosterURL(serie.getSerieDetails().getPosterPath(), 1));
        listItemDTO.setType(ListItem.TYPE_SERIE);
        return listItemDTO;
    }

}
