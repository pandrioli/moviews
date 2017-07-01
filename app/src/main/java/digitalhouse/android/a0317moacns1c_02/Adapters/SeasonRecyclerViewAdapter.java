package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.SerieController;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Season;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SeasonDetails;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by Gregorio Martin on 30/6/2017.
 */

public class SeasonRecyclerViewAdapter extends RecyclerView.Adapter<SeasonRecyclerViewAdapter.ViewHolder> {
    private List<Season> seasons;
    private String serieId;

    public SeasonRecyclerViewAdapter(Integer numberOfSeasons, String serieId){
        seasons = Arrays.asList(new Season[numberOfSeasons]);
        this.serieId = serieId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_seasons_episodes, parent, false);
        return new SeasonRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(seasons.get(position) == null){
            holder.showLoading();
            SerieController.getInstance().getSeasonDetails(serieId, position, new ResultListener<SeasonDetails>() {
                @Override
                public void finish(SeasonDetails result) {
                    Season mItem = new Season();
                    mItem.setSeasonDetails(result);
                    seasons.set(holder.getAdapterPosition(), mItem);
                    holder.mItem = mItem;
                    holder.showViews();
                }
            });
        } else {
            holder.mItem = seasons.get(position);
            holder.showViews();
        }

    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        ImageView poster;
        TextView seasonAirDate;
        TextView overview;
        CardView cardViewOverview;
        TextView title;
        CollapsingToolbarLayout collapsingToolbar;
        ImageView arrow;
        RecyclerView recyclerViewEpisodes;
        AppBarLayout appBarLayout;
        NestedScrollView nestedScrollView;
        LottieAnimationView loaderAnim;
        Season mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            poster = (ImageView) view.findViewById(R.id.imageViewSeasonPoster);
            seasonAirDate = (TextView) view.findViewById(R.id.textViewSeasonAirDate);
            overview = (TextView) view.findViewById(R.id.textViewSeasonOverview);
            cardViewOverview = (CardView) view.findViewById(R.id.cardViewSeasonOverview);
            title = (TextView) view.findViewById(R.id.textViewSeasonName);
            collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
            arrow = (ImageView) view.findViewById(R.id.imageViewArrow);
            recyclerViewEpisodes = (RecyclerView) view.findViewById(R.id.recyclerViewEpisodes);
            appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
            nestedScrollView = (NestedScrollView) view.findViewById(R.id.scrolling_container);
            loaderAnim = (LottieAnimationView) view.findViewById(R.id.loaderAnimSeasons);

        }

        void showLoading(){
            loaderAnim.playAnimation();
            loaderAnim.setAnimation("loader.json");
            appBarLayout.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.GONE);
        }


        private void showViews(){
            appBarLayout.setVisibility(View.VISIBLE);
            nestedScrollView.setVisibility(View.VISIBLE);
            setUpTitle();
            setUpEpisodes();
            setUpOverview();
            loaderAnim.cancelAnimation();
            loaderAnim.setVisibility(View.GONE);
        }

        private void setUpTitle(){
            Glide.with(mView.getContext()).load(mItem.getPosterUrl(3)).into(poster);
            seasonAirDate.setText(mItem.getAirDate());
            title.setText(mItem.getName());
            collapsingToolbar.setTitle(mItem.getName());
        }

        private void setUpEpisodes(){
            recyclerViewEpisodes.setLayoutManager(new LinearLayoutManager(mView.getContext()));
            recyclerViewEpisodes.setAdapter(new EpisodeRecyclerViewAdapter(mItem.getSeasonDetails().getEpisodes(), null));
        }

        private void setUpOverview(){
            if(mItem.getOverview() != null && !mItem.getOverview().isEmpty()){
                overview.setText(mItem.getOverview());
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

    }
}
