package digitalhouse.android.a0317moacns1c_02.Activities;import android.content.Intent;import android.support.v4.app.FragmentManager;import android.support.v4.app.FragmentTransaction;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import java.util.ArrayList;import butterknife.ButterKnife;import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;import digitalhouse.android.a0317moacns1c_02.Controller.MovieController;import digitalhouse.android.a0317moacns1c_02.Fragments.MediaListFragment;import digitalhouse.android.a0317moacns1c_02.Fragments.ProductionTeamFragment;import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;import digitalhouse.android.a0317moacns1c_02.Model.Credits.Crew;import digitalhouse.android.a0317moacns1c_02.Model.General.ImagesContainer;import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageListItem;import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieDetails;import digitalhouse.android.a0317moacns1c_02.Fragments.MovieDetailsPosterFragment;import digitalhouse.android.a0317moacns1c_02.Fragments.MovieDetailsTitleFragment;import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;import digitalhouse.android.a0317moacns1c_02.R;public class MovieDetailsActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {    public static final String MOVIE_ID_KEY = "movieId";    FragmentManager fragmentManager;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_movie_details);        ActivityStackManager.getInstance().addActivity(this); // agrego la activity al stack manager        fragmentManager = getSupportFragmentManager();        ButterKnife.bind(this);        Bundle bundleReceived = getIntent().getExtras();        Integer id = bundleReceived.getInt(MOVIE_ID_KEY);        MovieController.getInstance().getDetails(id, new TMDBClient.APICallback() {            @Override            public void onSuccess(Object result) {                MovieDetails movieDetails = (MovieDetails) result;                Bundle bundle = new Bundle();                bundle.putParcelable(movieDetails.tag, movieDetails);                startMovieDetailsPosterActivity(bundle);                startMovieDetailsTitleFragment(bundle);            }        });        MovieController.getInstance().getCredits(id, new TMDBClient.APICallback() {            @Override            public void onSuccess(Object result) {                Credits credits = (Credits) result;                ArrayList<ImageListItem> imageList = MovieController.getInstance().getCastImageList(credits);                startMovieDetailsCastFragment(imageList, "Cast");                startMovieDetailsProductionTeam(credits.getCrew());            }        });        MovieController.getInstance().getImages(id, new TMDBClient.APICallback() {            @Override            public void onSuccess(Object result) {                ImagesContainer imagesContainer = (ImagesContainer) result;                startMediaListFragment(imagesContainer);            }        });    }    private void startMovieDetailsCastFragment(ArrayList<ImageListItem> imageList, String title) {        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        ImageListFragment imageListFragment = ImageListFragment.newInstance(imageList, title);        fragmentTransaction.replace(R.id.frameLayoutMDCast, imageListFragment);        fragmentTransaction.commit();    }    private void startMovieDetailsProductionTeam(ArrayList<Crew> crewList) {        FragmentManager fragmentManager = getSupportFragmentManager();        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        ProductionTeamFragment crewFragment = ProductionTeamFragment.newInstance(crewList);        fragmentTransaction.replace(R.id.frameLayoutMDCrew, crewFragment);        fragmentTransaction.commit();    }    private void startMovieDetailsTitleFragment(Bundle bundle){        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        MovieDetailsTitleFragment movieDetailsTitleFragment = new MovieDetailsTitleFragment();        movieDetailsTitleFragment.setArguments(bundle);        fragmentTransaction.replace(R.id.frameLayoutMDTitle, movieDetailsTitleFragment);        fragmentTransaction.commit();    }    private void startMovieDetailsPosterActivity(Bundle bundle){        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        MovieDetailsPosterFragment movieDetailsPosterFragment = new MovieDetailsPosterFragment();        movieDetailsPosterFragment.setArguments(bundle);        fragmentTransaction.replace(R.id.frameLayoutMDPoster, movieDetailsPosterFragment);        fragmentTransaction.commit();    }    private void startMediaListFragment(ImagesContainer imagesContainer){        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        MediaListFragment mediaListFragment = MediaListFragment.newInstance(imagesContainer);        fragmentTransaction.replace(R.id.frameLayoutMDImages, mediaListFragment);        fragmentTransaction.commit();    }    @Override    public void onDestroy() {        super.onDestroy();        ActivityStackManager.getInstance().removeLastActivity(); // saco la activity del stack manager    }    @Override    public void onClick(Integer id, String title) {        if (title.equals("Cast")) {            Bundle bundle = new Bundle();            bundle.putInt(PersonDetailsActivity.PERSON_ID_KEY, id);            Intent intent = new Intent(this, PersonDetailsActivity.class);            intent.putExtras(bundle);            startActivity(intent);        }    }}