package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import digitalhouse.android.a0317moacns1c_02.Fragments.SeasonsAndEpisodesFragment;
import digitalhouse.android.a0317moacns1c_02.Model.Series.EpisodeDetails;
import digitalhouse.android.a0317moacns1c_02.R;

import java.util.List;

public class EpisodeRecyclerViewAdapter extends RecyclerView.Adapter<EpisodeRecyclerViewAdapter.ViewHolder> {

    private final List<EpisodeDetails> mValues;
    private final SeasonsAndEpisodesFragment.OnEpisodeListFragmentInteractionListener mListener;

    public EpisodeRecyclerViewAdapter(List<EpisodeDetails> items, SeasonsAndEpisodesFragment.OnEpisodeListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_episode_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.setUpViews();

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         final View mView;
         final TextView title;
         final TextView airDate;
         final TextView overview;
         final ImageView backdrop;
         final TextView share;
         final TextView save;
         final ImageView like;
         final TextView likeCount;
         EpisodeDetails mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) mView.findViewById(R.id.textViewEpisodeTitle);
            airDate = (TextView) mView.findViewById(R.id.textViewEpisodeAirDate);
            overview = (TextView) mView.findViewById(R.id.textViewEpisodeOverview);
            backdrop = (ImageView) mView.findViewById(R.id.imageViewEpisode);
            share = (TextView) mView.findViewById(R.id.textViewEpisodeShare);
            save = (TextView) mView.findViewById(R.id.textViewEpisodeSave);
            like = (ImageView) mView.findViewById(R.id.imageViewEpisodeLike);
            likeCount = (TextView) mView.findViewById(R.id.textViewEpisodeLikeCount);
        }

        public void setUpViews(){
            title.setText(mItem.getName());
            airDate.setText(mItem.getAirDate());
            overview.setText(mItem.getOverview());
            likeCount.setText("143");
            Glide.with(mView).load(mItem.getStillUrl(2)).into(backdrop);
        }
    }
}
