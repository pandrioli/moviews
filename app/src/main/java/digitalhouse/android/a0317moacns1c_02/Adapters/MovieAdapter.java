package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import digitalhouse.android.a0317moacns1c_02.Entities.Movie;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by Pablo on 20/05/2017.
 */

public class MovieAdapter extends BaseAdapter {
    private List<MovieListItem> movieList;
    private Context context;

    public MovieAdapter(List<MovieListItem> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public MovieListItem getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieListItem movie = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.cell_movie_list, parent, false);
        ((TextView)convertView.findViewById(R.id.textViewMovieListTitle)).setText(movie.getTitle());
        ((TextView)convertView.findViewById(R.id.textViewMovieListYear)).setText(movie.getYear());
        ((TextView)convertView.findViewById(R.id.textViewMovieListRating)).setText(movie.getRating());
        return convertView;
    }
}
