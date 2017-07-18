package digitalhouse.android.a0317moacns1c_02.Application;

import android.app.Application;
import android.content.Context;

import digitalhouse.android.a0317moacns1c_02.Controller.ConfigController;
import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Controller.ListTmdbController;
import digitalhouse.android.a0317moacns1c_02.Helpers.Toaster;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by dh3 on 15/07/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        Toaster.init(getApplicationContext());
        ListTmdbController.init(getApplicationContext());
        ConfigController.init(getApplicationContext());
        GenreController.init(getApplicationContext());
    }
}