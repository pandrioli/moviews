package digitalhouse.android.a0317moacns1c_02.DAO;

import digitalhouse.android.a0317moacns1c_02.Model.Configuration.ImageConfig;
import io.realm.Realm;

/**
 * Created by Pablo on 04/07/2017.
 */

public class ConfigDAOLocal {
    private Realm realm;

    public ConfigDAOLocal() {
        this.realm = Realm.getDefaultInstance();
    }

    public ImageConfig obtainConfigData() {
        return realm.where(ImageConfig.class).equalTo("id", ImageConfig.ID).findFirst();
    }

    public void saveConfigData(final ImageConfig imageConfig) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(imageConfig);
            }
        });
    }
}
