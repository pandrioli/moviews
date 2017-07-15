package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Fragments.ImageViewerFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;

/**
 * Created by Pablo on 25/06/2017.
 */

public class ImageViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public ImageViewPagerAdapter(FragmentManager fm, List<String> imagesURL) {
        super(fm);
        fragments = new ArrayList<>();
        for (String url : imagesURL) {
            fragments.add(ImageViewerFragment.newInstance(url));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }



    @Override
    public int getCount() {
        return fragments.size();
    }
}
