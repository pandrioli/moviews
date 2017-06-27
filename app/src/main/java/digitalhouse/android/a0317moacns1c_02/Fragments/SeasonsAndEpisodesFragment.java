package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Adapters.EpisodeRecyclerViewAdapter;
import digitalhouse.android.a0317moacns1c_02.Model.Series.EpisodeDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Season;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeasonsAndEpisodesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeasonsAndEpisodesFragment extends Fragment {
    @BindView(R.id.imageViewSeasonPoster) ImageView poster;
    @BindView(R.id.textViewSeasonAirDate) TextView seasonAirDate;
    @BindView(R.id.textViewSeasonOverview) TextView overview;
    @BindView(R.id.textViewSeasonName) TextView title;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imageViewArrow) ImageView arrow;
    @BindView(R.id.recyclerViewEpisodes) RecyclerView recyclerViewEpisodes;

    private OnEpisodeListFragmentInteractionListener mListener;


    private static final String SEASON_KEY = "SEASON";
    private Season season;
    private List<EpisodeDetails> episodes;
    private Unbinder unbinder;


    public SeasonsAndEpisodesFragment() {
        // Required empty public constructor
    }

    public static SeasonsAndEpisodesFragment newInstance(Season season) {
        SeasonsAndEpisodesFragment fragment = new SeasonsAndEpisodesFragment();
        Bundle args = new Bundle();
        args.putSerializable(SEASON_KEY, season);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            season = (Season)getArguments().getSerializable(SEASON_KEY);
            episodes = season.getSeasonDetails().getEpisodes();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seasons_episodes, container, false);
        unbinder = ButterKnife.bind(this, view);
        Glide.with(this).load(season.getPosterUrl(4)).into(poster);
        seasonAirDate.setText(season.getAirDate());
        overview.setText(season.getOverview());
        title.setText(season.getName());
        setUpCollapsingToolbar();
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overview.getVisibility() == View.GONE){
                    overview.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_keyboard_arrow_up_black_36dp);
                } else {
                    overview.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_36dp);
                }
            }
        });

        recyclerViewEpisodes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewEpisodes.setAdapter(new EpisodeRecyclerViewAdapter(episodes, mListener));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setUpCollapsingToolbar(){
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbar.setTitle(season.getName());
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);
    }

    public interface OnEpisodeListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(EpisodeDetails item);
    }

}
