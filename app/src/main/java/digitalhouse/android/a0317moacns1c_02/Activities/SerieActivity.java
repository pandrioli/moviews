package digitalhouse.android.a0317moacns1c_02.Activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.SerieController;
import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeasonPagerFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeasonsAndEpisodesFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SerieDetailsFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;
import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.Toaster;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Season;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SeasonDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.R;

public class SerieActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {
    public static final String SERIE_ID_KEY = "serieID";

    @BindView(R.id.fab) protected FloatingActionButton fab;
    @BindView(R.id.tabs) protected TabLayout tabLayout;
    @BindView(R.id.container) protected ViewPager mViewPager;

    private Serie serie;
    private ArrayList<Season> seasons;
    private Season temporalVar;
    private SerieDetailsFragment serieDetailsFragment;
    private SeasonPagerFragment seasonPagerFragment;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        ButterKnife.bind(this);
        ActivityStackManager.getInstance().addActivity(this);
        AnimationHelper.postponeTransition(this);

        Bundle bundleReceived = getIntent().getExtras();
        final String id = bundleReceived.getString(SERIE_ID_KEY);
        SerieController.getInstance().getSerie(id, new ResultListener<Serie>() {
            @Override
            public void finish(Serie result) {
                if (result==null) {
                    Toast.makeText(SerieActivity.this, "Not available without connection", Toast.LENGTH_SHORT).show();
                    SerieActivity.this.finish();
                } else {
                    serie = result;
                    setUpFragmentsAndViewPager();
                }
            }
        });
    }





    private void setUpFragmentsAndViewPager(){
        serieDetailsFragment = SerieDetailsFragment.newInstance(serie);
        Integer numOfSeason = Integer.parseInt(serie.getNumberOfSeasons());
        seasonPagerFragment = SeasonPagerFragment.newInstance(numOfSeason, serie.getSerieIdIMDB() );
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeLastActivity(); // saco la activity del stack manager
    }

    @Override
    protected void onStart() {
        super.onStart();
        AnimationHelper.stopLoader(this);
    }

    @Override
    public void onClick(ImageListItem imageListItem, String title, Integer index, ImageView imageView) {
        if (title.equals("Casting")) {
            AnimationHelper.startLoader(this);
            Bundle transitionBundle = AnimationHelper.getTransitionBundle(this, imageView, "profile");
            Bundle bundle = new Bundle();
            bundle.putInt(PersonDetailsActivity.PERSON_ID_KEY, imageListItem.getId());
            Intent intent = new Intent(this, PersonDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent, transitionBundle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                default:
                case 0:
                    return serieDetailsFragment;
                case 1:
                    return seasonPagerFragment;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DETAILS";
                case 1:
                    return "SEASONS & EPISODES";
            }
            return null;
        }
    }
}
