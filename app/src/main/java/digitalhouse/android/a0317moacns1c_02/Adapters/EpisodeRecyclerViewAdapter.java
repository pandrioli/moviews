package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import digitalhouse.android.a0317moacns1c_02.Fragments.EpisodeListFragment.OnListFragmentInteractionListener;
import digitalhouse.android.a0317moacns1c_02.Model.Series.ListItems.EpisodeListContent;
import digitalhouse.android.a0317moacns1c_02.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link EpisodeListContent.EpisodeItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EpisodeRecyclerViewAdapter extends RecyclerView.Adapter<EpisodeRecyclerViewAdapter.ViewHolder> {

    private final List<EpisodeListContent.EpisodeItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public EpisodeRecyclerViewAdapter(List<EpisodeListContent.EpisodeItem> items, OnListFragmentInteractionListener listener) {
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
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

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
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public EpisodeListContent.EpisodeItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //da error esto!!!
            mIdView = null;
            mContentView = null;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
