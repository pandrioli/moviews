package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.Helpers.POJO2DTOMapper;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.MovieDTO;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Pablo on 27/06/2017.
 */

public class MovieDAOLocal {
    private Realm realm;

    public MovieDAOLocal() {
        realm = Realm.getDefaultInstance();
    }

    public void saveMovie(MovieDTO movieDTO) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(movieDTO);
        realm.commitTransaction();
    }

    public MovieDTO obtainMovie(Integer id) {
        RealmResults<MovieDTO> result = realm.where(MovieDTO.class).equalTo("id", id).findAll();
        if (result.size()>0)
            return result.get(0);
        else return null;
    }

}
