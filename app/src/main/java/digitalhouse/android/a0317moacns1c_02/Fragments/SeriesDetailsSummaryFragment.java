package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImagesContainer;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesDetailsSummaryFragment extends Fragment {
    public static final String SUMMARY_KEY = "summary";
    private Unbinder unbinder;
    private String summary;
    @BindView(R.id.textViewSDSummary) protected TextView textViewSummary;

    public SeriesDetailsSummaryFragment() {
        // Required empty public constructor
    }

    public static SeriesDetailsSummaryFragment newInstance(String summary){
        SeriesDetailsSummaryFragment fragment = new SeriesDetailsSummaryFragment();
        Bundle args = new Bundle();
        args.putString(SUMMARY_KEY, summary);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        if(getArguments() != null){
            summary = getArguments().getString(SUMMARY_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series_details_summary, container, false);
        unbinder = ButterKnife.bind(this, view);
        textViewSummary.setText(summary);
        return  view;
    }

}
