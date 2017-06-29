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

    private Integer numberOfSeasons;


    public SeasonPagerFragment() {
        // Required empty public constructor
    }


    public static SeasonPagerFragment newInstance(Integer numberOfSeasons) {
        SeasonPagerFragment fragment = new SeasonPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER_OF_SEASONS, numberOfSeasons);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numberOfSeasons = getArguments().getInt(ARG_NUMBER_OF_SEASONS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_season_pager, container, false);
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewPagerSeasons);
        SeasonPagerAdapter seasonPagerAdapter = new SeasonPagerAdapter(getActivity().getSupportFragmentManager(),
                numberOfSeasons, new SeasonSwitchable() {
            @Override
            public SeasonsAndEpisodesFragment onSeasonPageSwiped(Integer seasonNumber) {
                return SeasonsAndEpisodesFragment.newInstance(new Season());
            }
        });
        return view;
    }

    public interface SeasonSwitchable{
        SeasonsAndEpisodesFragment onSeasonPageSwiped(Integer seasonNumber);
    }

}
