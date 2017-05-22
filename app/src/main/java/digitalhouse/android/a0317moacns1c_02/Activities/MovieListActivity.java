package digitalhouse.android.a0317moacns1c_02.Activities;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.GeneralAPIData;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.GeneralAPIData.Config;
import digitalhouse.android.a0317moacns1c_02.Entities.Movie;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieListFragment;
import digitalhouse.android.a0317moacns1c_02.R;

public class MovieListActivity extends AppCompatActivity implements MovieListFragment.MovieSelectable {

    // tiene que ser del tipo ArrayList, no List, ya que lo requiere el putParcelableArrayList;
    private ArrayList<MovieListItem> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_test);
        loadMovies();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(MovieListFragment.MOVIE_LIST_KEY, movieList);
        MovieListFragment movieListFragment = new MovieListFragment();
        movieListFragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.MovieListContainer, movieListFragment);
        ft.commit();
    }

    private void loadMovies(){
        Movie movie = new Movie();
        movie.setId(123324);
        movie.setTitle("Guardians of the Galaxy");
        movie.setReleaseDate("2014-01-01");
        movie.setGenres("Adventure, Action, Comedy, Science Fiction");
        movie.setVoteAverage(7.9);
        movie.setPosterPath("http://1.media.dorkly.cvcdn.com/26/95/18b149286ca6f2920e017bd5d2ffcbf5.jpg");
        movieList = new ArrayList<>();
        movieList.add(new MovieListItem(movie));
        movieList.add(new MovieListItem(movie));
        movieList.add(new MovieListItem(movie));
        movieList.add(new MovieListItem(movie));
        movieList.add(new MovieListItem(movie));
        movieList.add(new MovieListItem(movie));
        movieList.add(new MovieListItem(movie));
        movieList.add(new MovieListItem(movie));
    }

    @Override
    public void movieSelected(MovieListItem movieListItem) {
        Toast.makeText(this, "Movie selected: " + movieListItem.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
