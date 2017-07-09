package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Fragments.ItemListFragment;

/**
 * Created by Pablo on 23/05/2017.
 */

public class TabItemListPagerAdapter extends FragmentStatePagerAdapter {
    private List<ItemListFragment> fragmentList;
    private List<String> titles;

    public TabItemListPagerAdapter(FragmentManager fm, List<ItemListFragment> fragmentList, List<String> titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    public List<ItemListFragment> getFragmentList() {
        return fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
