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
        Integer layout = isGeneral ? R.layout.cell_bookmark_general : R.layout.cell_bookmark_movie_series;
        View  view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String imageUrl = ImageHelper.getImagePathFromFullURL(holder.mItem.getImageURL());
        holder.dateBookmarked.setText("15/06/2016");
        holder.title.setText(holder.mItem.getTitle());
        int imageQuality = 1;
        if(!isGeneral) {
            holder.year.setText(holder.mItem.getYear());
            imageQuality = 3;
        }
        Picasso.with(holder.mView.getContext()).load(ImageHelper.getPosterURL(imageUrl, imageQuality)).fit().centerInside().into(holder.poster);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mView ,holder.mItem);
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
            options.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onListFragmentInteraction(v, mItem);
                }
            });
            dateBookmarked = (TextView) view.findViewById(R.id.dateBoorkmarkMovie);
            year = isGeneral ? null : (TextView) view.findViewById(R.id.yearBookmarkMovie);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
