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
    public static final String GENRES_LIST_KEY = "serieGenres";
    private ArrayList<Genre> selectedGenres;
    private List<Genre> genres;
    private View view;
    private LinearLayout layoutLabels;
    private LinearLayout layoutCheckboxes;


    public static GenresFragment newInstance(ArrayList<Genre> genres, String mode) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(GENRES_LIST_KEY, genres);
        bundle.putString(MODE_KEY, mode);
        GenresFragment genresFragment = new GenresFragment();
        genresFragment.setArguments(bundle);
        return genresFragment;
    }

    public GenresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_genres, container, false);
        selectedGenres = (ArrayList) getArguments().getSerializable(GENRES_LIST_KEY);
        String mode = getArguments().getString(MODE_KEY);
        setupGenreList(mode);
        setupViews();
        return view;
    }

    private void setupGenreList(String mode) {
        Integer start = 0;
        Integer end = 0;
        List<Genre> allGenres = GenreController.getInstance().getGenreList();
        if (mode.equals(MOVIES_MODE)) {
            genres = GenreController.getInstance().getGenreList();
            start=0;
            end=18;
        }
        if (mode.equals(SERIES_MODE)) {
            start=19;
            end=34;
        }
        genres = new ArrayList<>();
        for (int i=start; i<end; i++) {
            genres.add(allGenres.get(i));
        }
    }

    private void setupViews() {
        layoutLabels = (LinearLayout)view.findViewById(R.id.linearLayoutGenresLabels);
        layoutCheckboxes = (LinearLayout)view.findViewById(R.id.linearLayoutGenresCheckboxes);
        for (Genre genre : genres) {
            TextView genreLabel = new Label(getContext());
            genreLabel.setOnClickListener(new GenreClickListener());
            genreLabel.setText(genre.getName());
            layoutLabels.addView(genreLabel);
            CheckBox genreCheckBox = new GenreCheckBox(getContext());
            layoutCheckboxes.addView(genreCheckBox);
        }
        Button search = (Button)view.findViewById(R.id.buttonGenresSelection);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GenreSelectionListener)getActivity()).onGenresSelected(selectedGenres);
            }
        });
    }

    private int getPixels(int dps) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }

    private void addGenre(View v) {
        Integer genreIndex = layoutLabels.indexOfChild(v);
        v.setTag("selected");
        CheckBox checkBox = (CheckBox)layoutCheckboxes.getChildAt(genreIndex);
        checkBox.setChecked(true);
        selectedGenres.add(genres.get(genreIndex));
    }

    private void removeGenre(View v) {
        Integer genreIndex = layoutLabels.indexOfChild(v);
        v.setTag(null);
        CheckBox checkBox = (CheckBox)layoutCheckboxes.getChildAt(genreIndex);
        checkBox.setChecked(false);
        selectedGenres.remove(genres.get(genreIndex));
    }

    private class GenreClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getTag()==null) {
                addGenre(v);
            } else {
                removeGenre(v);
            }
        }
    }

    private class Label extends TextView {
        public Label(Context context) {
            super(context);
            setTextSize(20);
            setHeight(getPixels(30));
            setTextColor(getResources().getColor(R.color.primary_text));
        }
    }

    private class GenreCheckBox extends CheckBox {
        public GenreCheckBox(Context context) {
            super(context);
            setText("");
            setHeight(getPixels(30));
        }
    }

    public interface GenreSelectionListener {
        void onGenresSelected(List<Genre> selectedGenres);
    }

}
