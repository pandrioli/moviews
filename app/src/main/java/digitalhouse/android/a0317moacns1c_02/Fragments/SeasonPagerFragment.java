package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Adapters.SeasonPagerAdapter;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Season;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SeasonDetails;
import digitalhouse.android.a0317moacns1c_02.R;

public class SeasonPagerFragment extends Fragment {
    private static final String ARG_NUMBER_OF_SEASONS = "numberOfSeasons";
    private static final String ARG_SEASON_ID = "seasonId";

    private Integer numberOfSeasons;
    private String serieId;


    public SeasonPagerFragment() {
        // Required empty public constructor
    }


    public static SeasonPagerFragment newInstance(Integer numberOfSeasons, String serieId) {
        SeasonPagerFragment fragment = new SeasonPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER_OF_SEASONS, numberOfSeasons);
        args.putString(ARG_SEASON_ID, serieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numberOfSeasons = getArguments().getInt(ARG_NUMBER_OF_SEASONS);
            serieId = getArguments().getString(ARG_SEASON_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_season_pager, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagerSeasons);
        SeasonPagerAdapter seasonPagerAdapter = new SeasonPagerAdapter(getActivity().getSupportFragmentManager(), numberOfSeasons,  serieId);
        viewPager.setAdapter(seasonPagerAdapter);
        return view;
    }

}
