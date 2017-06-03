package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieDetails;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsTitleFragment extends Fragment {

    @BindView(R.id.textViewMovieDetailsDescription) TextView textViewMovieDetailsDescription;
    @BindView(R.id.textViewMovieDetailsDuration) TextView textViewMovieDetailsDuration;
    @BindView(R.id.textViewMovieDetailsGenres) TextView textViewMovieDetailsGenres;
    @BindView(R.id.textViewMovieDetailsTitle) TextView textViewMovieDetailsTitle;
    @BindView(R.id.textViewMovieDetailsYear) TextView textViewMovieDetailsYear;
    @BindView(R.id.textViewMovieDetailsMovieRatings) TextView textViewMovieDetailsMovieRatings;
    private Unbinder unbinder;


    public MovieDetailsTitleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_details_title, container, false);
        unbinder = ButterKnife.bind(this, view);

        setUpViews((MovieDetails) getArguments().getParcelable(MovieDetails.tag));

        return view;
    }

    private void setUpViews(MovieDetails movieDetails){
        textViewMovieDetailsDescription.setText(movieDetails.getOverview());
        textViewMovieDetailsDuration.setText(movieDetails.getRuntimeString());
        textViewMovieDetailsGenres.setText(GenreController.getInstance().getGenresString(movieDetails.getGenres(), " | "));
        textViewMovieDetailsTitle.setText(movieDetails.getTitle());
        textViewMovieDetailsYear.setText(movieDetails.getYear());
        //TODO: deshardcodear
        textViewMovieDetailsMovieRatings.setText("PG-13");
    }

    @Override public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

}
