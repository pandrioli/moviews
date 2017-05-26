package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Adapters.MovieRecyclerAdapter;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment implements View.OnClickListener {
    public final static String MOVIE_LIST_KEY = "movieList";
    RecyclerView recyclerViewMovies;
    ArrayList<MovieListItem> movieList;

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
        movieList = bundle.getParcelableArrayList(MOVIE_LIST_KEY);
        MovieRecyclerAdapter movieAdapter = new MovieRecyclerAdapter(view.getContext(), movieList, this);
        recyclerViewMovies = (RecyclerView) view.findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerViewMovies.setAdapter(movieAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        MovieSelectable activity = (MovieSelectable)getActivity();
        activity.movieSelected(movieList.get((Integer)v.getTag()));
    }

    public interface MovieSelectable {
        void movieSelected(MovieListItem movieListItem);
    }


}
