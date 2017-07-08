package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Controller.ListUserController;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Mappers.ImageListMapper;
import digitalhouse.android.a0317moacns1c_02.Model.General.Network;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Serie;
import digitalhouse.android.a0317moacns1c_02.R;

public class SerieDetailsFragment extends Fragment implements ImageListFragment.ImageClickeable {
    public static final String SERIE_KEY = "serie";

    private Boolean favorited;
    private Boolean bookmarked;

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
    @BindView(R.id.share) ImageView share;
    @BindView(R.id.like) ImageView like;
    @BindView(R.id.bookmark) ImageView bookmark;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private Unbinder unbinder;
    private Serie serie;


    public SerieDetailsFragment() {
        // Required empty public constructor
    }

    public static SerieDetailsFragment newInstance(Serie serie) {
        SerieDetailsFragment fragment = new SerieDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(SERIE_KEY, serie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            serie = (Serie)getArguments().getSerializable(SERIE_KEY);
        }
        setHasOptionsMenu(true);
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
        startMediaListFragment();
        startCastListFragment();
        setUpCollapsingToolbar();
        setUpButtons();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onOptionsItemSelected(item);
                return true;
        }
        return false;
    }

    private void setUpButtons(){
        favorited = ListUserController.getInstance().isSerieInFavorites(serie.getSerieDetails().getId())
        ;
        bookmarked = ListUserController.getInstance().isSerieInBookmarks(serie.getSerieDetails().getId());
        drawButtons();
        int color = ContextCompat.getColor(getContext(), R.color.accent);
        bookmark.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        like.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        share.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookmarked) {
                    bookmarked = false;
                    ListUserController.getInstance().removeSerieFromBookmarks(serie.getSerieDetails().getId());
                    drawButtons();
                } else {
                    bookmarked = true;
                    ListUserController.getInstance().addSerieToBookmarks(serie);
                    drawButtons();
                }
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favorited) {
                    favorited=false;
                    ListUserController.getInstance().removeSerieFromFavorites(serie.getSerieDetails().getId());
                    drawButtons();
                } else {
                    favorited=true;
                    ListUserController.getInstance().addSerieToFavorites(serie);
                    drawButtons();
                }
            }
        });
    }

    private void drawButtons() {
        if (favorited) like.setImageResource(R.drawable.fav_black_xhdpi);
        else like.setImageResource(R.drawable.fav_border_xhdpi);

        if (bookmarked) bookmark.setImageResource(R.drawable.bookmark_black_xhdpi);
        else bookmark.setImageResource(R.drawable.bookmark_border_xhdpi);
    }

    private void setUpTitleAndGenres(){
        title.setText(serie.getName() + " (" + serie.getYear() + ") ");
        genres.setText(GenreController.getInstance()
                .getGenresString(serie.getGenres(), " | "));
        if(!serie.getStatus().equals("Returning Series"))
            onTheAir.setVisibility(View.GONE);
    }

    private void setUpImages(){
        String url = ImageHelper.getPosterURL(serie.getBackDropPath(), 4);
        Picasso.with(getContext()).load(url).fit().centerCrop().into(backdrop);
    }

    private void setUpInfo(){
        String networks = Network.getNetworksInString(serie.getNetworks(), ", ");
        network.setText(networks);
        launchDate.setText(serie.getFirstAirDate());
        numberOfSeasons.setText(serie.getNumberOfSeasons());
        numberOfChapters.setText(serie.getNumberOfEpisodes());
        String url = ImageHelper.getPosterURL(serie.getPosterPath(), 2);
        Glide.with(this).load(url).into(poster);
    }

    private void setUpSummary(){
        textViewSummary.setText(serie.getOverview());
    }

    private void setUpCollapsingToolbar(){
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbar.setTitle(serie.getName());
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);
        if (toolbar != null)
        {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
            {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void startRatingsFragment(){
        RateFragment rateFragment = RateFragment.newInstance(serie.getRatingsContainer());
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.framelayoutSDRatings, rateFragment)
                .commit();
    }

    private void startMediaListFragment(){
        ArrayList<String> URLs;
        URLs = serie.getVideoContainer().getYouTubeThumbnailURLs();
        URLs.addAll(serie.getImagesContainer().getURLsList());
        MediaListFragment mediaListFragment = MediaListFragment.newInstance(URLs);
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayoutSDMedia, mediaListFragment)
                .commit();
    }

    private void startCastListFragment(){
        ImageListFragment imageListFragment = ImageListFragment.newInstance(
                ImageListMapper.map(serie.getCredits()), "Casting");
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayoutSDCasting, imageListFragment)
                .commit();
    }

    @Override
    public void onClick(ImageListItem imageListItem, String title, Integer index) {
        if (title.equals("Casting")) {
            ImageListFragment.ImageClickeable act = (ImageListFragment.ImageClickeable) getActivity();
            act.onClick(imageListItem, title, index);
        }
    }
}
