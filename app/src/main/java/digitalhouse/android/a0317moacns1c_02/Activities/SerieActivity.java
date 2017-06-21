package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.SerieController;
import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SerieDetailsFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.R;

public class SerieActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {
    public static final String SERIE_ID_KEY = "serieID";

    @BindView(R.id.fab) protected FloatingActionButton fab;
    @BindView(R.id.tabs) protected TabLayout tabLayout;
    @BindView(R.id.container) protected ViewPager mViewPager;

    private Serie serie;
    private SerieDetailsFragment serieDetailsFragment;
    private SerieDetailsFragment serieDetailsFragment2;
    private SerieDetailsFragment serieDetailsFragment3;
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
        setContentView(R.layout.activity_serie);
        ButterKnife.bind(this);
        ActivityStackManager.getInstance().addActivity(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle bundleReceived = getIntent().getExtras();
        final String id;
        if(bundleReceived != null)
            id = bundleReceived.getString(SERIE_ID_KEY);
        else id = "3232";
        SerieActivity.ObtainSerieTask task = new SerieActivity.ObtainSerieTask(){
            @Override
            public void finish(Serie result) {
                serie = result;
                LottieAnimationView animation = (LottieAnimationView) findViewById(R.id.animationViewAS);
                animation.cancelAnimation();
                animation.setVisibility(View.GONE);
                serieDetailsFragment = SerieDetailsFragment.newInstance(serie);
                serieDetailsFragment2 = SerieDetailsFragment.newInstance(serie);
                serieDetailsFragment3 = SerieDetailsFragment.newInstance(serie);
                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                mViewPager.setAdapter(mSectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
            }
        };
        task.execute(id);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeLastActivity(); // saco la activity del stack manager
    }

    @Override
    public void onClick(ImageListItem imageListItem, String title) {
        if (title.equals("Cast")) {
            Bundle bundle = new Bundle();
            bundle.putInt(PersonDetailsActivity.PERSON_ID_KEY, imageListItem.getId());
            Intent intent = new Intent(this, PersonDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
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
                    return serieDetailsFragment2;
                case 2:
                    return serieDetailsFragment3;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DETAILS";
                case 1:
                    return "SEASONS";
                case 2:
                    return "EPISODES";
            }
            return null;
        }
    }

    private class ObtainSerieTask extends AsyncTask<String, Void, Serie> implements ResultListener<Serie> {

        @Override
        protected Serie doInBackground(String... params) {
            String ID = params[0];
            serie = SerieController.getInstance().getSerieSync(ID);
            return serie;
        }

        @Override
        protected void onPostExecute(Serie serie) {
            super.onPostExecute(serie);
            finish(serie);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void finish(Serie result) {
        }
    }
}
