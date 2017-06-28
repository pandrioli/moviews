package digitalhouse.android.a0317moacns1c_02.Controller;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.DAO.ListDAOLocal;
import digitalhouse.android.a0317moacns1c_02.Mappers.DTOListItemMapper;
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
    private static ListController instance;
    public static ListController getInstance() {
        if (instance==null) instance = new ListController();
        return instance;
    }

    private ListController() {
        listDAOLocal = new ListDAOLocal();
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
    }

    public void addMovieToUserList(String listId, Movie movie) {
        listDAOLocal.saveItemToUserList(listId, DTOListItemMapper.map(movie));
    }

    public void addSerieToFavorites(Serie serie) {
        ListDTO listDTO = listDAOLocal.obtainAppList(ListDTO.FAVORITES);
        ListItemDTO listItemDTO = DTOListItemMapper.map(serie);
        listDTO.getList().add(listItemDTO);
    }

    public void addSerieToUserList(String listId, Serie serie) {
        ListDTO listDTO = listDAOLocal.obtainUserList(listId);
        ListItemDTO listItemDTO = DTOListItemMapper.map(serie);
        listDTO.getList().add(listItemDTO);
    }

}
