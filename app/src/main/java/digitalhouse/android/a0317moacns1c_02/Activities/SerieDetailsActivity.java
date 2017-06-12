package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.SerieController;
import digitalhouse.android.a0317moacns1c_02.Fragments.ActionsFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.MediaListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.RateProgressBarFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeriesDetailsInfoFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeriesDetailsTitleFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.SeriesDetailsSummaryFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImagesContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.R;

public class SerieDetailsActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {

    public static final String SERIE_ID_KEY = "serieID";

    @BindView(R.id.linearLayoutSDRatings) LinearLayout linearLayoutRatings;
    @BindView(R.id.imageViewPoster) ImageView imageViewPoster;

    private FragmentManager fragmentManager;
    private Serie serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_details);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        Bundle bundleReceived = getIntent().getExtras();
        String id = bundleReceived.getString(SERIE_ID_KEY);

        SerieController.getInstance().getDetails(id.toString(), new ResultListener<Serie>() {
            @Override
            public void finish(Serie result) {
                serie = result;
                startTitleFargment();
                startSummaryFragment(serie.getOverview());
                startInfoFragment();
                setUpPoster();
                setUpRatings();
            }
        });

        SerieController.getInstance().getImages(id, new ResultListener<ImagesContainer>() {
            @Override
            public void finish(ImagesContainer imagesContainer) {
                startMediaListFragment(imagesContainer);
            }
        });

        SerieController.getInstance().getCredits(id, new ResultListener<Credits>() {
            @Override
            public void finish(Credits credits) {
                ArrayList<ImageListItem> imageList = credits.getImageListItems();
                startCastingFragment(imageList, "Cast");
            }
        });

        startActionsFragment();
    }

    private void setUpPoster(){
        String url = ImageHelper.getPosterURL(serie.getPosterPath(), 2);
        Picasso.with(this).load(url).fit().centerCrop().into(imageViewPoster);
    }

    private void startTitleFargment(){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        SeriesDetailsTitleFragment fragment = SeriesDetailsTitleFragment.newInstance(serie);
        fragmentTransaction.replace(R.id.frameLayoutSDTitle, fragment);
        fragmentTransaction.commit();
    }

    private void startMediaListFragment(ImagesContainer imagesContainer){
        imagesContainer.getBackdrops().remove(0);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MediaListFragment fragment = MediaListFragment.newInstance(imagesContainer);
        fragmentTransaction.replace(R.id.frameLayoutSDMedia, fragment);
        fragmentTransaction.commit();
    }

    private void startSummaryFragment(String summary){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SeriesDetailsSummaryFragment fragment = SeriesDetailsSummaryFragment.newInstance(summary);
        fragmentTransaction.replace(R.id.frameLayoutSDSummary, fragment);
        fragmentTransaction.commit();
    }

    private void startCastingFragment(ArrayList<ImageListItem> imageList, String title){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        ImageListFragment imageListFragment = ImageListFragment.newInstance(imageList, title);
        fragmentTransaction.replace(R.id.frameLayoutSDCasting, imageListFragment);
        fragmentTransaction.commit();
    }

    private void startActionsFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ActionsFragment fragment = new ActionsFragment();
        fragmentTransaction.replace(R.id.frameLayoutSDActions, fragment);
        fragmentTransaction.commit();
    }

    private void startInfoFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SeriesDetailsInfoFragment fragment = SeriesDetailsInfoFragment.newInstance(serie);
        fragmentTransaction.replace(R.id.frameLayoutSDInfo, fragment);
        fragmentTransaction.commit();
    }

    private void setUpRatings(){
        Fragment fragment;
        Double subTotalVoteAverage = serie.getVoteAverage() * 10.0;
        Integer voteAverage = subTotalVoteAverage.intValue();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = RateProgressBarFragment.newInstance("TMDB", voteAverage);
        fragmentTransaction.add(linearLayoutRatings.getId(), fragment);
        fragmentTransaction.commit();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = RateProgressBarFragment.newInstance("Tomatometer", 97);
        fragmentTransaction.add(linearLayoutRatings.getId(), fragment);
        fragmentTransaction.commit();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = RateProgressBarFragment.newInstance("IMDb", 95);
        fragmentTransaction.add(linearLayoutRatings.getId(), fragment);
        fragmentTransaction.commit();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = RateProgressBarFragment.newInstance("Letterboxd", 95);
        fragmentTransaction.add(linearLayoutRatings.getId(), fragment);
        fragmentTransaction.commit();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = RateProgressBarFragment.newInstance("Metascore", 95);
        fragmentTransaction.add(linearLayoutRatings.getId(), fragment);
        fragmentTransaction.commit();
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
}
