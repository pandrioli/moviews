package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Fragments.SeasonPagerFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeasonsAndEpisodesFragment;

/**
 * Created by dh3 on 28/06/17.
 */

public class SeasonPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private String serieId;


        public SeasonPagerAdapter(FragmentManager fm, Integer numberOfSeasons,
                                  String serieId){
            super(fm);
            fragments = Arrays.asList(new Fragment[numberOfSeasons]);
            this.serieId = serieId;
    }

    @Override
    public Fragment getItem(int position) {
        if(position > fragments.size()) throw new ArrayIndexOutOfBoundsException("Posición mayor a la cantidad de fragments del Adapter.");
        if(fragments.get(position) == null) {
            //TODO: solucionar lo del las season 0 (especiales)
            // se le sumo 1 a la posicion para evitar el error y ademas obtener la ultima season (antes no llegaba)
            SeasonsAndEpisodesFragment seasonFrag = SeasonsAndEpisodesFragment.newInstance(serieId, position+1);
            fragments.set(position, seasonFrag);
        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}