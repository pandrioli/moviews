package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import digitalhouse.android.a0317moacns1c_02.Fragments.BookmarkMovieSeriesFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.R;

import java.util.List;

public class BookmarkRecyclerViewAdapter extends RecyclerView.Adapter<BookmarkRecyclerViewAdapter.ViewHolder> {

    private final boolean isGeneral;
    private final List<ListItem> mValues;
    private final BookmarkMovieSeriesFragment.OnListFragmentInteractionListener mListener;

    public BookmarkRecyclerViewAdapter(List<ListItem> items,
                                       BookmarkMovieSeriesFragment.OnListFragmentInteractionListener listener,
                                       boolean isGeneral) {
        this.isGeneral = isGeneral;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(this.isGeneral){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cell_bookmark_general, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cell_bookmark_movie_series, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String imageUrl = ImageHelper.getImagePathFromFullURL(holder.mItem.getImageURL());
        Picasso.with(holder.mView.getContext()).load(ImageHelper.getPosterURL(imageUrl, 3)).fit().centerInside().into(holder.poster);
        if(!isGeneral){
            holder.title.setText(holder.mItem.getTitle());
        }
        holder.year.setText(holder.mItem.getYear());
        holder.dateBookmarked.setText("15/06/2016");

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
        public final ImageView poster;
        public final TextView title;
        public final ImageView options;
        public final TextView year;
        public final TextView dateBookmarked;
        public ListItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            poster = (ImageView) view.findViewById(R.id.imageBookmarkMovie);
            title = (TextView) view.findViewById(R.id.titleBookmarkMovie);
            options = (ImageView) view.findViewById(R.id.moreOptionsBookmark);
            dateBookmarked = (TextView) view.findViewById(R.id.dateBoorkmarkMovie);
            if(!isGeneral){
                year = (TextView) view.findViewById(R.id.yearBookmarkMovie);
            } else {
                year = null;
            }
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
