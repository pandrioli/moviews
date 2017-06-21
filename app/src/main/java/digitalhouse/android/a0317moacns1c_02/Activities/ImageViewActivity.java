package digitalhouse.android.a0317moacns1c_02.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.R;

public class ImageViewActivity extends AppCompatActivity {

    public static final String IMAGE_PATH_KEY = "imagePath";

    @BindView(R.id.imageViewImageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String imagePath = bundle.getString(IMAGE_PATH_KEY);
        String imageURL = ImageHelper.getOriginalSizeURL(imagePath);
        Picasso.with(this).load(imageURL).fit().centerInside().into(imageView);
    }
}
