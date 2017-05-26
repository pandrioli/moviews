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
import digitalhouse.android.a0317moacns1c_02.Entities.API.MovieDetails.MovieDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.ConfigurationService;


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

        setUpViews((MovieDetailsAPI) getArguments().getSerializable(MovieDetailsAPI.movieDetailsTag));

        return view;
    }

    @Override public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setUpViews(MovieDetailsAPI movieDetailsAPI){
        textViewMDPActualRating.setText(movieDetailsAPI.getVote_average().toString());
        textViewMDPNumberOfRates.setText(movieDetailsAPI.getVote_count().toString());

        //TODO: deshardcoread esto
        textViewMDPMetaScoreRating.setText("99");
        textViewMDPMetaScoreDescription.setText("Metascore");
        textViewMDPNumberOfCritics.setText("139 críticas");
        textViewMDPMyRate.setText("Calificar");

        String urlBase = ConfigurationService.getInstance().getImagesBaseURL();
        String urlImagenTam = ConfigurationService.getInstance().getPosterSizes().get(2);
        String urlFinal = urlBase + urlImagenTam + movieDetailsAPI.getBackdrop_path();

        Picasso.with(getContext()).load(urlFinal).fit().centerInside().into(imageViewMDPPoster);



    }


}
