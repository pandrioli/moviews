package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImagesContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieOmdb;
import digitalhouse.android.a0317moacns1c_02.R;

public class SerieDetailsActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {

    public static final String SERIE_ID_KEY = "serieID";

    @BindView(R.id.textViewSDSummary) protected TextView textViewSummary;

    private FragmentManager fragmentManager;
    private SerieDetails serieDetails;
    private SerieOmdb serieOmdb;
    private Serie serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_details);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        Bundle bundleReceived = getIntent().getExtras();
        String id = bundleReceived.getString(SERIE_ID_KEY);

        SerieController.getInstance().getDetails(id.toString(), new ResultListener<SerieDetails>() {
            @Override
            public void finish(SerieDetails result) {
                serieDetails = result;
                serieOmdb = SerieController.getInstance().getSerie(serieDetails.getName());
                serie = SerieController.getInstance().map(serieDetails, serieOmdb);
                textViewSummary.setText(serieDetails.getOverview());
                startRatingsFragment(serie);
                startTitleFargment();
                startInfoFragment();
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
    }

    private void startTitleFargment(){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        SeriesDetailsTitleFragment fragment = SeriesDetailsTitleFragment.newInstance(serieDetails);
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

    private void startCastingFragment(ArrayList<ImageListItem> imageList, String title){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        ImageListFragment imageListFragment = ImageListFragment.newInstance(imageList, title);
        fragmentTransaction.replace(R.id.frameLayoutSDCasting, imageListFragment);
        fragmentTransaction.commit();
    }

    private void startInfoFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SeriesDetailsInfoFragment fragment = SeriesDetailsInfoFragment.newInstance(serieDetails);
        fragmentTransaction.replace(R.id.frameLayoutSDInfo, fragment);
        fragmentTransaction.commit();
    }

    private void startRatingsFragment(Serie serie){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RateFragment fragment = RateController.instanceRateFragment(serie);
        fragmentTransaction.replace(R.id.framelayoutSDRatings, fragment);
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
