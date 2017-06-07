package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Adapters.ListItemRecyclerAdapter;
import digitalhouse.android.a0317moacns1c_02.Model.General.ListItem;
import digitalhouse.android.a0317moacns1c_02.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment implements View.OnClickListener {
    public final static String MOVIE_LIST_KEY = "movieList";
    RecyclerView recyclerViewMovies;
    ArrayList<ListItem> movieList;

    public ItemListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        final ItemClickeable myActivity = (ItemClickeable)getActivity();
        Bundle bundle = getArguments();
        movieList = bundle.getParcelableArrayList(MOVIE_LIST_KEY);
        ListItemRecyclerAdapter movieAdapter = new ListItemRecyclerAdapter(view.getContext(), movieList, this);
        recyclerViewMovies = (RecyclerView) view.findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerViewMovies.setAdapter(movieAdapter);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerViewMovies.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerViewMovies.addItemDecoration(mDividerItemDecoration);


        return view;
    }

    @Override
    public void onClick(View v) {
        ItemClickeable activity = (ItemClickeable)getActivity();
        activity.onClick(movieList.get((Integer)v.getTag()));
    }

    public interface ItemClickeable {
        void onClick(ListItem listItem);
    }


}
