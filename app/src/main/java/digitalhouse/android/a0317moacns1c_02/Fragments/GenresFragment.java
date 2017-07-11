package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenresFragment extends Fragment {
    public static final String MOVIES_MODE = "movies";
    public static final String SERIES_MODE = "series";
    public static final String MODE_KEY = "mode";
    private List<Genre> selectedGenres;
    private List<Genre> genres;
    private View view;
    private LinearLayout layoutLabels;
    private LinearLayout layoutCheckboxes;
    private String mode;
    private GenreSelectionListener listenerActivity;

    public static GenresFragment newInstance(String mode) {
        Bundle bundle = new Bundle();
        bundle.putString(MODE_KEY, mode);
        GenresFragment genresFragment = new GenresFragment();
        genresFragment.setArguments(bundle);
        return genresFragment;
    }

    public GenresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerActivity = (GenreSelectionListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_genres, container, false);
        mode = getArguments().getString(MODE_KEY);
        setupGenreList();
        setupViews();
        return view;
    }

    private void setupGenreList() {
        selectedGenres = new ArrayList<>();
        if (mode.equals(MOVIES_MODE)) {
            selectedGenres.addAll(GenreController.getInstance().getSelectedMovieGenres());
            genres = GenreController.getInstance().getGenreListMovies();
        }
        if (mode.equals(SERIES_MODE)) {
            selectedGenres.addAll(GenreController.getInstance().getSelectedSerieGenres());
            genres = GenreController.getInstance().getGenreListSeries();
        }
    }

    private void setupViews() {
        layoutLabels = (LinearLayout)view.findViewById(R.id.linearLayoutGenresLabels);
        layoutCheckboxes = (LinearLayout)view.findViewById(R.id.linearLayoutGenresCheckboxes);
        layoutLabels.removeAllViews();
        layoutCheckboxes.removeAllViews();
        for (Genre genre : genres) {
            TextView genreLabel = new Label(getContext());
            genreLabel.setOnClickListener(new GenreClickListener());
            genreLabel.setText(genre.getName());
            layoutLabels.addView(genreLabel);
            CheckBox genreCheckBox = new GenreCheckBox(getContext());
            genreCheckBox.setOnClickListener(new GenreClickListener());
            layoutCheckboxes.addView(genreCheckBox);
            if (selectedGenres.contains(genre)) genreCheckBox.setChecked(true);
        }

        //listeners buttons

        Button search = (Button)view.findViewById(R.id.buttonGenresSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button clear = (Button)view.findViewById(R.id.buttonGenresAll);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGenres = new ArrayList<>();
                listenerActivity.onSelectionUpdated(selectedGenres);
                setupViews();
            }
        });
    }

    private void finish() {
        listenerActivity.onFinishSelection(selectedGenres);
        //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


    private int getPixels(int dps) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }

    private class GenreClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Integer genreIndex = ((LinearLayout)v.getParent()).indexOfChild(v);
            CheckBox checkBox = (CheckBox)layoutCheckboxes.getChildAt(genreIndex);
            if (v instanceof CheckBox) checkBox.setChecked(!checkBox.isChecked());
            if (checkBox.isChecked()) {
                checkBox.setChecked(false);
                selectedGenres.remove(genres.get(genreIndex));
            } else {
                checkBox.setChecked(true);
                selectedGenres.add(genres.get(genreIndex));
            }
            listenerActivity.onSelectionUpdated(selectedGenres);
        }
    }

    private class Label extends android.support.v7.widget.AppCompatTextView {
        public Label(Context context) {
            super(context);
            setTextSize(20);
            setHeight(getPixels(30));
            setTextColor(getResources().getColor(R.color.primary_text));
        }
    }

    private class GenreCheckBox extends android.support.v7.widget.AppCompatCheckBox {
        public GenreCheckBox(Context context) {
            super(context);
            setText("");
            setHeight(getPixels(30));
            //setClickable(false);
        }
    }

    public interface GenreSelectionListener {
        void onFinishSelection(List<Genre> selectedGenres);
        void onSelectionUpdated(List<Genre> selectedGenres);
    }

}
