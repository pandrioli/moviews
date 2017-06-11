package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Controller.PersonController;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Person.PersonDetails;
import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.PersonDetailsBioFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.PersonDetailsInfoFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;
import digitalhouse.android.a0317moacns1c_02.R;

public class PersonDetailsActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {
    public final static String PERSON_ID_KEY = "personId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        ActivityStackManager.getInstance().addActivity(this);
        Bundle bundleReceived = getIntent().getExtras();
        Integer id = bundleReceived.getInt(PERSON_ID_KEY);
        PersonController.getInstance().getDetails(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                PersonDetails personDetails = (PersonDetails) result;
                Bundle bundle = new Bundle();
                bundle.putParcelable(PersonDetails.tag, personDetails);
                startPersonDetailsInfoFragment(bundle);
                startPersonDetailsBioFragment(bundle);
            }
        });
        PersonController.getInstance().getImageList(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                ArrayList<ImageListItem> imageList = (ArrayList<ImageListItem>) result;
                startPersonDetailsImageFragment(imageList, "Images");
            }
        });
        PersonController.getInstance().getMovieCreditsImageList(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                ArrayList<ImageListItem> imageList = (ArrayList<ImageListItem>) result;
                Bundle bundle = new Bundle();
                bundle.putString(ImageListFragment.TITLE_KEY, "Movie credits");
                bundle.putParcelableArrayList(ImageListFragment.IMAGE_LIST_KEY, imageList);
                startPersonDetailsMovieCreditsFragment(bundle);
            }
        });
    }

    private void startPersonDetailsInfoFragment(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PersonDetailsInfoFragment PDIFragment = new PersonDetailsInfoFragment();
        PDIFragment.setArguments(bundle);
        ft.replace(R.id.frameLayoutPDInfo, PDIFragment);
        ft.commit();
    }
    private void startPersonDetailsBioFragment(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PersonDetailsBioFragment bioFragment = new PersonDetailsBioFragment();
        bioFragment.setArguments(bundle);
        ft.replace(R.id.frameLayoutPDBio, bioFragment);
        ft.commit();
    }
    private void startPersonDetailsImageFragment(ArrayList<ImageListItem> imageList, String title) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ImageListFragment imgFragment = ImageListFragment.newInstance(imageList, title);
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
    public void onClick(Integer id, String title) {
        if (title.equals("Movie credits")) {
            Bundle bundle = new Bundle();
            bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, id);
            Intent intent = new Intent(this, MovieDetailsActivity.class);
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
