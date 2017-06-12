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
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Controller.ConfigController;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonToLoginActivity) protected Button button;
    @BindView(R.id.buttonToTabsTestActivity) protected Button buttonSwipeTest;
    @BindView(R.id.textViewStatusDatosGenerales) protected TextView generalAPIDataLoadStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Carga los datos generales de la API
        //TODO: debería bloquearse la ejecución hasta obtener los datos
        ConfigController.getInstance().loadConfigData(new ResultListener<String>() {
            @Override
            public void finish(String result) {
                String status = generalAPIDataLoadStatus.getText().toString();
                status += "\r\n" + result.toString();
                generalAPIDataLoadStatus.setText(status);
            }
        });
        //Carga la lista de generos de peliculas
        //TODO: debería bloquearse la ejecución hasta obtener los datos
        GenreController.getInstance().loadMovieGenres(new ResultListener<String>() {
            @Override
            public void finish(String result) {
                String status = generalAPIDataLoadStatus.getText().toString();
                status += "\r\n" + result.toString();
                generalAPIDataLoadStatus.setText(status);
            }
        });
        //Carga la lista de generos de peliculas
        //TODO: debería bloquearse la ejecución hasta obtener los datos
        GenreController.getInstance().loadSerieGenres(new ResultListener<String>() {
            @Override
            public void finish(String result) {
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

    @OnClick(R.id.buttonToTabsTestActivity)
    public void onClickTabsTest(View v) {
        Intent intent = new Intent(this, ItemTabsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonToMovieDetailsActivity)
    public void onClickMovieDetails(View v){
        Bundle bundle = new Bundle();
        bundle.putInt("movieId", 400928);
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.buttonToPersonDetailsActivity)
    public void onClickPersonDetails(View v){
        Bundle bundle = new Bundle();
        bundle.putInt(PersonDetailsActivity.PERSON_ID_KEY, 287);
        Intent intent = new Intent(this, PersonDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
