package digitalhouse.android.a0317moacns1c_02.Activities;import android.content.Intent;import android.support.v4.app.FragmentManager;import android.support.v4.app.FragmentTransaction;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.widget.ImageView;import android.widget.TextView;import com.squareup.picasso.Picasso;import java.util.ArrayList;import java.util.Date;import butterknife.BindView;import butterknife.ButterKnife;import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;import digitalhouse.android.a0317moacns1c_02.Controller.MovieController;import digitalhouse.android.a0317moacns1c_02.Controller.RateController;import digitalhouse.android.a0317moacns1c_02.Fragments.MediaListFragment;import digitalhouse.android.a0317moacns1c_02.Fragments.ProductionTeamFragment;import digitalhouse.android.a0317moacns1c_02.Fragments.RateFragment;import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;import digitalhouse.android.a0317moacns1c_02.Model.Credits.Credits;import digitalhouse.android.a0317moacns1c_02.Model.Credits.Crew;import digitalhouse.android.a0317moacns1c_02.Model.Media.ImagesContainer;import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageListItem;import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieDetails;import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;import digitalhouse.android.a0317moacns1c_02.R;public class MovieDetailsActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {    public static final String MOVIE_ID_KEY = "movieId";    FragmentManager fragmentManager;    @BindView(R.id.textViewMDTitle) TextView textViewTitle;    @BindView(R.id.textViewMDGenres) TextView textViewGenres;    @BindView(R.id.textViewMDDate) TextView textViewDate;    @BindView(R.id.textViewMDRuntime) TextView textViewRuntime;    @BindView(R.id.textViewMDOverview) TextView textViewOverview;    @BindView(R.id.imageViewMDPoster) ImageView imageViewPoster;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_movie_details);        ActivityStackManager.getInstance().addActivity(this); // agrego la activity al stack manager        fragmentManager = getSupportFragmentManager();        ButterKnife.bind(this);        Bundle bundleReceived = getIntent().getExtras();        Integer id = bundleReceived.getInt(MOVIE_ID_KEY);        MovieController.getInstance().getDetails(id, new ResultListener<MovieDetails>() {            @Override            public void finish(MovieDetails movieDetails) {                setupViews(movieDetails);                startRatingsFragment(movieDetails);            }        });        MovieController.getInstance().getCredits(id, new ResultListener<Credits>() {            @Override            public void finish(Credits credits) {                ArrayList<ImageListItem> imageList = MovieController.getInstance().getCastImageList(credits);                startMovieDetailsCastFragment(imageList, "Cast");                startMovieDetailsProductionTeam(credits.getCrew());            }        });        MovieController.getInstance().getImages(id, new ResultListener<ImagesContainer>() {            @Override            public void finish(ImagesContainer imagesContainer) {                startMediaListFragment(imagesContainer);            }        });    }    private void setupViews(MovieDetails movieDetails) {        textViewTitle.setText(movieDetails.getTitle());        textViewGenres.setText(GenreController.getInstance().getGenresString(movieDetails.getGenres()," | "));        Date releaseDate = DateHelper.parse(movieDetails.getRelease_date(),DateHelper.FORMAT_API);        textViewDate.setText(DateHelper.format(releaseDate, DateHelper.FORMAT_FULL));        textViewRuntime.setText(movieDetails.getRuntimeString());        textViewOverview.setText(movieDetails.getOverview());        String url = ImageHelper.getPosterURL(movieDetails.getPoster_path(),2);        Picasso.with(this).load(url).fit().into(imageViewPoster);    }    private void startRatingsFragment(MovieDetails movieDetails){        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();        RateFragment fragment = RateController.instanceRateFragment(movieDetails);        fragmentTransaction.replace(R.id.framelayoutMDRatings, fragment);        fragmentTransaction.commit();    }    private void startMovieDetailsCastFragment(ArrayList<ImageListItem> imageList, String title) {        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        ImageListFragment imageListFragment = ImageListFragment.newInstance(imageList, title);        fragmentTransaction.replace(R.id.frameLayoutMDCast, imageListFragment);        fragmentTransaction.commit();    }    private void startMovieDetailsProductionTeam(ArrayList<Crew> crewList) {        FragmentManager fragmentManager = getSupportFragmentManager();        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        ProductionTeamFragment crewFragment = ProductionTeamFragment.newInstance(crewList);        fragmentTransaction.replace(R.id.frameLayoutMDProductionTeam, crewFragment);        fragmentTransaction.commit();    }    private void startMediaListFragment(ImagesContainer imagesContainer){        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        ArrayList<String> URLs = (ArrayList<String>) imagesContainer.getURLsList();        MediaListFragment mediaListFragment = MediaListFragment.newInstance(URLs);        fragmentTransaction.replace(R.id.frameLayoutMDImages, mediaListFragment);        fragmentTransaction.commit();    }    @Override    public void onDestroy() {        super.onDestroy();        ActivityStackManager.getInstance().removeLastActivity(); // saco la activity del stack manager    }    @Override    public void onClick(Integer id, String title) {        if (title.equals("Cast")) {            Bundle bundle = new Bundle();            bundle.putInt(PersonDetailsActivity.PERSON_ID_KEY, id);            Intent intent = new Intent(this, PersonDetailsActivity.class);            intent.putExtras(bundle);            startActivity(intent);        }    }}