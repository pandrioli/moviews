package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.PersonController;
import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;
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
        AnimationHelper.postponeTransition(this);
        setContentView(R.layout.activity_person_details);
        ButterKnife.bind(this);
        ActivityStackManager.getInstance().addActivity(this);
        Bundle bundleReceived = getIntent().getExtras();
        Integer id = bundleReceived.getInt(PERSON_ID_KEY);
        if (NetworkHelper.isNetworkAvailable(this)) {
            PersonController.getInstance().getDetails(id, new ResultListener<PersonDetails>() {
                @Override
                public void finish(PersonDetails personDetails) {
                    setupViews(personDetails);
                    startTransition();
                }
            });
            PersonController.getInstance().getImageList(id, new ResultListener<ArrayList<ImageListItem>>() {
                @Override
                public void finish(ArrayList<ImageListItem> imageList) {
                    personImages = imageList;
                    startPersonDetailsImageFragment(imageList);
                    startTransition();
                }
            });
            PersonController.getInstance().getMovieCreditsImageList(id, new ResultListener<ArrayList<ImageListItem>>() {
                @Override
                public void finish(ArrayList<ImageListItem> imageList) {
                    startImageListCreditsFragment(R.id.frameLayoutPDMovieCredits, "Movie credits", imageList);
                    startTransition();
                }
            });
            PersonController.getInstance().getTVCreditsImageList(id, new ResultListener<ArrayList<ImageListItem>>() {
                @Override
                public void finish(ArrayList<ImageListItem> imageList) {
                    startImageListCreditsFragment(R.id.frameLayoutPDTVCredits, "TV credits", imageList);
                    startTransition();
                }
            });
        } else {
            Toast.makeText(this, "Not available without connection", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void startTransition() {
        loadCounter++;
        if (loadCounter==5) {
            AnimationHelper.startPostponedTransition(this);
        }
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
        Picasso.with(this).load(url).fit().centerCrop().into(imageViewProfile, new Callback() {
            @Override
            public void onSuccess() {
                startTransition();
            }

            @Override
            public void onError() {
                startTransition();
            }
        });
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageViewActivity(personDetails.getProfile_path(), imageViewProfile);
            }
        });
    }

    private void startImageViewActivity(String imagePath, ImageView imageView) {
        AnimationHelper.startLoader(this);
        Bundle transitionBundle = AnimationHelper.getTransitionBundle(this, imageView, "imageviewer");
        Intent intent = new Intent(this, ImageViewerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ImageViewerActivity.IMAGE_URL_KEY, ImageViewMapper.map(imagePath));
        intent.putExtras(bundle);
        startActivity(intent, transitionBundle);
    }

    private void startPersonDetailsImageFragment(ArrayList<ImageListItem> imageList) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ImageListFragment imgFragment = ImageListFragment.newInstance(imageList, "Images", true);
        ft.replace(R.id.frameLayoutPDImages, imgFragment);
        ft.commit();
    }

    private void startImageListCreditsFragment(Integer layout, String title, ArrayList<ImageListItem> imageListItems) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(layout, ImageListFragment.newInstance(imageListItems, title));
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        AnimationHelper.stopLoader(this);
    }

    @Override
    public void onClick(ImageListItem imageListItem, String title, Integer index, ImageView imageView) {
        if (title.equals("Movie credits")) {
            AnimationHelper.startLoader(this);
            Bundle transitionBundle = AnimationHelper.getTransitionBundle(this, imageView, "poster");
            Bundle bundle = new Bundle();
            bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, imageListItem.getId());
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent, transitionBundle);
        }
        if (title.equals("TV credits")) {
            AnimationHelper.startLoader(this);
            Bundle transitionBundle = AnimationHelper.getTransitionBundle(this, imageView, "poster");
            Bundle bundle = new Bundle();
            bundle.putString(SerieActivity.SERIE_ID_KEY, imageListItem.getId().toString());
            Intent intent = new Intent(this, SerieActivity.class);
            intent.putExtras(bundle);
            startActivity(intent, transitionBundle);
        }
        if (title.equals("Images")) {
            Bundle transitionBundle = AnimationHelper.getTransitionBundle(this, imageView, "imageviewer");
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(ImageViewerActivity.IMAGE_LIST_URL_KEY, ImageViewMapper.map(personImages));
            bundle.putInt(ImageViewerActivity.IMAGE_INDEX_KEY, index);
            bundle.putBoolean(ImageViewerActivity.NO_RETURN_ANIMATION_FLAG, true);
            Intent intent = new Intent(this, ImageViewerActivity.class);
            intent.putExtras(bundle);
            startActivity(intent, transitionBundle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeLastActivity();
    }
}
