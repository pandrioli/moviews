package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Fragments.SeasonPagerFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeasonsAndEpisodesFragment;

/**
 * Created by dh3 on 28/06/17.
 */

public class SeasonPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private Integer nos;
    private SeasonPagerFragment.SeasonSwitchable ss;


        public SeasonPagerAdapter(FragmentManager fm, Integer numberOfSeasons,
                                  SeasonPagerFragment.SeasonSwitchable ss){
            super(fm);
            nos = numberOfSeasons;
            this.ss = ss;
    }

    @Override
    public Fragment getItem(int position) {
        if(position > nos) throw new ArrayIndexOutOfBoundsException("Posición mayor a la cantidad de fragments del Adapter.");
        if(fragments.get(position) == null) {
            SeasonsAndEpisodesFragment seasonFrag = ss.onSeasonPageSwiped(position);
            fragments.set(position, seasonFrag);
        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return nos;
    }
}