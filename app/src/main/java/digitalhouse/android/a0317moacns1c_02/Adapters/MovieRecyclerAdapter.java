package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by dh3 on 22/05/17.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private List<MovieListItem> movieList;
    private View.OnClickListener clickListener;

    public MovieRecyclerAdapter(Context context, List<MovieListItem> movieList, View.OnClickListener clickListener) {
        this.context = context;
        this.movieList = movieList;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View cellView = inflater.inflate(R.layout.cell_movie_list, parent, false);
        cellView.setOnClickListener(this);
        MovieViewHolder movieViewHolder = new MovieViewHolder(cellView);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieListItem movieListItem = movieList.get(position);
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        movieViewHolder.view.setTag(position);
        movieViewHolder.title.setText(movieListItem.getTitle());
        movieViewHolder.year.setText(movieListItem.getYear());
        movieViewHolder.genres.setText(movieListItem.getGenres());
        movieViewHolder.rating.setText(movieListItem.getRating());
        Picasso.with(context).load(movieListItem.getPosterURL()).fit().centerInside().into(movieViewHolder.poster);
    }

    public void onClick(View v) {
        clickListener.onClick(v);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView title;
        private TextView year;
        private TextView genres;
        private TextView rating;
        private ImageView poster;
        public MovieViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.textViewMovieListTitle);
            year = (TextView) view.findViewById(R.id.textViewMovieListYear);
            genres = (TextView) view.findViewById(R.id.textViewMovieListGenres);
            rating = (TextView) view.findViewById(R.id.textViewMovieListRating);
            poster = (ImageView) view.findViewById(R.id.imageViewMovieListPoster);
        }
    }
}
