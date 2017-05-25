package digitalhouse.android.a0317moacns1c_02.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.MovieService;

public class MovieDetailsTestActivity extends AppCompatActivity {
    @BindView(R.id.TextViewMovieDetails) protected TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_test);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String movieId = bundle.getString("movieId");
        MovieService.getInstance().obtainMovieDetails(movieId, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                textView.setText(result.toString());
            }
        });
    }
}
