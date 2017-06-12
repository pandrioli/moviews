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
import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.Misc.Company;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.R;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesDetailsTitleFragment extends Fragment {
    @BindView(R.id.imageViewSDBackDrop) protected ImageView backdrop;
    @BindView(R.id.textViewSDTitle) protected TextView title;
    @BindView(R.id.textViewSDGenres) protected TextView genres;
    @BindView(R.id.textViewSDOnTheAir) protected TextView onTheAir;

    private Unbinder unbinder;

    public static final String SERIE_TITLE_KEY = "serieTitle";
    protected Serie serie;

    public SeriesDetailsTitleFragment() {
        // Required empty public constructor
    }

    public static SeriesDetailsTitleFragment newInstance(Serie serie) {
        SeriesDetailsTitleFragment fragment = new SeriesDetailsTitleFragment();
        Bundle args = new Bundle();
        args.putParcelable(SERIE_TITLE_KEY, serie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getArguments() != null){
            serie = getArguments().getParcelable(SERIE_TITLE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series_details_title, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpTextViews();
        setUpImages();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setUpTextViews(){
        title.setText(serie.getName() + " (" + serie.getYear() + ") ");
        genres.setText(GenreController.getInstance().getGenresString(serie.getGenres(), " | "));
        if(!serie.getStatus().equals("Returning Series"))
            onTheAir.setVisibility(View.GONE);
    }

    private void setUpImages(){
        String url = ImageHelper.getPosterURL(serie.getBackdropPath(), 4);
        Picasso.with(getContext()).load(url).fit().centerCrop().into(backdrop);
    }
}
