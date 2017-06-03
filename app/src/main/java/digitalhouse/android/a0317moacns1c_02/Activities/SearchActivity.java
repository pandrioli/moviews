package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Adapters.SearchPagerAdapter;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsItem;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.MovieSearchRequest;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieListFragment;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Controller.SearchController;

public class SearchActivity extends AppCompatActivity implements MovieListFragment.MovieClickeable {

    public static final String SEARCH_ACTIVITY_QUERY_TAG = "query";
    public static final String SEARCH_ACTION_TAG = "search_action";
    public static final Integer SEARCH_MOVIES = 0;
    public static final Integer SEARCH_SERIES = 1;
    public static final Integer SEARCH_ACTORS = 2;
    private static Integer currentTab;
    private SearchPagerAdapter adapter;

    @BindView(R.id.toolbar_editText) protected EditText searchEditText;
    @BindView(R.id.search_act_toolbar) protected Toolbar toolbar;
    @BindView(R.id.tab_layout) protected TabLayout tabLayout;
    @BindView(R.id.view_pager) protected ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent.hasExtra(SEARCH_ACTION_TAG))
        {
            currentTab = intent.getIntExtra(SEARCH_ACTION_TAG, 0);
        }

        if(intent.hasExtra(SEARCH_ACTIVITY_QUERY_TAG)){
            String query = intent.getStringExtra(SEARCH_ACTIVITY_QUERY_TAG);
            searchEditText.setText(query);
            onButtonSearchPressed(query);
        }

        tabLayout.addTab(tabLayout.newTab().setText("Movies"));
        tabLayout.addTab(tabLayout.newTab().setText("Series"));
        tabLayout.addTab(tabLayout.newTab().setText("Actors"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        setSupportActionBar(toolbar);

        loadViewPager();
    }


    private void loadViewPager() {
        adapter = new SearchPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                currentTab = viewPager.getCurrentItem();
                switch (currentTab){
                    case 1:
                        searchEditText.setHint("Search Series...");
                        break;
                    case 2:
                        searchEditText.setHint("Search Actors...");
                        break;
                    case 0:
                    default:
                        searchEditText.setHint("Search Movies...");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setCurrentItem(currentTab);

    }

    //Mas adelate voy a agregar todas las opciones de busqueda, por eso los diferentes requests
    private void onButtonSearchPressed(String query){
        MovieSearchRequest movieSearchRequest = new MovieSearchRequest(query);
        SearchController.getInstance().searchMovies(movieSearchRequest, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                MovieListFragment movieListFragment = new MovieListFragment();
                Bundle bundle = new Bundle();
                ArrayList<Parcelable> movieList = (ArrayList<Parcelable>) result;
                bundle.putParcelableArrayList(MovieListFragment.MOVIE_LIST_KEY, movieList);
                movieListFragment.setArguments(bundle);
                adapter.getFragments().set(0, movieListFragment);
                adapter.notifyDataSetChanged();
            }
        });


        //adapter.getMovieFragment().realoadList(movieSearchRequest);
        //TODO: crear request de actores y cambiar el null de abajo por el request
        //adapter.getActorsFragment().realoadList(null);
        //TODO: crear request de series y cambiar el null de abajo por el request
        //adapter.getSeriesFragment().realoadList(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                if(!TextUtils.isEmpty(searchEditText.getText()))
                    onButtonSearchPressed(searchEditText.getText().toString());
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        return true;
    }

    @Override
    public void onClick(MovieResultsItem movieResultsItem) {
        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, movieResultsItem.getId());
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
