package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Adapters.ImageViewPagerAdapter;
import digitalhouse.android.a0317moacns1c_02.R;

public class ImageViewActivity extends AppCompatActivity {

    public static final String IMAGE_INDEX_KEY = "imageIndex";
    public static final String IMAGE_URL_KEY = "imageURL";
    public static final String IMAGE_LIST_URL_KEY = "imageListURL";
    public static final String LANDSCAPE_KEY = "landscape";

    @BindView(R.id.viewPagerImageView)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        Boolean landscape = bundle.getBoolean(LANDSCAPE_KEY);
        landscape = landscape == null ? false : landscape;
        if (landscape) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        List<String> imagesURL = new ArrayList<>();
        if (bundle.containsKey(IMAGE_INDEX_KEY)) {
            imagesURL = bundle.getStringArrayList(IMAGE_LIST_URL_KEY);
        } else if (bundle.containsKey(IMAGE_URL_KEY)) {
            imagesURL.add(bundle.getString(IMAGE_URL_KEY));
        }
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(getSupportFragmentManager(), imagesURL);
        viewPager.setAdapter(adapter);
        if (bundle.containsKey(IMAGE_INDEX_KEY)) {
            Integer imageIndex = bundle.getInt(IMAGE_INDEX_KEY);
            viewPager.setCurrentItem(imageIndex);
        }
    }
}
