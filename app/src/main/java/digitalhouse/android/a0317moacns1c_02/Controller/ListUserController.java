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

// Controller para listas bookmarks, favoritos, y listas de usuario

public class ListUserController {
    private ListDAOLocal listDAOLocal;
    private MovieDAOLocal movieDAOLocal;

    private static ListUserController instance;
    public static ListUserController getInstance() {
        if (instance==null) instance = new ListUserController();
        return instance;
    }

    private ListUserController() {
        listDAOLocal = new ListDAOLocal();
        movieDAOLocal = new MovieDAOLocal();
    }


    //get Lists

    public ArrayList<ListItem> getFavorites() {
        ListDTO listDTO = listDAOLocal.obtainAppList(ListDTO.FAVORITES);
        return DTOListItemMapper.map(listDTO.getList());
    }

    public ArrayList<ListItem> getBookmarks() {
        ListDTO listDTO = listDAOLocal.obtainAppList(ListDTO.BOOKMARKS);
        return DTOListItemMapper.map(listDTO.getList());
    }

    public ArrayList<ListItem> getUserList(String id) {
        ListDTO listDTO = listDAOLocal.obtainUserList(id);
        return DTOListItemMapper.map(listDTO.getList());
    }


    // add Movies
    
    public void addMovieToFavorites(Movie movie) {
        listDAOLocal.saveItemToAppList(ListDTO.FAVORITES, DTOListItemMapper.map(movie));
        movieDAOLocal.saveMovie(DTOMovieMapper.map(movie));
    }
    
    public void addMovieToBookmarks(Movie movie) {
        listDAOLocal.saveItemToAppList(ListDTO.BOOKMARKS, DTOListItemMapper.map(movie));
        movieDAOLocal.saveMovie(DTOMovieMapper.map(movie));
    }

    public void addMovieToUserList(String listId, Movie movie) {
        listDAOLocal.saveItemToUserList(listId, DTOListItemMapper.map(movie));
        movieDAOLocal.saveMovie(DTOMovieMapper.map(movie));
    }

    
    // add Series
    
    public void addSerieToFavorites(Serie serie) {
        listDAOLocal.saveItemToAppList(ListDTO.FAVORITES, DTOListItemMapper.map(serie));
        //TODO: guardar serie en Realm
    }

    public void addSerieToBookmarks(Serie serie) {
        listDAOLocal.saveItemToAppList(ListDTO.BOOKMARKS, DTOListItemMapper.map(serie));
        //TODO: guardar serie en Realm
    }

    public void addSerieToUserList(String listId, Serie serie) {
        listDAOLocal.saveItemToUserList(listId, DTOListItemMapper.map(serie));
        //TODO: guardar serie en Realm
    }

    
    // remove Movies
    
    public void removeMovieFromFavorites(Integer itemId) {
        listDAOLocal.removeItemFromList(ListDTO.FAVORITES, itemId, ListItem.TYPE_MOVIE);
    }
    public void removeMovieFromBookmarks(Integer itemId) {
        listDAOLocal.removeItemFromList(ListDTO.BOOKMARKS, itemId, ListItem.TYPE_MOVIE);
    }

    public void removeMovieFromUserList(String listId, Integer itemId) {
        listDAOLocal.removeItemFromList(listId, itemId, ListItem.TYPE_MOVIE);
    }
    
    
    // remove Series
    
    public void removeSerieFromFavorites(Integer itemId) {
        listDAOLocal.removeItemFromList(ListDTO.FAVORITES, itemId, ListItem.TYPE_SERIE);
    }

    public void removeSerieFromBookmarks(Integer itemId) {
        listDAOLocal.removeItemFromList(ListDTO.BOOKMARKS, itemId, ListItem.TYPE_SERIE);
    }

    public void removeSerieFromUserList(String listId, Integer itemId) {
        listDAOLocal.removeItemFromList(listId, itemId, ListItem.TYPE_SERIE);
    }

    
    //check if Movie is in lists
    
    public Boolean isMovieInFavorites(Integer id) {
        return isItemInList(getFavorites(), id, ListItem.TYPE_MOVIE);
    }

    public Boolean isMovieInBookmarks(Integer id) {
        return isItemInList(getBookmarks(), id, ListItem.TYPE_MOVIE);
    }

    public Boolean isMovieInUserList(Integer id) {
        return isItemInUserList(id, ListItem.TYPE_MOVIE);
    }


    //check if Serie is in lists

    public Boolean isSerieInFavorites(Integer id) {
        return isItemInList(getFavorites(), id, ListItem.TYPE_SERIE);
    }

    public Boolean isSerieInBookmarks(Integer id) {
        return isItemInList(getBookmarks(), id, ListItem.TYPE_SERIE);
    }

    public Boolean isSerieInUserList(Integer id) {
        return isItemInUserList(id, ListItem.TYPE_SERIE);
    }


    //private methods

    private Boolean isItemInList(List<ListItem> list, Integer id, String type) {
        for (ListItem item : list) {
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
