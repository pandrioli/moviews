package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;
import digitalhouse.android.a0317moacns1c_02.Helpers.Toaster;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Controller.ConfigController;
import io.realm.Realm;


public class MainActivity extends AppCompatActivity {
    private LottieAnimationView animationView;
    private boolean configDataLoaded;
    private boolean genresLoaded;
    private Integer loadCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationView = (LottieAnimationView) findViewById(R.id.animationViewMainActivity);

        //Inicializar Toaster (tostadora para hacer tostadas desde cualquier lugar sin necesidad de contexto)
        Toaster.init(this);

        //Inicializar Realm
        Realm.init(this);

        //Provisorio: borra toda la base de datos
        //Realm realm = Realm.getDefaultInstance();
        //realm.beginTransaction();
        //realm.deleteAll();
        //realm.commitTransaction();

        loadCounter = 0;

        //Carga los datos generales de la API
        ConfigController.getInstance().loadConfigData(this, new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean result) {
                loadCounter++;
                configDataLoaded = result;
                startTabsActivity();
            }
        });

        GenreController.getInstance().loadGenres(this, new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean result) {
                loadCounter++;
                genresLoaded = result;
                startTabsActivity();
            }
        });
    }

    private void startTabsActivity(){
        if (loadCounter<2) return;
        if (isLoadFinished()) {
            //Intent intent = new Intent(this, ItemTabsActivity.class);
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "No se pudo cargar la configuración", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Se necesita una conexión a internet para el primer arranque de la aplicación", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isLoadFinished(){
        return configDataLoaded && genresLoaded;
    }

}
