package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Adapters.TabItemListPagerAdapter;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;
import digitalhouse.android.a0317moacns1c_02.Controller.ListTmdbController;
import digitalhouse.android.a0317moacns1c_02.Fragments.GenresFragment;
import digitalhouse.android.a0317moacns1c_02.Fragments.ItemListFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.R;

public class TopsActivity extends AppCompatActivity implements ItemListFragment.ItemClickeable, GenresFragment.GenreSelectionListener {

    @BindView(R.id.tabLayoutTops)
    TabLayout tabLayout;
    @BindView(R.id.viewPagerTops)
    ViewPager viewPager;
    @BindView(R.id.editTextTopsFrom)
    EditText editTextFrom;
    @BindView(R.id.editTextTopsTo)
    EditText editTextTo;
    @BindView(R.id.textViewTopsGenres)
    TextView textViewGenres;
    @BindView(R.id.imageViewTopsGenreBurger)
    ImageView imageViewBurger;
    @BindView(R.id.frameLayoutGenreSelection)
    FrameLayout frameLayoutGenreSelection;

    private List<Genre> movieGenresSelected;
    private List<Genre> serieGenresSelected;
    private List<ListItem> movieResults;
    private List<ListItem> serieResults;
    private TabItemListPagerAdapter adapter;
    private GenresFragment genresFragment;
    private Boolean selectingGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops);
        ButterKnife.bind(this);
        selectingGenres = false;
        movieGenresSelected = GenreController.getInstance().getSelectedMovieGenres();
        serieGenresSelected = GenreController.getInstance().getSelectedSerieGenres();
        setupViews();
        updateGenresTextView(movieGenresSelected);
        searchMoviesAndSeries();
    }

    private void setupViews() {
        //Viewpager
        List<String> titles = new ArrayList<>();
        titles.add("TOP MOVIES");
        titles.add("TOP SERIES");
        List<ItemListFragment> fragments = new ArrayList<>();
        fragments.add(ItemListFragment.newInstance(new ArrayList<ListItem>()));
        fragments.add(ItemListFragment.newInstance(new ArrayList<ListItem>()));
        adapter = new TabItemListPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //EditTexts
        editTextFrom.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextFrom.setSelectAllOnFocus(true);
        editTextFrom.setText(ListTmdbController.getInstance(this).getTopsYearFrom());
        editTextTo.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextTo.setSelectAllOnFocus(true);
        editTextTo.setText(ListTmdbController.getInstance(this).getTopsYearTo());

        //Listeners
        editTextFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) editTextFrom.setText("");
                else if (editTextFrom.getText().length()<4) {
                    editTextFrom.setText(ListTmdbController.getInstance(TopsActivity.this).getTopsYearFrom());
                    searchMoviesAndSeries();
                }
            }
        });
        editTextTo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) editTextTo.setText("");
                else if (editTextTo.getText().length()<4) {
                    editTextTo.setText(ListTmdbController.getInstance(TopsActivity.this).getTopsYearTo());
                    searchMoviesAndSeries();
                }
            }
        });
        editTextFrom.addTextChangedListener(new YearWatcher());
        editTextTo.addTextChangedListener(new YearWatcher());
        imageViewBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                editTextFrom.clearFocus();
                editTextTo.clearFocus();
                startGenreSelection();
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateGenresTextView(tab.getPosition()==0?movieGenresSelected:serieGenresSelected);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void startGenreSelection() {
        selectingGenres = true;
        String mode = tabLayout.getSelectedTabPosition() == 0 ? Genre.TYPE_MOVIES : Genre.TYPE_SERIES;
        genresFragment = GenresFragment.newInstance(mode);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutGenreSelection, genresFragment).commit();
        tabLayout.setVisibility(View.INVISIBLE);
        AnimationHelper.zoomAndFade(frameLayoutGenreSelection,true,0f,1f,0f,400);
    }


    private void searchMoviesAndSeries(){
        String from = editTextFrom.getText().toString();
        String to = editTextTo.getText().toString();
        ListTmdbController.getInstance(this).setTopsYearFrom(from);
        ListTmdbController.getInstance(this).setTopsYearTo(to);
        ListTmdbController.getInstance(this).getTopMovies(from, to, movieGenresSelected, new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> result) {
                if (TopsActivity.this.isDestroyed()) return;
                movieResults = result;
                sortResults(movieResults);
                adapter.getFragmentList().set(0, ItemListFragment.newInstance(result));
                adapter.notifyDataSetChanged();
            }
        });
        ListTmdbController.getInstance(this).getTopSeries(from, to, serieGenresSelected, new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> result) {
                if (TopsActivity.this.isDestroyed()) return;
                serieResults = result;
                sortResults(serieResults);
                adapter.getFragmentList().set(1, ItemListFragment.newInstance(result));
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void sortResults(List<ListItem> results) {
        Collections.sort(results, new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                return o2.getRating().compareTo(o1.getRating());
            }
        });
    }

    private void saveGenresLists(List<Genre> selectedGenres) {
        if (tabLayout.getSelectedTabPosition() == 0) {
            movieGenresSelected = selectedGenres;
            GenreController.getInstance().setSelectedMovieGenres(selectedGenres);
        } else {
            serieGenresSelected = selectedGenres;
            GenreController.getInstance().setSelectedSerieGenres(selectedGenres);
        }
    }

    private void updateGenresTextView(List<Genre> selectedGenres) {
        String genresText = "All genres";
        if (selectedGenres.size()>0) {
            genresText = GenreController.getInstance().getGenresString(selectedGenres, " + ");
        }
        textViewGenres.setText(genresText);
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void unloadGenreSelectionFragment() {
        AnimationHelper.zoomAndFade(frameLayoutGenreSelection,false,.3f,1f,0f,300);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().remove(genresFragment).commit();
            }
        };
        new Timer().schedule(timerTask, 300);
    }

    @Override
    public void onBackPressed() {
        if (selectingGenres) {
            onFinishSelection(tabLayout.getSelectedTabPosition()==0?movieGenresSelected:serieGenresSelected);
        }
        else finish();
    }

    @Override
    public void onFinishSelection(List<Genre> selectedGenres) {
        selectingGenres = false;
        unloadGenreSelectionFragment();
        tabLayout.setVisibility(View.VISIBLE);
        saveGenresLists(selectedGenres);
        updateGenresTextView(selectedGenres);
        searchMoviesAndSeries();
    }

    @Override
    public void onSelectionUpdated(List<Genre> selectedGenres) {
        updateGenresTextView(selectedGenres);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AnimationHelper.stopLoader(this);
    }

    @Override
    public void onClick(ListItem listItem, ImageView imageView) {

        AnimationHelper.startLoader(this);
        Bundle transitionBundle = AnimationHelper.getTransitionBundle(this, imageView, "poster");

        if (listItem.getType().equals(ListItem.TYPE_MOVIE)) {

            Intent intent = new Intent(this,MovieDetailsActivity.class);

            Bundle bundle = new Bundle();

            bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, listItem.getId());

            intent.putExtras(bundle);

            startActivity(intent, transitionBundle);

        }

        if (listItem.getType().equals(ListItem.TYPE_SERIE)) {

            Intent intent = new Intent(this, SerieActivity.class);

            Bundle bundle = new Bundle();

            bundle.putString(SerieActivity.SERIE_ID_KEY, listItem.getId().toString());

            intent.putExtras(bundle);

            startActivity(intent, transitionBundle);

        }


    }

    private class YearWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        @Override
        public void afterTextChanged(Editable s) {
            if (s.length()==4) {
                searchMoviesAndSeries();
                viewPager.requestFocus();
                hideKeyboard();
            }
        }

    }
}
