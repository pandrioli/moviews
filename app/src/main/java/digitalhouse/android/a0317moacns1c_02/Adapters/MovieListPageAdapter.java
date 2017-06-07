package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import digitalhouse.android.a0317moacns1c_02.Fragments.ItemListFragment;

/**
 * Created by Pablo on 23/05/2017.
 */

public class MovieListPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private Bundle[] bundleList;

    public MovieListPageAdapter(FragmentManager fm, Bundle[] bundleList, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.bundleList = bundleList;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = bundleList[position];
        ItemListFragment itemListFragment = new ItemListFragment();
        itemListFragment.setArguments(bundle);
        return itemListFragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
