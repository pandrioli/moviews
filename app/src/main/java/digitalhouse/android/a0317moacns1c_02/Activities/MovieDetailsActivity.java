package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Genres.GenreAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Misc.CompanyAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Misc.LanguageAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieDetails.MovieDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.ImageData;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieCredits;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieData;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieDetailsPosterFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieDetailsProductionTeamFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieDetailsTitleFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.MultimediaListFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.PersonListFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageMapper;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.MovieService;

public class MovieDetailsActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {
    public static final String MOVIE_ID_KEY = "movieId";

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        fragmentManager = getSupportFragmentManager();

        ButterKnife.bind(this);
        Bundle bundleReceived = getIntent().getExtras();
        Integer id = bundleReceived.getInt(MOVIE_ID_KEY);
        MovieService.getInstance().getMovieData(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                MovieData movieData = (MovieData) result;
                Bundle bundle = new Bundle();
                bundle.putSerializable(movieData.tag, movieData);
                startMovieDetailsPosterActivity(bundle);
                startMovieDetailsTitleFragment(bundle);
            }
        });
        MovieService.getInstance().getMovieCredits(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                MovieCredits movieCredits = (MovieCredits) result;
                Bundle bundle1 = new Bundle();
                bundle1.putParcelableArrayList(ImageListFragment.IMAGE_LIST_KEY,movieCredits.getCastList());
                bundle1.putString(ImageListFragment.TITLE_KEY, "Cast");
                startMovieDetailsCastFragment(bundle1);
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList(MovieDetailsProductionTeamFragment.CREW_LIST_KEY,movieCredits.getCrewList());
                startMovieDetailsProductionTeam(bundle2);
            }
        });
        MovieService.getInstance().getMovieBackdrops(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                List<ImageData> imageList = (List<ImageData>) result;
                ArrayList<String> URLsArray = ImageMapper.map(imageList);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(MultimediaListFragment.MULTIMEDIA_LIST_KEY, URLsArray);
                startMultimediaListFragment(bundle);
            }
        });
    }

    private void startMovieDetailsCastFragment(Bundle bundle) {

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        ImageListFragment castListFragment = new ImageListFragment();

        castListFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameLayoutMDCast, castListFragment);

        fragmentTransaction.commit();
    }

    private void startMovieDetailsProductionTeam(Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        MovieDetailsProductionTeamFragment crewFragment = new MovieDetailsProductionTeamFragment();

        crewFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameLayoutMDCrew, crewFragment);

        fragmentTransaction.commit();
    }

    private void startMovieDetailsTitleFragment(Bundle bundle){

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        MovieDetailsTitleFragment movieDetailsTitleFragment = new MovieDetailsTitleFragment();

        movieDetailsTitleFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameLayoutMDTitle, movieDetailsTitleFragment);

        fragmentTransaction.commit();
    }

    private void startMovieDetailsPosterActivity(Bundle bundle){

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        MovieDetailsPosterFragment movieDetailsPosterFragment = new MovieDetailsPosterFragment();

        movieDetailsPosterFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameLayoutMDPoster, movieDetailsPosterFragment);

        fragmentTransaction.commit();
    }

    private void startMultimediaListFragment(Bundle bundle){

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        MultimediaListFragment multimediaListFragment = new MultimediaListFragment();

        multimediaListFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameLayoutMDImages, multimediaListFragment);

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
