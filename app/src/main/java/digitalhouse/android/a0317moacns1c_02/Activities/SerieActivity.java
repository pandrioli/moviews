package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.SerieController;
import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeasonPagerFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SerieDetailsFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;
import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Season;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.R;

public class SerieActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable, View.OnClickListener {
    public static final String SERIE_ID_KEY = "serieID";

    @BindView(R.id.fab) protected FloatingActionButton fab;
    @BindView(R.id.container) protected FrameLayout container;

    private Serie serie;
    private ArrayList<Season> seasons;
    private Season temporalVar;
    private SerieDetailsFragment serieDetailsFragment;
    private SeasonPagerFragment seasonPagerFragment;
    private Boolean isSeasonsLoaded;

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
        AnimationHelper.postponeTransition(this);
        setContentView(R.layout.activity_series);
        ButterKnife.bind(this);



        Bundle bundleReceived = getIntent().getExtras();
        final String id = bundleReceived.getString(SERIE_ID_KEY);
        SerieController.getInstance().getSerie(id, new ResultListener<Serie>() {
            @Override
            public void finish(Serie result) {
                if (result==null) {
                    Toast.makeText(SerieActivity.this, "Not available without connection", Toast.LENGTH_SHORT).show();
                    SerieActivity.this.startPostponedEnterTransition();
                    SerieActivity.this.finish();
                } else {
                    serie = result;
                    serieDetailsFragment = SerieDetailsFragment.newInstance(serie);
                    loadSerieDetails();
                }
            }
        });
    }


    private void loadSerieDetails(){
        isSeasonsLoaded=false;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, serieDetailsFragment)
                .commit();
    }

    private void loadSeasons() {
        isSeasonsLoaded=true;
        Integer numOfSeason = Integer.parseInt(serie.getNumberOfSeasons());
        seasonPagerFragment = SeasonPagerFragment.newInstance(numOfSeason, serie.getSerieIdIMDB() );
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, seasonPagerFragment)
                .commit();
    }


    @Override
    protected void onStop() {
        super.onStop();
        AnimationHelper.stopLoader(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            startActivityForResult(intent,1, transitionBundle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isSeasonsLoaded) loadSerieDetails();
        else super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.loadSeasons) loadSeasons();
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
