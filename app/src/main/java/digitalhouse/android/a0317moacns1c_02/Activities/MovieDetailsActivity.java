package digitalhouse.android.a0317moacns1c_02.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Genres.GenreAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Misc.CompanyAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Misc.LanguageAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieDetails.MovieDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieCredits;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieData;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieDetailsPosterFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieDetailsTitleFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.PersonListFragment;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.MovieService;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.frameLayoutMDImages) FrameLayout frameLayoutMDImages;
    @BindView(R.id.frameLayoutMDTitle) FrameLayout frameLayoutMDTitle;
    @BindView(R.id.frameLayoutMDPoster) FrameLayout frameLayoutMDPoster;
    @BindView(R.id.imageViewDetailsBackdrop) ImageView imageViewBackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);
        Bundle bundleReceived = getIntent().getExtras();
        Integer id = bundleReceived.getInt("movieId");
        MovieService.getInstance().getMovieData(id, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                MovieData movieData = (MovieData) result;
                Picasso.with(MovieDetailsActivity.this).load(movieData.getBackdropURL(3)).fit().centerCrop().into(imageViewBackdrop);
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
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(PersonListFragment.PERSON_LIST_KEY,movieCredits.getCastList());
                bundle.putString(PersonListFragment.TITLE_KEY, "Cast");
                startMovieDetailsCastFragment(bundle);
            }
        });
    }

    private void startMovieDetailsCastFragment(Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        PersonListFragment personListFragment = new PersonListFragment();

        personListFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameLayoutMDCast, personListFragment);

        fragmentTransaction.commit();
    }

    private void startMovieDetailsTitleFragment(Bundle bundle){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        MovieDetailsTitleFragment movieDetailsTitleFragment = new MovieDetailsTitleFragment();

        movieDetailsTitleFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameLayoutMDTitle, movieDetailsTitleFragment);

        fragmentTransaction.commit();
    }

    private void startMovieDetailsPosterActivity(Bundle bundle){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        MovieDetailsPosterFragment movieDetailsPosterFragment = new MovieDetailsPosterFragment();

        movieDetailsPosterFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameLayoutMDPoster, movieDetailsPosterFragment);

        fragmentTransaction.commit();
    }

    private MovieDetailsAPI hardCodearMovieDetails(){
        MovieDetailsAPI movieDetailsAPI = new MovieDetailsAPI();
        movieDetailsAPI.setAdult(false);
        movieDetailsAPI.setBackdrop_path("/ytKo2tLvKUd9Kbs0poXSQh6ft7d.jpg");
        movieDetailsAPI.setBudget(63000000);
        ArrayList<GenreAPI> genreAPIs = new ArrayList<>();
        genreAPIs.add(new GenreAPI(18, "Drama"));
        genreAPIs.add(new GenreAPI(19, "Ciencia-Ficción"));

        movieDetailsAPI.setGenres(genreAPIs);
        movieDetailsAPI.setId(550);
        movieDetailsAPI.setImdb_id("tt0137523");
        movieDetailsAPI.setOriginal_language("en");
        movieDetailsAPI.setOriginal_title("Fight Club");
        movieDetailsAPI.setOverview("A ticking-time-bomb insomniac and a slippery soap salesman " +
                "channel primal male aggression into a shocking new form of therapy. Their concept" +
                " catches on, with underground \\\"fight clubs\\\" forming in every town, until an " +
                "eccentric gets in the way and ignites an out-of-control spiral toward oblivion.");
        movieDetailsAPI.setPopularity(0.5);
        movieDetailsAPI.setPoster_path(null);

        ArrayList<CompanyAPI> companyAPIs = new ArrayList<>();
        companyAPIs.add(new CompanyAPI(25, "20th Century Fox"));
        movieDetailsAPI.setProduction_companies(companyAPIs);

        movieDetailsAPI.setRelease_date("1999-10-12");

        movieDetailsAPI.setRevenue(100853753);

        movieDetailsAPI.setRuntime(139);

        ArrayList<LanguageAPI> languageAPIs = new ArrayList<>();
        languageAPIs.add(new LanguageAPI("en", "English"));
        movieDetailsAPI.setSpoken_languages(languageAPIs);

        movieDetailsAPI.setStatus("Released");
        movieDetailsAPI.setTagline("How much can you know about yourself if you've never been in a " +
                "fight?");

        movieDetailsAPI.setTitle("Fight Club");
        movieDetailsAPI.setVideo(false);

        movieDetailsAPI.setVote_average(7.8);

        movieDetailsAPI.setVote_count(3439);

        return movieDetailsAPI;
    }
}
