package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.ConfigurationService;
import digitalhouse.android.a0317moacns1c_02.Services.MovieService;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonToLoginActivity) protected Button button;
    @BindView(R.id.buttonToMovieListActivity) protected Button buttonMovieList;
    @BindView(R.id.buttonToTabsTestActivity) protected Button buttonSwipeTest;
    @BindView(R.id.textViewStatusDatosGenerales) protected TextView generalAPIDataLoadStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Carga los datos generales de la API
        //TODO: debería bloquearse la ejecución hasta obtener los datos
        ConfigurationService.getInstance().obtainConfigData(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                String status = generalAPIDataLoadStatus.getText().toString();
                status += "\r\n" + result.toString();
                generalAPIDataLoadStatus.setText(status);
            }
        });
        //Carga la lista de generos de peliculas
        //TODO: debería bloquearse la ejecución hasta obtener los datos
        MovieService.getInstance().loadGenres(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                String status = generalAPIDataLoadStatus.getText().toString();
                status += "\r\n" + result.toString();
                generalAPIDataLoadStatus.setText(status);
            }
        });

        ButterKnife.bind(this);
    }


    @OnClick(R.id.buttonToLoginActivity)
    public void onClick(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonToMovieListActivity)
    public void onClickMovieList(View v) {
        Intent intent = new Intent(this, MovieListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonToTabsTestActivity)
    public void onClickTabsTest(View v) {
        Intent intent = new Intent(this, TabsTestActivity.class);
        startActivity(intent);
    }
}
