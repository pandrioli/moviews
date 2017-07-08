package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.Model.DTO.MovieDTO;
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
        MovieDTO result = realm.where(MovieDTO.class).equalTo("id", id).findFirst();
        return result;
    }

}
