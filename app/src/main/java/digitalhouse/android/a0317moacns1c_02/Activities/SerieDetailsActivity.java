package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.RateController;
import digitalhouse.android.a0317moacns1c_02.Controller.SerieController;
import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.MediaListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.RateFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeriesDetailsInfoFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeriesDetailsTitleFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.R;


public class SerieDetailsActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {

    public static final String SERIE_ID_KEY = "serieID";

    @BindView(R.id.textViewSDSummary) protected TextView textViewSummary;
    @BindView(R.id.progressBarSD) ProgressBar progressBar;
    @BindView(R.id.cardViewSDSummary) CardView summary;

    private FragmentManager fragmentManager;
    private Serie serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_details);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        ActivityStackManager.getInstance().addActivity(this); // agrego la activity al stack manager

        Bundle bundleReceived = getIntent().getExtras();
        final String id = bundleReceived.getString(SERIE_ID_KEY);

        ObtainSerieTask task = new ObtainSerieTask(){
            @Override
            public void finish(Serie result) {
                serie = result;
                progressBar.setVisibility(View.GONE);
                startRatingsFragment();
                startTitleFargment();
                startInfoFragment();
                startMediaListFragment();
                startCastingFragment();
                summary.setVisibility(View.VISIBLE);
            }
        };
        task.execute(id);
    }

    private void startTitleFargment(){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        SeriesDetailsTitleFragment fragment = SeriesDetailsTitleFragment.newInstance(serie.getSerieDetails());
        fragmentTransaction.replace(R.id.frameLayoutSDTitle, fragment);
        fragmentTransaction.commit();
    }

    private void startMediaListFragment(){
        serie.getImagesContainer().getBackdrops().remove(0);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ArrayList<String> URLs = new ArrayList<>();
        URLs.addAll(serie.getVideosContainer().getYouTubeThumbnailURLs());
        URLs.addAll(serie.getImagesContainer().getURLsList());
        MediaListFragment fragment = MediaListFragment.newInstance(URLs);
        fragmentTransaction.replace(R.id.frameLayoutSDMedia, fragment);
        fragmentTransaction.commit();
    }

    private void startCastingFragment(){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        ArrayList<ImageListItem> imageList = serie.getCredits().getImageListItems();
        ImageListFragment imageListFragment = ImageListFragment.newInstance(imageList, "Cast");
        fragmentTransaction.replace(R.id.frameLayoutSDCasting, imageListFragment);
        fragmentTransaction.commit();
    }

    private void startInfoFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SeriesDetailsInfoFragment fragment = SeriesDetailsInfoFragment.newInstance(serie.getSerieDetails());
        fragmentTransaction.replace(R.id.frameLayoutSDInfo, fragment);
        fragmentTransaction.commit();
    }

    private void startRatingsFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RateFragment fragment = RateController.instanceRateFragment(serie);
        fragmentTransaction.replace(R.id.framelayoutSDRatings, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeLastActivity(); // saco la activity del stack manager
    }

    @Override
    public void onClick(Integer id, String title) {
        if (title.equals("Cast")) {
            Bundle bundle = new Bundle();
            bundle.putInt(PersonDetailsActivity.PERSON_ID_KEY, id);
            Intent intent = new Intent(this, PersonDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private class ObtainSerieTask extends AsyncTask<String, Void, Serie> implements ResultListener<Serie>{

        @Override
        protected Serie doInBackground(String... params) {
            String ID = params[0];
            Serie serie = SerieController.getInstance().getSerieSync(ID);
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
