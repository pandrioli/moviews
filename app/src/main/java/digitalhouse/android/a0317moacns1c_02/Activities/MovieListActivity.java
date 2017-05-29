package digitalhouse.android.a0317moacns1c_02.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieListFragment;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.MovieService;

public class MovieListActivity extends AppCompatActivity implements MovieListFragment.MovieClickeable {

    // tiene que ser del tipo ArrayList, no List, ya que lo requiere el putParcelableArrayList;
    private ArrayList<MovieListItem> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_test);
        //loadMovies();
        MovieService.getInstance().getPopularMovies(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                movieList = (ArrayList<MovieListItem>) result;
                loadFragment();
            }
        });
    }

    private void loadFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(MovieListFragment.MOVIE_LIST_KEY, movieList);
        MovieListFragment movieListFragment = new MovieListFragment();
        movieListFragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.MovieListContainer, movieListFragment);
        ft.commit();
    }


    @Override
    public void onClick(MovieListItem movieListItem) {
        Toast.makeText(this, "Movie selected: " + movieListItem.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
