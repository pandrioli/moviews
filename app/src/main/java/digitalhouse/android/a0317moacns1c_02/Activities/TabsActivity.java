package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Adapters.MovieListPageAdapter;
import digitalhouse.android.a0317moacns1c_02.Controller.MovieController;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieResultsItem;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieListFragment;
import digitalhouse.android.a0317moacns1c_02.R;


public class TabsActivity extends AppCompatActivity implements MovieListFragment.MovieClickeable {

    @BindView(R.id.pager) protected ViewPager viewPager;
    @BindView(R.id.tab_layout) protected TabLayout tabLayout;
    @BindView(R.id.toolbar_editText) protected EditText searchEditText;
    @BindView(R.id.tab_act_toolbar) protected Toolbar toolbar;

    private ArrayList<MovieResultsItem> movieList;
    private Bundle[] bundleList;
    private Integer loadedCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        setContentView(R.layout.activity_tabs_test);

        ButterKnife.bind(this);

        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("Now Playing"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        bundleList = new Bundle[tabLayout.getTabCount()];
        loadedCounter = 0;

        MovieController.getInstance().getPopular(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                movieList = (ArrayList<MovieResultsItem>) result;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(MovieListFragment.MOVIE_LIST_KEY, movieList);
                bundleList[0] = bundle;
                loadedCounter++;
                loadViewPager();
            }
        });

        MovieController.getInstance().getNowPlaying(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                movieList = (ArrayList<MovieResultsItem>) result;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(MovieListFragment.MOVIE_LIST_KEY, movieList);
                bundleList[1] = bundle;
                loadedCounter++;
                loadViewPager();
            }
        });

        MovieController.getInstance().getUpcoming(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                movieList = (ArrayList<MovieResultsItem>) result;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(MovieListFragment.MOVIE_LIST_KEY, movieList);
                bundleList[2] = bundle;
                loadedCounter++;
                loadViewPager();
            }
        });

        setSupportActionBar(toolbar);

    }

    @Override
    public void onClick(MovieResultsItem movieResultsItem) {
        Intent intent = new Intent(this,MovieDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, movieResultsItem.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void loadViewPager() {
        if (loadedCounter<tabLayout.getTabCount()) return;
        PagerAdapter adapter = new MovieListPageAdapter(getSupportFragmentManager(), bundleList, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);

        return true;
    }

    @Override
    public void onBackPressed(){
        if(searchEditText.getVisibility() == View.VISIBLE)
        {
            searchEditText.setVisibility(View.GONE);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume(){
        searchEditText.setVisibility(View.GONE);
        searchEditText.setText("");
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                if(searchEditText.getVisibility() == View.VISIBLE && !searchEditText.getText().toString().isEmpty()){
                    Intent intent = new Intent(this, SearchActivity.class);
                    intent.putExtra(SearchActivity.SEARCH_ACTIVITY_QUERY_TAG, searchEditText.getText().toString());
                    intent.putExtra(SearchActivity.SEARCH_ACTION_TAG,SearchActivity.SEARCH_MOVIES);
                    startActivity(intent);
                }
                else
                {
                    searchEditText.setVisibility(View.VISIBLE);
                    searchEditText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
