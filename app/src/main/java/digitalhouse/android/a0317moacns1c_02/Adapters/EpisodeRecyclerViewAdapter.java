package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import digitalhouse.android.a0317moacns1c_02.Fragments.SeasonsAndEpisodesFragment;
import digitalhouse.android.a0317moacns1c_02.Model.Series.EpisodeDetails;
import digitalhouse.android.a0317moacns1c_02.R;

import java.util.List;

public class EpisodeRecyclerViewAdapter extends RecyclerView.Adapter<EpisodeRecyclerViewAdapter.ViewHolder> {

    private final List<EpisodeDetails> mValues;
    private final SeasonsAndEpisodesFragment.OnEpisodeInteractionListener mListener;

    public EpisodeRecyclerViewAdapter(List<EpisodeDetails> items, SeasonsAndEpisodesFragment.OnEpisodeInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_episode_list, parent, false);
        ImageView like = (ImageView) view.findViewById(R.id.imageViewEpisodeLike);
        ImageView boorkmark = (ImageView) view.findViewById(R.id.imageViewEpisodeSave);
        ImageView share = (ImageView) view.findViewById(R.id.imageViewEpisodeShare);
        boorkmark.setColorFilter(Color.parseColor("#009688"), PorterDuff.Mode.SRC_IN);
        like.setColorFilter(Color.parseColor("#009688"), PorterDuff.Mode.SRC_IN);
        share.setColorFilter(Color.parseColor("#009688"), PorterDuff.Mode.SRC_IN);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.setUpViews();

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.imageViewEpisodeShare:
                        mListener.onEpisodeShared(holder.mItem);
                        break;
                    case R.id.imageViewEpisodeSave:
                        mListener.onEpisodeBookmarked(holder.mItem);
                        break;
                    case R.id.imageViewEpisodeLike:
                        mListener.onEpisodeLiked(holder.mItem);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView title;
        final TextView airDate;
        final TextView overview;
        final ImageView backdrop;
        final ImageView share;
        final ImageView save;
        final ImageView like;
        EpisodeDetails mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) mView.findViewById(R.id.textViewEpisodeTitle);
            airDate = (TextView) mView.findViewById(R.id.textViewEpisodeAirDate);
            overview = (TextView) mView.findViewById(R.id.textViewEpisodeOverview);
            backdrop = (ImageView) mView.findViewById(R.id.imageViewEpisode);
            share = (ImageView) mView.findViewById(R.id.imageViewEpisodeShare);
            save = (ImageView) mView.findViewById(R.id.imageViewEpisodeSave);
            like = (ImageView) mView.findViewById(R.id.imageViewEpisodeLike);
        }

        void setUpViews(){

            title.setText(mItem.getName());
            if(mItem.getFormattedAirDate() == null) airDate.setVisibility(View.GONE);
            else airDate.setText(mItem.getFormattedAirDate());
            overview.setText(mItem.getOverview());
            String stillUrl = mItem.getStillUrl(1);
            if(stillUrl == null) backdrop.setVisibility(View.GONE);
            else Picasso.with(mView.getContext()).load(mItem.getStillUrl(1)).into(backdrop);
        }
    }
}