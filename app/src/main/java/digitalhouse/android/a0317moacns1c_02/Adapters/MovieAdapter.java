package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
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

    static class ViewHolder {
        private TextView title;
        private TextView year;
        private TextView genres;
        private TextView rating;
        private ImageView poster;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        MovieListItem movie = getItem(position);
        // Acá implementé el ViewHolder pattern, no lo vimos en DH pero es lo que se usa
        // para optimizar los ListView, sólo infla cuando la celda no está creada y guarda
        // todos los componentes a actualizar dentro del mismo view de la celda
        // (como un objeto ViewHolder mediante el método setTag)
        // De esta manera se evita ejecutar todos
        // los findViewById cada vez que actualiza los datos.
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.cell_movie_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView)convertView.findViewById(R.id.textViewMovieListTitle);
            viewHolder.year = (TextView)convertView.findViewById(R.id.textViewMovieListYear);
            viewHolder.genres = (TextView)convertView.findViewById(R.id.textViewMovieListGenres);
            viewHolder.rating = (TextView)convertView.findViewById(R.id.textViewMovieListRating);
            viewHolder.poster = (ImageView)convertView.findViewById(R.id.imageViewMovieListPoster);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.title.setText(movie.getTitle());
        viewHolder.year.setText(movie.getYear());
        viewHolder.genres.setText(movie.getGenres());
        viewHolder.rating.setText(movie.getRating());
        // Acá usé una lib (Picasso) que agregué en el Gradle, no sólo es fácil de usar
        // sino que está bien optimizada, por ejemplo cachea las imagenes del adapter para
        // no estar descargando a cada rato
        Picasso.with(context).load(movie.getPosterURL()).fit().centerInside().into(viewHolder.poster);
        return convertView;
    }
}
