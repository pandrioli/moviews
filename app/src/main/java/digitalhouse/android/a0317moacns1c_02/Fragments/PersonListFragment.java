package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Adapters.PersonRecyclerAdapter;
import digitalhouse.android.a0317moacns1c_02.Entities.PersonListItem;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonListFragment extends Fragment {
    public final static String PERSON_LIST_KEY = "personList";

    private List<PersonListItem> personList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public PersonListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);
        ButterKnife.bind(view);
        Bundle bundle = getArguments();
        personList = bundle.getParcelableArrayList(PERSON_LIST_KEY);
        adapter = new PersonRecyclerAdapter(getContext(), personList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewPersons);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
