package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadCounter = 0;

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()==null) loginFirebaseAnonymous();
        else loadCounter++;

        animationView = (LottieAnimationView) findViewById(R.id.animationViewMainActivity);

        startOnBoarding();
        if (isFinishing()) return;

        //Provisorio: borra toda la base de datos
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        //realm.deleteAll();
        realm.commitTransaction();


        //Carga los datos generales de la API
        ConfigController.getInstance().loadConfigData(new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean result) {
                configDataLoaded = result;
                startTabsActivity();
            }
        });

        GenreController.getInstance().loadGenres(new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean result) {
                genresLoaded = result;
                startTabsActivity();
            }
        });
    }

    private void startOnBoarding() {
        // Get the shared preferences
        SharedPreferences preferences =  getSharedPreferences("my_preferences", MODE_PRIVATE);

        // Check if onboarding_complete is false
        if(!preferences.getBoolean("onboarding_complete",false)) {
            // Start the onboarding Activity
            Intent onboarding = new Intent(this, OnBoardingActivity.class);
            startActivity(onboarding);

            // Close the main Activity
            finish();
        }
    }

    private void startTabsActivity(){
        loadCounter++;
        if (loadCounter<3) return;
        if (isLoadFinished()) {
            //Intent intent = new Intent(this, ItemTabsActivity.class);
            Intent intent = new Intent(this, ItemTabsActivity.class);
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

    private void loginFirebaseAnonymous() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        startTabsActivity();
                    }
                });
    }

}
