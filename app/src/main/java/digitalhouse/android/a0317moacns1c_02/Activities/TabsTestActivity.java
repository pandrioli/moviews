package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Adapters.MovieListPageAdapter;
import digitalhouse.android.a0317moacns1c_02.Entities.MovieListItem;
import digitalhouse.android.a0317moacns1c_02.Fragments.MovieListFragment;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.MovieService;


public class TabsTestActivity extends AppCompatActivity implements MovieListFragment.MovieSelectable {

    @BindView(R.id.pager) protected ViewPager viewPager;
    @BindView(R.id.tab_layout) protected TabLayout tabLayout;

    private ArrayList<MovieListItem> movieList;
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

        MovieService.getInstance().obtainPopularMovies(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                movieList = (ArrayList<MovieListItem>) result;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(MovieListFragment.MOVIE_LIST_KEY, movieList);
                bundleList[0] = bundle;
                loadedCounter++;
                loadViewPager();
            }
        });

        MovieService.getInstance().obtainNowPlayingMovies(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                movieList = (ArrayList<MovieListItem>) result;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(MovieListFragment.MOVIE_LIST_KEY, movieList);
                bundleList[1] = bundle;
                loadedCounter++;
                loadViewPager();
            }
        });

        MovieService.getInstance().obtainUpcomingMovies(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                movieList = (ArrayList<MovieListItem>) result;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(MovieListFragment.MOVIE_LIST_KEY, movieList);
                bundleList[2] = bundle;
                loadedCounter++;
                loadViewPager();
            }
        });

    }

    @Override
    public void movieSelected(MovieListItem movieListItem) {
        Intent intent = new Intent(this,MovieDetailsTestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("movieId", movieListItem.getId().toString());
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
}
