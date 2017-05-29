package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Entities.PersonData;
import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonDetailsInfoFragment extends Fragment {

    @BindView(R.id.imageViewPDIprofile) ImageView imageViewProfile;
    @BindView(R.id.textViewPDIName) TextView textViewName;
    @BindView(R.id.textViewPDIBirthDay) TextView textViewBirthDay;
    @BindView(R.id.textViewPDIBirthPlace) TextView textViewBirthPlace;

    private Unbinder unbinder;

    public PersonDetailsInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_details_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpViews((PersonData) getArguments().getSerializable(PersonData.tag));
        return view;
    }

    private void setUpViews(PersonData personData) {
        textViewName.setText(personData.getName());
        if (personData.getBirthday() != null) {
            String birthdayAndAge = DateHelper.format(personData.getBirthday(), DateHelper.FORMAT_ARG);
            birthdayAndAge += " (Age: " + personData.getAge().toString() + ")";
            textViewBirthDay.setText(birthdayAndAge);
        } else {
            textViewBirthDay.setText("no info");
        }
        if (personData.getPlaceOfBirth() != null) {
            textViewBirthPlace.setText(personData.getPlaceOfBirth());
        } else {
            textViewBirthPlace.setText("no info");
        }
        Picasso.with(getContext()).load(personData.getProfileURL(3)).fit().centerCrop().into(imageViewProfile);
    }

    @Override public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }
}
