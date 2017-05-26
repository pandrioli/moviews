package digitalhouse.android.a0317moacns1c_02.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieCredits;
import digitalhouse.android.a0317moacns1c_02.Fragments.PersonListFragment;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.MovieService;

public class MovieDetailsTestActivity extends AppCompatActivity {
    @BindView(R.id.TextViewMovieDetails) protected TextView textView;

    private MovieCredits movieCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_test);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        Integer movieId = bundle.getInt("movieId");
        MovieService.getInstance().getMovieCredits(movieId, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                movieCredits = (MovieCredits) result;
                loadCreditsFragment();
            }
        });
    }

    private void loadCreditsFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PersonListFragment.PERSON_LIST_KEY, movieCredits.getCastList());
        PersonListFragment personListFragment = new PersonListFragment();
        personListFragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.PersonListContainer, personListFragment);
        ft.commit();
    }
}
