package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieCredits;
import digitalhouse.android.a0317moacns1c_02.Entities.CrewListItem;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsProductionTeamFragment extends Fragment {

    public static final String CREW_LIST_KEY = "crewList";

    @BindView(R.id.textViewMDDirecting) TextView textViewDirecting;
    @BindView(R.id.textViewMDWriting) TextView textViewWriting;
    private Unbinder unbinder;


    public MovieDetailsProductionTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details_production_team, container, false);
        unbinder = ButterKnife.bind(this, view);
        ArrayList<CrewListItem> crewList = getArguments().getParcelableArrayList(CREW_LIST_KEY);
        String direction = "";
        String writing = "";
        for (CrewListItem person : crewList) {
            switch (person.getDepartment()) {
                case "Directing":
                    direction += person.getName() + " (" + person.getJob() + ") \r\n";
                    break;
                case "Writing":
                    writing += person.getName() + " (" + person.getJob() + ") \r\n";
                    break;
            }
        }
        textViewDirecting.setText(direction);
        textViewWriting.setText(writing);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
