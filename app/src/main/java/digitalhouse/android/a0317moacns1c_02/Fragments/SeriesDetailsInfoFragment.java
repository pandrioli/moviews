package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Model.General.Network;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeriesDetailsInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeriesDetailsInfoFragment extends Fragment {
    private static final String SERIE_INFO_KEY = "serieInfo";
    protected Serie serie;
    private Unbinder unbinder;

    @BindView(R.id.textViewSDNetworkCont) protected TextView network;
    @BindView(R.id.textViewSDLaunchCont) protected TextView launchDate;
    @BindView(R.id.textViewSDSeasonsCont) protected TextView numberOfSeasons;
    @BindView(R.id.textViewSDChaptersCont) protected TextView numberOfChapters;


    public SeriesDetailsInfoFragment() {
        // Required empty public constructor
    }

    public static SeriesDetailsInfoFragment newInstance(Serie serie) {
        SeriesDetailsInfoFragment fragment = new SeriesDetailsInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(SERIE_INFO_KEY, serie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            serie = getArguments().getParcelable(SERIE_INFO_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series_details_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        String networks = Network.getNetworksInString(serie.getNetworks(), ", ");
        network.setText(networks);
        launchDate.setText(serie.getFirstAirDate());
        numberOfSeasons.setText(serie.getNumberOfSeasons().toString());
        numberOfChapters.setText(serie.getNumberOfEpisodes().toString());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
