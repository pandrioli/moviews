package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.General.Network;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieDetails;
import digitalhouse.android.a0317moacns1c_02.R;

public class SerieDetailsFragment extends Fragment {
    public static final String SERIE_DETAILS_KEY = "serieDetails";
    public static final String RATE_FRAGMENT_SD_KEY = "rateFragment";

    @BindView(R.id.imageViewSDBackDrop) protected ImageView backdrop;
    @BindView(R.id.textViewSDTitle) protected TextView title;
    @BindView(R.id.textViewSDGenres) protected TextView genres;
    @BindView(R.id.textViewSDOnTheAir) protected TextView onTheAir;
    @BindView(R.id.textViewSDNetworkCont) protected TextView network;
    @BindView(R.id.textViewSDLaunchCont) protected TextView launchDate;
    @BindView(R.id.textViewSDSeasonsCont) protected TextView numberOfSeasons;
    @BindView(R.id.textViewSDChaptersCont) protected TextView numberOfChapters;
    @BindView(R.id.imageViewPoster) protected ImageView poster;
    @BindView(R.id.textViewSDSummary) protected TextView textViewSummary;

    private Unbinder unbinder;
    private SerieDetails serieDetails;


    public SerieDetailsFragment() {
        // Required empty public constructor
    }

    public static SerieDetailsFragment newInstance(SerieDetails serieDetails,
                                                   RateFragment rateFragment) {
        SerieDetailsFragment fragment = new SerieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(SERIE_DETAILS_KEY, serieDetails);
        args.putSerializable(RATE_FRAGMENT_SD_KEY, rateFragment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            serieDetails = getArguments().getParcelable(SERIE_DETAILS_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serie_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        startRatingsFragment();
        setUpSummary();
        setUpImages();
        setUpTitleAndGenres();
        setUpInfo();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setUpTitleAndGenres(){
        title.setText(serieDetails.getName() + " (" + serieDetails.getYear() + ") ");
        genres.setText(GenreController.getInstance()
                .getGenresString(serieDetails.getGenres(), " | "));
        if(!serieDetails.getStatus().equals("Returning Series"))
            onTheAir.setVisibility(View.GONE);
    }

    private void setUpImages(){
        String url = ImageHelper.getPosterURL(serieDetails.getBackdropPath(), 4);
        Picasso.with(getContext()).load(url).fit().centerCrop().into(backdrop);
    }

    private void setUpInfo(){
        String networks = Network.getNetworksInString(serieDetails.getNetworks(), ", ");
        network.setText(networks);
        launchDate.setText(serieDetails.getFirstAirDate());
        numberOfSeasons.setText(serieDetails.getNumberOfSeasons().toString());
        numberOfChapters.setText(serieDetails.getNumberOfEpisodes().toString());
        String url = ImageHelper.getPosterURL(serieDetails.getPosterPath(), 2);
        Glide.with(this).load(url).into(poster);
    }

    private void setUpSummary(){
        textViewSummary.setText(serieDetails.getOverview());
    }

    private void startRatingsFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.framelayoutSDRatings,
                        (RateFragment) getArguments().getSerializable(RATE_FRAGMENT_SD_KEY))
                .commit();
    }

}
