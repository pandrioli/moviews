package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import digitalhouse.android.a0317moacns1c_02.Activities.SearchActivity;

/**
 * Created by Gregorio Martin on 27/5/2017.
 */

public class SearchPagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;
    private Fragment movieFragment;
    private Fragment seriesFragment;
    private Fragment actorsFragment;

    public SearchPagerAdapter(FragmentManager fragmentManager, Integer NumOfTabs){
        super(fragmentManager);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {   //TODO: colocar los fragments correctos
            if(movieFragment == null)
            fragment = new Fragment();
            else fragment =  movieFragment;
        }
        else if (position == 1)
        {   //series list fragment
            if(seriesFragment == null)
            fragment = new Fragment();
            else fragment = seriesFragment;
        }
        else if (position == 2)
        {   //actor list fragments
            if(actorsFragment == null)
            fragment = new Fragment();
            else fragment = actorsFragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public SearchActivity.ListReloadable getMovieFragment(){
        if(this.movieFragment == null) movieFragment = new Fragment(); //TODO: Fragment por el tipo de fragment correspondiente
        return (SearchActivity.ListReloadable)this.movieFragment;
    }

    public SearchActivity.ListReloadable getSeriesFragment(){
        if(this.seriesFragment == null) seriesFragment = new Fragment();//TODO: Fragment por el tipo de fragment correspondiente
        return (SearchActivity.ListReloadable)this.seriesFragment;
    }

    public SearchActivity.ListReloadable getActorsFragment(){
        if(this.seriesFragment == null) actorsFragment = new Fragment();//TODO: Fragment por el tipo de fragment correspondiente
        return (SearchActivity.ListReloadable)this.actorsFragment;
    }
}
