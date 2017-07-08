package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.Model.DTO.SeasonDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.SerieDTO;
import io.realm.Realm;

/**
 * Created by Pablo on 08/07/2017.
 */

public class SerieDAOLocal {
    Realm realm;
    public SerieDAOLocal() {
        realm = Realm.getDefaultInstance();
    }
    public void saveSerie(SerieDTO serieDTO) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(serieDTO);
        realm.commitTransaction();
    }
    public SerieDTO obtainSerie(Integer id) {
        SerieDTO result = realm.where(SerieDTO.class).equalTo("id", id).findFirst();
        return result;
    }
    public void saveSeason(SeasonDTO seasonDTO) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(seasonDTO);
        realm.commitTransaction();
    }
    public SeasonDTO obtainSeason(Integer serieId, Integer seasonNumber) {
        SeasonDTO seasonDTO = realm.where(SeasonDTO.class)
                .equalTo("serieId", serieId)
                .equalTo("seasonNumber", seasonNumber).findFirst();
        return seasonDTO;
    }
}
