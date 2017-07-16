package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Adapters.EpisodeRecyclerViewAdapter;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.SerieController;
import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.Toaster;
import digitalhouse.android.a0317moacns1c_02.Model.Series.EpisodeDetails;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Season;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SeasonDetails;
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
    @BindView(R.id.cardViewSeasonOverview) CardView cardViewOverview;
    @BindView(R.id.textViewSeasonName) TextView title;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imageViewArrow) ImageView arrow;
    @BindView(R.id.recyclerViewEpisodes) RecyclerView recyclerViewEpisodes;
    @BindView(R.id.appbar) AppBarLayout appBarLayout;
    @BindView(R.id.scrolling_container) NestedScrollView nestedScrollView;
    @BindView(R.id.textViewSeasonsNoConnection) TextView textViewNoConnection;

    private View mView;

    private OnEpisodeInteractionListener mListener;


    private static final String SEASON_KEY = "SEASON";
    private static final String SEASON_NUMBER_KEY = "seasonNumber";
    private static final String SERIE_ID_KEY = "serieIdKey";
    private String serieId;
    private Integer seasonNumber;
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

    public static SeasonsAndEpisodesFragment newInstance(String serieId, Integer seasonNumber) {
        SeasonsAndEpisodesFragment fragment = new SeasonsAndEpisodesFragment();
        Bundle args = new Bundle();
        args.putString(SERIE_ID_KEY, serieId);
        args.putInt(SEASON_NUMBER_KEY, seasonNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //season = (Season)getArguments().getSerializable(SEASON_KEY);
            //episodes = season.getSeasonDetails().getEpisodes();
            serieId = getArguments().getString(SERIE_ID_KEY);
            seasonNumber = getArguments().getInt(SEASON_NUMBER_KEY);
        }
        mListener = new OnEpisodeInteractionListener() {
            @Override
            public void onEpisodeLiked(EpisodeDetails episode) {
                //TODO: llamar al controller para sumar un like
            }

            @Override
            public void onEpisodeBookmarked(EpisodeDetails episode) {
                //TODO: llamar al controller para guardar el episodio
            }

            @Override
            public void onEpisodeShared(EpisodeDetails episode) {
                //TODO: compartir el episodio
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_seasons_episodes, container, false);
        unbinder = ButterKnife.bind(this, mView);
        AnimationHelper.startLoaderInView(getActivity(), mView);
        SerieController.getInstance().getSeasonDetails(serieId, seasonNumber, new ResultListener<SeasonDetails>() {
            @Override
            public void finish(SeasonDetails result) {
                    if (result!=null) {
                        season = new Season();
                        season.setSeasonDetails(result);
                        episodes = result.getEpisodes();
                        toggleViews();
                    } else {
                        //no se por que a veces textViewNoConnection es null
                        if (textViewNoConnection !=null) textViewNoConnection.setVisibility(View.VISIBLE);
                    }
            }
        });
        return mView;
    }

    private void finishFragment(){
        Toaster.getInstance().toast("Sorry. We have no info for this series.");
        getActivity().getFragmentManager().popBackStack();
    }

    private void toggleViews(){
        // no se por que a veces textViewNoConnection es null
        if (textViewNoConnection !=null) textViewNoConnection.setVisibility(View.INVISIBLE);
        appBarLayout.setVisibility(View.VISIBLE);
        nestedScrollView.setVisibility(View.VISIBLE);
        setUpTitle();
        setUpCollapsingToolbar();
        setUpEpisodes();
        setUpOverview();
    }

    private void setUpTitle(){
        Glide.with(this).load(season.getPosterUrl(3)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                AnimationHelper.stopLoaderInView(getActivity(), mView);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                AnimationHelper.stopLoaderInView(getActivity(), mView);
                return false;
            }
        }).into(poster);
        seasonAirDate.setText(season.getAirDate());
        title.setText(season.getName());
    }

    private void setUpEpisodes(){
        recyclerViewEpisodes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewEpisodes.setAdapter(new EpisodeRecyclerViewAdapter(episodes, mListener));
    }

    private void setUpOverview(){
        if(season.getOverview() != null && !season.getOverview().isEmpty()){
            overview.setText(season.getOverview());
            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overview.setVisibility(View.VISIBLE);
                    arrow.setVisibility(View.GONE);
                    arrow.setOnClickListener(null);
                }
            });
        } else {
            cardViewOverview.setVisibility(View.GONE);
        }
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


    public interface OnEpisodeInteractionListener {
        void onEpisodeLiked(EpisodeDetails episode);
        void onEpisodeBookmarked(EpisodeDetails episode);
        void onEpisodeShared(EpisodeDetails episode);
    }

}