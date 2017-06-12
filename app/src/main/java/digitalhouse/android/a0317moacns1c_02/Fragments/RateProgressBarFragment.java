package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RateProgressBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RateProgressBarFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RATE_SOURCE = "sourceRate";
    private static final String RATING = "rating";

    private String rateSource;
    private Integer rating;
    private Unbinder unbinder;

    @BindView(R.id.textViewFRPB_RateSource) protected TextView textViewRateSource;
    @BindView(R.id.textViewFRPB_Rating) protected TextView textViewRating;
    @BindView(R.id.progressBarFRPB) protected ProgressBar progressBar;

    public RateProgressBarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param rateSource De que empresa, página o API proviene el puntaje
     * @param rating el puntaje
     * @return A new instance of fragment RateProgressBarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RateProgressBarFragment newInstance(String rateSource, Integer rating) {
        RateProgressBarFragment fragment = new RateProgressBarFragment();
        Bundle args = new Bundle();
        args.putString(RATE_SOURCE, rateSource);
        args.putInt(RATING, rating);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rateSource = getArguments().getString(RATE_SOURCE);
            rating = getArguments().getInt(RATING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rate_progress_bar, container, false);

        unbinder = ButterKnife.bind(this, view);

        progressBar.setProgress(rating);
        textViewRateSource.setText(rateSource);
        textViewRating.setText(rating.toString());



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
