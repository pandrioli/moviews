package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.DAO.ListDAOLocal;
import digitalhouse.android.a0317moacns1c_02.DAO.MovieDAOLocal;
import digitalhouse.android.a0317moacns1c_02.Mappers.DTOListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Mappers.DTOMovieMapper;
import digitalhouse.android.a0317moacns1c_02.Mappers.ListItemMapper;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.ListDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.ListItemDTO;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;

/**
 * Created by Pablo on 27/06/2017.
 */

public class ListController {
    private ListDAOLocal listDAOLocal;
    private MovieDAOLocal movieDAOLocal;

    private static ListController instance;
    public static ListController getInstance() {
        if (instance==null) instance = new ListController();
        return instance;
    }

    private ListController() {
        listDAOLocal = new ListDAOLocal();
        movieDAOLocal = new MovieDAOLocal();
    }

    public ArrayList<ListItem> getFavorites() {
        ListDTO listDTO = listDAOLocal.obtainAppList(ListDTO.FAVORITES);
        return ListItemMapper.map(listDTO.getList());
    }

    public ArrayList<ListItem> getUserList(String id) {
        ListDTO listDTO = listDAOLocal.obtainUserList(id);
        return ListItemMapper.map(listDTO.getList());
    }

    public void addMovieToFavorites(Movie movie) {
        listDAOLocal.saveItemToAppList(ListDTO.FAVORITES, DTOListItemMapper.map(movie));
        movieDAOLocal.saveMovie(DTOMovieMapper.map(movie));
    }

    public void addMovieToUserList(String listId, Movie movie) {
        listDAOLocal.saveItemToUserList(listId, DTOListItemMapper.map(movie));
        movieDAOLocal.saveMovie(DTOMovieMapper.map(movie));
    }

    public void addSerieToFavorites(Serie serie) {
        listDAOLocal.saveItemToAppList(ListDTO.FAVORITES, DTOListItemMapper.map(serie));
        //TODO: guardar serie en Realm
    }

    public void addSerieToUserList(String listId, Serie serie) {
        listDAOLocal.saveItemToUserList(listId, DTOListItemMapper.map(serie));
        //TODO: guardar serie en Realm
    }

    public void removeMovieFromFavorites(Integer itemId) {
        listDAOLocal.removeItemFromList(ListDTO.FAVORITES, itemId, ListItem.TYPE_MOVIE);
    }

    public void removeSerieFromFavorites(Integer itemId) {
        listDAOLocal.removeItemFromList(ListDTO.FAVORITES, itemId, ListItem.TYPE_SERIE);
    }

    public void removeMovieFromUserList(String listId, Integer itemId) {
        listDAOLocal.removeItemFromList(listId, itemId, ListItem.TYPE_MOVIE);
    }

    public void removeSerieFromUserList(String listId, Integer itemId) {
        listDAOLocal.removeItemFromList(listId, itemId, ListItem.TYPE_SERIE);
    }

    public Boolean isMovieFavorited(Integer id) {
        return isItemFavorited(id, ListItem.TYPE_MOVIE);
    }

    public Boolean isSerieFavorited(Integer id) {
        return isItemFavorited(id, ListItem.TYPE_SERIE);
    }

    public Boolean isMovieInUserList(Integer id) {
        return isItemInUserList(id, ListItem.TYPE_MOVIE);
    }

    public Boolean isSerieInUserList(Integer id) {
        return isItemInUserList(id, ListItem.TYPE_SERIE);
    }

    private Boolean isItemFavorited(Integer id, String type) {
        List<ListItem> favorites = getFavorites();
        for (ListItem item : favorites) {
            if (item.getId().equals(id) && item.getType().equals(type)) return true;
        }
        return false;
    }

    private Boolean isItemInUserList(Integer id, String type) {
        List<ListDTO> lists = listDAOLocal.obtainAllUserLists();
        for (ListDTO list : lists) {
            for (ListItemDTO item : list.getList()) {
                if (item.getId().equals(id) && item.getType().equals(type)) return true;
            }
        }
        return false;
    }

}
