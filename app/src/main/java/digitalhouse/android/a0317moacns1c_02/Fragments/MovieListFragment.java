package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    public final static String MOVIE_LIST_KEY = "movieList";

    public MovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        final MovieSelectable myActivity = (MovieSelectable)getActivity();
        Bundle bundle = getArguments();
        ArrayList<MovieListItem> movieList = bundle.getParcelableArrayList(MOVIE_LIST_KEY);
        MovieAdapter movieAdapter = new MovieAdapter(movieList, view.getContext());
        ListView listViewMovies = (ListView)view.findViewById(R.id.listViewMovies);
        listViewMovies.setAdapter(movieAdapter);
        listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer movieId = ((MovieListItem)parent.getItemAtPosition(position)).getId();
                myActivity.movieSelected(movieId);
            }
        });
        return view;
    }

    public interface MovieSelectable {
        void movieSelected(Integer id);
    }
}
