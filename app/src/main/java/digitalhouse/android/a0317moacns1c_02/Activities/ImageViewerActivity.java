package digitalhouse.android.a0317moacns1c_02.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Adapters.ImageViewPagerAdapter;
import digitalhouse.android.a0317moacns1c_02.CustomViews.PageIndicator;
import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;
import digitalhouse.android.a0317moacns1c_02.R;

public class ImageViewerActivity extends AppCompatActivity {

    public static final String IMAGE_INDEX_KEY = "imageIndex";
    public static final String IMAGE_URL_KEY = "imageURL";
    public static final String IMAGE_LIST_URL_KEY = "imageListURL";
    public static final String LANDSCAPE_KEY = "landscape";

    private List<String> imagesURL;

    @BindView(R.id.viewPagerImageView)
    ViewPager viewPager;
    @BindView(R.id.pageIndicatorImageView)
    PageIndicator pageIndicator;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ButterKnife.bind(this);
        AnimationHelper.postponeTransition(this);
        Bundle bundle = getIntent().getExtras();
        Boolean landscape = bundle.getBoolean(LANDSCAPE_KEY);
        landscape = landscape == null ? false : landscape;
        if (landscape) {
            //el cambio de orientacion causa error al volver a MovieDetails en mi telefono (no en el emulador)
            //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        imagesURL = new ArrayList<>();
        if (bundle.containsKey(IMAGE_LIST_URL_KEY)) {
            imagesURL = bundle.getStringArrayList(IMAGE_LIST_URL_KEY);
        } else if (bundle.containsKey(IMAGE_URL_KEY)) {
            imagesURL.add(bundle.getString(IMAGE_URL_KEY));
        }
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(getSupportFragmentManager(), imagesURL);
        viewPager.setAdapter(adapter);
        Integer imageIndex = 0;
        if (bundle.containsKey(IMAGE_INDEX_KEY)) {
            imageIndex = bundle.getInt(IMAGE_INDEX_KEY);
            viewPager.setCurrentItem(imageIndex);
        }
        pageIndicator.setTotalPages(imagesURL.size());
        pageIndicator.setPage(imageIndex);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageIndicator.setPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
