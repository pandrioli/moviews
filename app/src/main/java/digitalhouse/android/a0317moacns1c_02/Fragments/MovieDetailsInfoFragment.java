package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsInfoFragment extends Fragment {

    @BindView(R.id.textViewMovieDetailsDescription) TextView textViewMovieDetailsDescription;
    @BindView(R.id.textViewMovieDetailsDuration) TextView textViewMovieDetailsDuration;
    @BindView(R.id.textViewMovieDetailsGeneros) TextView getTextViewMovieDetailsDescription;
    @BindView(R.id.textViewMovieDetailsTitle) TextView textViewMovieDetailsTitle;
    @BindView(R.id.textViewMovieDetailsYear) TextView textViewMovieDetailsYear;
    @BindView(R.id.textViewMovieDetailsMovieRatings) TextView textViewMovieDetailsMovieRatings;


    public MovieDetailsInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ButterKnife.bind(getActivity());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details_info, container, false);
    }

}
