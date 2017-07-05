package digitalhouse.android.a0317moacns1c_02.DAO;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Mappers.DTOGeneralMapper;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.GenreDTO;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Pablo on 05/07/2017.
 */

public class GenreDAOLocal {
    private Realm realm;
    public GenreDAOLocal() {
        realm = Realm.getDefaultInstance();
    }
    public List<Genre> obtainGenres() {
        List<Genre> genreList = new ArrayList<>();
        RealmResults<GenreDTO> results = realm.where(GenreDTO.class).findAll();
        for (GenreDTO genreDTO : results) {
            genreList.add(DTOGeneralMapper.map(genreDTO));
        }
        return genreList;
    }
    public void saveGenres(List<Genre> genreList) {
        realm.beginTransaction();
        for (Genre genre : genreList) {
            GenreDTO genreDTO = DTOGeneralMapper.map(genre);
            realm.copyToRealmOrUpdate(genreDTO);
        }
        realm.commitTransaction();
    }
}
