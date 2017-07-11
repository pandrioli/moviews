package digitalhouse.android.a0317moacns1c_02.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Fragments.GenresFragment;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.R;

public class GenresSelectionTestActivity extends AppCompatActivity implements GenresFragment.GenreSelectionListener {

    @BindView(R.id.textViewGenreSelectionTest)
    TextView textViewGenresSelected;

    private List<Genre> selectedGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres_selection_test);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutGenreSelectionTest, GenresFragment.newInstance(new ArrayList<Genre>(), GenresFragment.MOVIES_MODE))
                .commit();
    }


    @Override
    public void onGenresSelected(List<Genre> selectedGenres) {
         String genres = GenreController.getInstance().getGenresString(selectedGenres, " / ");
        textViewGenresSelected.setText(genres);
    }
}
