package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Adapters.MovieAdapter;
import digitalhouse.android.a0317moacns1c_02.Entities.Movie;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    private List<MovieListItem> movieList;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        loadMovies();
        MovieAdapter movieAdapter = new MovieAdapter(movieList, view.getContext());
        ListView listViewMovies = (ListView)view.findViewById(R.id.listViewMovies);
        listViewMovies.setAdapter(movieAdapter);
        return view;
    }

    private void loadMovies(){
        Movie movie = new Movie();
        movie.setTitle("Guardians of the Galaxy");
        movie.setReleaseDate("2014-01-01");
        movie.setGenres("Adventure, Action, Comedy, Science Fiction");
        movie.setVoteAverage(7.9);
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

}
