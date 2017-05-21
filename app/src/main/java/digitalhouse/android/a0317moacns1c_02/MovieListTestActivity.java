package digitalhouse.android.a0317moacns1c_02;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import digitalhouse.android.a0317moacns1c_02.Fragments.MovieListFragment;
import digitalhouse.android.a0317moacns1c_02.R;

public class MovieListTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_test);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.MovieListContainer, new MovieListFragment());
        ft.commit();
    }
}
