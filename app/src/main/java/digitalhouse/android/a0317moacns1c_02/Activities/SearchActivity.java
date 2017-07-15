package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Adapters.SearchPagerAdapter;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.MovieSearchRequest;
import digitalhouse.android.a0317moacns1c_02.Fragments.ItemListFragment;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.PersonSearchRequest;
import digitalhouse.android.a0317moacns1c_02.Model.Requests.SerieSearchRequest;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Controller.SearchController;

public class SearchActivity extends AppCompatActivity implements ItemListFragment.ItemClickeable {

    public static final String SEARCH_ACTIVITY_QUERY_TAG = "query";
    public static final String SEARCH_ACTION_TAG = "search_action";

    public enum SEARCH_TYPE implements Serializable{
        MOVIES, SERIES, ACTORS
    }

    private static Integer currentTab;
    private SearchPagerAdapter adapter;
    private Handler handler;

    @BindView(R.id.toolbar_editText) protected EditText searchEditText;
    @BindView(R.id.tab_layout) protected TabLayout tabLayout;
    @BindView(R.id.view_pager) protected ViewPager viewPager;
    @BindView(R.id.clear_text) protected TextView clearText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        clearText.setVisibility(View.GONE);

        Intent intent = getIntent();
        currentTab = 0;
        if(intent.hasExtra(SEARCH_ACTION_TAG))
        {
            Integer default_value = 0;
            currentTab = intent.getIntExtra(SEARCH_ACTION_TAG, default_value);
        }

        if(intent.hasExtra(SEARCH_ACTIVITY_QUERY_TAG)){
            String query = intent.getStringExtra(SEARCH_ACTIVITY_QUERY_TAG);
            searchEditText.setText(query);
            onButtonSearchPressed(query);
        }

        tabLayout.addTab(tabLayout.newTab().setText("MOVIES"));
        tabLayout.addTab(tabLayout.newTab().setText("SERIES"));
        tabLayout.addTab(tabLayout.newTab().setText("PEOPLE"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        loadViewPager();

        setupAutoSearch();

    }


    private void loadViewPager() {
        adapter = new SearchPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                currentTab = viewPager.getCurrentItem();

                /*
                switch (currentTab){
                    case 1:
                        searchEditText.setHint("Search series...");
                        break;
                    case 2:
                        searchEditText.setHint("Search people...");
                        break;
                    case 0:
                    default:
                        searchEditText.setHint("Search movies...");
                        break;
                }
                 */
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

    private void setupAutoSearch() {
        //listener del clearText

        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.setText("");
            }
        });


        //handler que ejecuta la busqueda
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                onButtonSearchPressed(searchEditText.getText().toString());
            }
        };
        //listener para cuando el texto es cambiado
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>0) clearText.setVisibility(View.VISIBLE);
                else clearText.setVisibility(View.GONE);
                if (s.toString().length()>3) {
                    //borra cualquier peticion anterior
                    handler.removeMessages(0);
                    //envia peticion de busqueda con un retardo de un segundo
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        });
    }


    //Mas adelate voy a agregar todas las opciones de busqueda, por eso los diferentes requests
    private void onButtonSearchPressed(String query){
        // buscar movies
        MovieSearchRequest movieSearchRequest = new MovieSearchRequest(query);
        SearchController.getInstance().searchMovies(movieSearchRequest, new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> movieList) {
                if (SearchActivity.this.isDestroyed()) return;
                adapter.getFragments().set(0, ItemListFragment.newInstance(movieList));
                adapter.notifyDataSetChanged();
            }
        });
        // buscar series
        SerieSearchRequest serieSearchRequest = new SerieSearchRequest(query);
        SearchController.getInstance().searchSeries(serieSearchRequest, new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> serieList) {
                if (SearchActivity.this.isDestroyed()) return;
                adapter.getFragments().set(1, ItemListFragment.newInstance(serieList));
                adapter.notifyDataSetChanged();
            }
        });
        // buscar gente
        PersonSearchRequest personSearchRequest = new PersonSearchRequest(query);
        SearchController.getInstance().searchPeople(personSearchRequest, new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> personList) {
                if (SearchActivity.this.isDestroyed()) return;
                adapter.getFragments().set(2, ItemListFragment.newInstance(personList));
                adapter.notifyDataSetChanged();
            }
        });

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
    public void onClick(ListItem listItem, ImageView imageView) {
        if (listItem.getType().equals(ListItem.TYPE_MOVIE)) {
            Bundle bundle = new Bundle();
            bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, listItem.getId());
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (listItem.getType().equals(ListItem.TYPE_SERIE)) {

        }
        if (listItem.getType().equals(ListItem.TYPE_PERSON)) {
            Bundle bundle = new Bundle();
            bundle.putInt(PersonDetailsActivity.PERSON_ID_KEY, listItem.getId());
            Intent intent = new Intent(this, PersonDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
