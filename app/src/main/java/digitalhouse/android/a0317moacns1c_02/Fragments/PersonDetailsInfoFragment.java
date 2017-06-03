package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonDetails;
import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
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
        setUpViews((PersonDetails) getArguments().getParcelable(PersonDetails.tag));
        return view;
    }

    private void setUpViews(PersonDetails personDetails) {
        textViewName.setText(personDetails.getName());
        Date birthday = personDetails.getBirthdayDate();
        Date deathday = personDetails.getDeathdayDate();
        if (birthday != null) {
            String birthdayAndAge = DateHelper.format(birthday, DateHelper.FORMAT_ARG);
            if (deathday == null)
                birthdayAndAge += " (Age: " + DateHelper.age(birthday) + ")";
                else birthdayAndAge += " (Death: " + DateHelper.format(deathday, DateHelper.FORMAT_ARG) + ")";
            textViewBirthDay.setText(birthdayAndAge);
        } else {
            textViewBirthDay.setText("no info");
        }
        if (personDetails.getPlace_of_birth() != null) {
            textViewBirthPlace.setText(personDetails.getPlace_of_birth());
        } else {
            textViewBirthPlace.setText("no info");
        }
        String url = ImageHelper.getProfileURL(personDetails.getProfile_path(), 2);
        Picasso.with(getContext()).load(url).fit().centerCrop().into(imageViewProfile);
    }

    @Override public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }
}
