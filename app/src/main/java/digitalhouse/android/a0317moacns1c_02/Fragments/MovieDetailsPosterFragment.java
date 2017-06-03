package digitalhouse.android.a0317moacns1c_02.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Model.POJO.Movie.MovieDetails;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.R;


public class MovieDetailsPosterFragment extends Fragment {


    @BindView(R.id.imageViewMDPRate) protected ImageView imageButtonMDPRate;
    @BindView(R.id.imageViewMDPPoster) protected ImageView imageViewMDPPoster;
    @BindView(R.id.imageViewMDPActualRating) protected ImageView imageButtonMDPActualRating;
    @BindView(R.id.textViewMDPMyRate) protected TextView textViewMDPMyRate;
    @BindView(R.id.textViewMDPActualRating) protected TextView textViewMDPActualRating;
    @BindView(R.id.textViewMDPNumberOfRates) protected TextView textViewMDPNumberOfRates;
    @BindView(R.id.textViewMDPMetaScoreRating) protected TextView textViewMDPMetaScoreRating;
    @BindView(R.id.textViewMDPMetaScoreDescription) protected TextView textViewMDPMetaScoreDescription;
    @BindView(R.id.textViewMDPNumberOfCritics) protected TextView textViewMDPNumberOfCritics;

    Unbinder unbinder;

    public MovieDetailsPosterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_details_poster, container, false);

        unbinder = ButterKnife.bind(this, view);

        setUpViews((MovieDetails) getArguments().getParcelable(MovieDetails.tag));

        return view;
    }

    @Override public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setUpViews(MovieDetails movieDetails){
        textViewMDPActualRating.setText(movieDetails.getVote_average().toString());

        //TODO: deshardcoread esto
        textViewMDPNumberOfRates.setText("12342");
        textViewMDPMetaScoreRating.setText("99");
        textViewMDPMetaScoreDescription.setText("Metascore");
        textViewMDPNumberOfCritics.setText("139 críticas");
        textViewMDPMyRate.setText("Calificar");

        String url = ImageHelper.getPosterURL(movieDetails.getPoster_path(), 2);

        Picasso.with(getContext()).load(url).fit().centerInside().into(imageViewMDPPoster);

    }


}
