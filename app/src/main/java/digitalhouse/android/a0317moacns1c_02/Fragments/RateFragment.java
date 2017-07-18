package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Model.General.RatingsContainer;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RateFragment extends Fragment implements Serializable {
    @BindView(R.id.textViewIMDbRate) protected TextView imdbRate;
    @BindView(R.id.textViewTMDBRate) protected TextView tmdbRate;
    @Nullable
    @BindView(R.id.imageViewRottenTomatoes) protected ImageView rottenTomatoesLogo;
    @Nullable
    @BindView(R.id.textViewRottenTomatoesPercentage) protected  TextView rottenTomatoesRate;
    @Nullable
    @BindView(R.id.imageViewMetacritic) protected ImageView metacriticLogo;
    @Nullable
    @BindView(R.id.textViewMetacriticRate) protected TextView metascore;
    @Nullable
    @BindView(R.id.textViewMetacriticMaxRate) protected TextView maxMetascore;
    @BindView(R.id.circularProgressBarMVSScore) protected CircularProgressBar circularProgressBar;
    @BindView(R.id.textViewMoviewsScore) protected TextView textViewMoviewsScore;

    private static final String RATE_FRAGMENT_KEY = "ratingsContainer";
    private RatingsContainer ratingsContainer;
    private Unbinder unbinder;


    public RateFragment() {
        // Required empty public constructor
    }

    public static RateFragment newInstance(RatingsContainer ratingsContainer) {
        RateFragment fragment = new RateFragment();
        Bundle args = new Bundle();
        args.putSerializable(RATE_FRAGMENT_KEY, ratingsContainer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ratingsContainer = (RatingsContainer) getArguments().getSerializable(RATE_FRAGMENT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        if(ratingsContainer.hasMetascoreAndRottenScore())
        {
            view  = inflater.inflate(R.layout.fragment_rate_full, container, false);
            unbinder = ButterKnife.bind(this, view);
            setAndToggleMetascore();
            setAndToggleRottenTomatoes();
        } else {
            view = inflater.inflate(R.layout.fragment_rate_reduced, container, false);
            unbinder = ButterKnife.bind(this, view);
        }

        setUpImdbRate();
        setUpTmdbRate();
        setUpMoviewsScore();
        return view;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (unbinder!=null) unbinder.unbind();
    }

    private void setUpMoviewsScore(){
        if(ratingsContainer.getMoviews()==null) return;
        Integer moviewsScore = ratingsContainer.getMoviews().intValue();
        circularProgressBar.setProgress(0);
        int animationDurationMSegs = 2500;
        circularProgressBar.setProgressWithAnimation(ratingsContainer.getMoviews().floatValue(), animationDurationMSegs);
        if (moviewsScore>0) {
            textViewMoviewsScore.setText(String.format("%1$d",moviewsScore));
        } else {
            textViewMoviewsScore.setText("N/A");
        }
    }

    private void setAndToggleMetascore(){
        if(ratingsContainer.getMetaScore() != null)
        {
            metascore.setText(String.format("%1$d",ratingsContainer.getMetaScore().intValue()));
        } else {
            metacriticLogo.setVisibility(View.INVISIBLE);
            metascore.setVisibility(View.INVISIBLE);
            maxMetascore.setVisibility(View.INVISIBLE);
        }
    }

    private void setAndToggleRottenTomatoes(){
        if(ratingsContainer.getRottenTomatoes() != null){
            rottenTomatoesRate.setText(String.format("%1$d",ratingsContainer.getRottenTomatoes().intValue()) + "%");
        } else{
            rottenTomatoesLogo.setVisibility(View.INVISIBLE);
            rottenTomatoesRate.setVisibility(View.INVISIBLE);
        }
    }

    private void setUpTmdbRate(){
        if(ratingsContainer.getTmdb() != null){
            tmdbRate.setText(ratingsContainer.getTmdb().toString());
        }
    }

    private void setUpImdbRate(){
        if(ratingsContainer.getImdb() != null){
            imdbRate.setText(ratingsContainer.getImdb().toString());
        }
    }

}
