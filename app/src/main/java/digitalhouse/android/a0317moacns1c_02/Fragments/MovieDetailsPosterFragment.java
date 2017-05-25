package digitalhouse.android.a0317moacns1c_02.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.R;


public class MovieDetailsPosterFragment extends Fragment {



    public MovieDetailsPosterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_details_title, container, false);

        ButterKnife.bind(this, view);


        return view;
    }


}
