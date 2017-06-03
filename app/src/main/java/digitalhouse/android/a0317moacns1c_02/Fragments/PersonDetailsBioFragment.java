package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.DAO.Person.PersonDetails;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonDetailsBioFragment extends Fragment {

    @BindView(R.id.textViewPDIBio) TextView textViewBio;

    private Unbinder unbinder;

    public PersonDetailsBioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_person_details_bio, container, false);
        unbinder = ButterKnife.bind(this, view);
        PersonDetails personDetails = (PersonDetails) getArguments().getParcelable(PersonDetails.tag);
        textViewBio.setText(personDetails.getBiography());
        return view;
    }

    @Override public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }
}
