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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Adapters.TabItemListPagerAdapter;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.ListTmdbController;
import digitalhouse.android.a0317moacns1c_02.Controller.ListUserController;
import digitalhouse.android.a0317moacns1c_02.Controller.MovieController;
import digitalhouse.android.a0317moacns1c_02.Controller.SerieController;
import digitalhouse.android.a0317moacns1c_02.Fragments.ItemListFragment;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.R;


public class ItemTabsActivity extends AppCompatActivity implements ItemListFragment.ItemClickeable {

    @BindView(R.id.pager) protected ViewPager viewPager;
    @BindView(R.id.tab_layout) protected TabLayout tabLayout;
    @BindView(R.id.tabLayoutModes) protected TabLayout tabLayoutModes;
    @BindView(R.id.toolbar_editText) protected EditText searchEditText;
    @BindView(R.id.tab_act_toolbar) protected Toolbar toolbar;

    private ArrayList<ListItem> itemList;
    private List<ItemListFragment> fragments;
    private List<String> titles;
    private Integer loadedCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        tabLayoutModes.addOnTabSelectedListener(new TabModeListener());
        searchEditText.setHint("Search movies...");
        loadMovies();
    }

    private void loadMovies() {
        loadedCounter = 0; // flag de carga
        fragments = new ArrayList<>();
        fragments.add(null);
        fragments.add(null);
        fragments.add(null);
        titles = new ArrayList<>();
        titles.add("Popular");
        titles.add("Latest");
        titles.add("Upcoming");

        //carga de datos
        ListTmdbController.getInstance().getMoviesPopular(new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> itemList) {
                fragments.set(0,ItemListFragment.newInstance(itemList));
                loadedCounter++;
                loadViewPager();
            }
        });

        ListTmdbController.getInstance().getMoviesLatest(new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> itemList) {
                fragments.set(1,ItemListFragment.newInstance(itemList));
                loadedCounter++;
                loadViewPager();
            }
        });

        ListTmdbController.getInstance().getMoviesUpcoming(new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> itemList) {
                fragments.set(2,ItemListFragment.newInstance(itemList));
                loadedCounter++;
                loadViewPager();
            }
        });
    }

    private void loadSeries() {
        loadedCounter = 0; // flag de carga
        fragments = new ArrayList<>();
        fragments.add(null);
        fragments.add(null);
        fragments.add(null);
        titles = new ArrayList<>();
        titles.add("Popular");
        titles.add("Top rated");
        titles.add("Airing today");

        SerieController.getInstance().getPopular(new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> itemList) {
                fragments.set(0,ItemListFragment.newInstance(itemList));
                loadedCounter++;
                loadViewPager();
            }
        });
        SerieController.getInstance().getTopRated(new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> itemList) {
                fragments.set(1,ItemListFragment.newInstance(itemList));
                loadedCounter++;
                loadViewPager();
            }
        });
        SerieController.getInstance().getAiringToday(new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> itemList) {
                fragments.set(2,ItemListFragment.newInstance(itemList));
                loadedCounter++;
                loadViewPager();
            }
        });
    }

    private void loadLists() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        titles.add("Bookmarks");
        titles.add("Favorites");
        ArrayList<ListItem> listItemsBookmarks = ListUserController.getInstance().getBookmarks();
        ArrayList<ListItem> listItemsFavorites = ListUserController.getInstance().getFavorites();
        fragments.add(ItemListFragment.newInstance(listItemsBookmarks));
        fragments.add(ItemListFragment.newInstance(listItemsFavorites));
        loadViewPager();
    }



    private void loadViewPager() {
        if (loadedCounter<fragments.size()) return; // si todavia no se cargaron todos los fragments, cancelar
        PagerAdapter adapter = new TabItemListPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(ListItem listItem) {
        // se define la accion a tomar segun el tipo del listitem (movie o serieDetails)
        if (listItem.getType().equals(ListItem.TYPE_MOVIE)) {
            Intent intent = new Intent(this,MovieDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, listItem.getId());
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (listItem.getType().equals(ListItem.TYPE_SERIE)) {
            Intent intent = new Intent(this, SerieActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(SerieActivity.SERIE_ID_KEY, listItem.getId().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
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
                    intent.putExtra(SearchActivity.SEARCH_ACTION_TAG, tabLayoutModes.getSelectedTabPosition());
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

    // listener del tablayout de modos
    private class TabModeListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    searchEditText.setHint("Search movies...");
                    loadMovies();
                    break;
                case 1:
                    searchEditText.setHint("Search series...");
                    loadSeries();
                    break;
                case 2:
                    loadLists();
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            onTabSelected(tab);
        }
    }

}
