package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.General.Network;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieDetails;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeriesDetailsInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeriesDetailsInfoFragment extends Fragment {
    private static final String SERIE_INFO_KEY = "serieInfo";
    protected SerieDetails serieDetails;
    private Unbinder unbinder;

    @BindView(R.id.textViewSDNetworkCont) protected TextView network;
    @BindView(R.id.textViewSDLaunchCont) protected TextView launchDate;
    @BindView(R.id.textViewSDSeasonsCont) protected TextView numberOfSeasons;
    @BindView(R.id.textViewSDChaptersCont) protected TextView numberOfChapters;
    @BindView(R.id.imageViewPoster) protected ImageView poster;


    public SeriesDetailsInfoFragment() {
        // Required empty public constructor
    }

    public static SeriesDetailsInfoFragment newInstance(SerieDetails serieDetails) {
        SeriesDetailsInfoFragment fragment = new SeriesDetailsInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(SERIE_INFO_KEY, serieDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            serieDetails = getArguments().getParcelable(SERIE_INFO_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series_details_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        String networks = Network.getNetworksInString(serieDetails.getNetworks(), ", ");
        network.setText(networks);
        launchDate.setText(serieDetails.getFirstAirDate());
        numberOfSeasons.setText(serieDetails.getNumberOfSeasons().toString());
        numberOfChapters.setText(serieDetails.getNumberOfEpisodes().toString());
        String url = ImageHelper.getPosterURL(serieDetails.getPosterPath(), 2);
        Glide.with(this).load(url).into(poster);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
