package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.PersonController;
import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.NetworkHelper;
import digitalhouse.android.a0317moacns1c_02.Mappers.ImageViewMapper;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonDetails;
import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;
import digitalhouse.android.a0317moacns1c_02.R;

public class PersonDetailsActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {
    public final static String PERSON_ID_KEY = "personId";

    @BindView(R.id.frameLayoutLoaderPersonDetails) FrameLayout loaderContainer;
    @BindView(R.id.textViewPDName)
    TextView textViewName;
    @BindView(R.id.textViewPDBirthDay)
    TextView textViewBirthDay;
    @BindView(R.id.textViewPDBirthPlace)
    TextView textViewBirthPlace;
    @BindView(R.id.textViewPDBio)
    TextView textViewBio;
    @BindView(R.id.imageViewPDprofile)
    ImageView imageViewProfile;

    private Integer loadCounter = 0;
    private ArrayList<ImageListItem> personImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        ButterKnife.bind(this);
        ActivityStackManager.getInstance().addActivity(this);
        Bundle bundleReceived = getIntent().getExtras();
        Integer id = bundleReceived.getInt(PERSON_ID_KEY);
        loaderContainer.setVisibility(View.VISIBLE);
        if (NetworkHelper.isNetworkAvailable(this)) {
            PersonController.getInstance().getDetails(id, new ResultListener<PersonDetails>() {
                @Override
                public void finish(PersonDetails personDetails) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(PersonDetails.tag, personDetails);
                    setupViews(personDetails);
                    stopLoader();
                }
            });
            PersonController.getInstance().getImageList(id, new ResultListener<ArrayList<ImageListItem>>() {
                @Override
                public void finish(ArrayList<ImageListItem> imageList) {
                    personImages = imageList;
                    startPersonDetailsImageFragment(imageList);
                    stopLoader();
                }
            });
            PersonController.getInstance().getMovieCreditsImageList(id, new ResultListener<ArrayList<ImageListItem>>() {
                @Override
                public void finish(ArrayList<ImageListItem> imageList) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ImageListFragment.TITLE_KEY, "Movie credits");
                    bundle.putSerializable(ImageListFragment.IMAGE_LIST_KEY, imageList);
                    startPersonDetailsMovieCreditsFragment(bundle);
                    stopLoader();
                }
            });
        } else {
            Toast.makeText(this, "Not available without connection", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void stopLoader() {
        loadCounter++;
        if (loadCounter==3) loaderContainer.setVisibility(View.GONE);
    }

    private void setupViews(final PersonDetails personDetails) {
        textViewName.setText(personDetails.getName());
        Date birthday = personDetails.getBirthdayDate();
        Date deathday = personDetails.getDeathdayDate();
        if (birthday != null) {
            String birthdayAndAge = DateHelper.format(birthday, DateHelper.FORMAT_ARG);
            if (deathday == null)
                birthdayAndAge += " (Age: " + DateHelper.age(birthday) + ")";
            else birthdayAndAge += " (Death: " + DateHelper.format(deathday, DateHelper.FORMAT_ARG) + ")";
            textViewBirthDay.setText(birthdayAndAge);
        } else {
            textViewBirthDay.setText("no info");
        }
        if (personDetails.getPlace_of_birth() != null) {
            textViewBirthPlace.setText(personDetails.getPlace_of_birth());
        } else {
            textViewBirthPlace.setText("no info");
        }
        textViewBio.setText(personDetails.getBiography());
        String url = ImageHelper.getProfileURL(personDetails.getProfile_path(), 2);
        Picasso.with(this).load(url).fit().centerCrop().into(imageViewProfile);
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageViewActivity(personDetails.getProfile_path());
            }
        });
    }

    private void startImageViewActivity(String imagePath) {
        Intent intent = new Intent(this, ImageViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ImageViewActivity.IMAGE_URL_KEY, ImageViewMapper.map(imagePath));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void startPersonDetailsImageFragment(ArrayList<ImageListItem> imageList) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ImageListFragment imgFragment = ImageListFragment.newInstance(imageList, "Images", true);
        ft.replace(R.id.frameLayoutPDImages, imgFragment);
        ft.commit();
    }
    private void startPersonDetailsMovieCreditsFragment(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ImageListFragment imgFragment = new ImageListFragment();
        imgFragment.setArguments(bundle);
        ft.replace(R.id.frameLayoutPDCredits, imgFragment);
        ft.commit();
    }

    @Override
    public void onClick(ImageListItem imageListItem, String title, Integer index) {
        if (title.equals("Movie credits")) {
            Bundle bundle = new Bundle();
            bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, imageListItem.getId());
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (title.equals("Images")) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(ImageViewActivity.IMAGE_LIST_URL_KEY, ImageViewMapper.map(personImages));
            bundle.putInt(ImageViewActivity.IMAGE_INDEX_KEY, index);
            Intent intent = new Intent(this, ImageViewActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeLastActivity();
    }
}
