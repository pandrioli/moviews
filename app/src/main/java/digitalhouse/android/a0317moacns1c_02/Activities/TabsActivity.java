package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Adapters.ListPagerAdapter;
import digitalhouse.android.a0317moacns1c_02.Controller.ObtainerController;
import digitalhouse.android.a0317moacns1c_02.Fragments.ItemListFragment;
import digitalhouse.android.a0317moacns1c_02.Model.General.ListItem;
import digitalhouse.android.a0317moacns1c_02.R;

public class TabsActivity extends AppCompatActivity implements ItemListFragment.ItemClickeable {

    @BindView(R.id.pager) protected ViewPager viewPager;
    @BindView(R.id.tab_layout) protected TabLayout tabLayout;
    @BindView(R.id.toolbar_editText) protected EditText searchEditText;
    @BindView(R.id.tab_act_toolbar) protected Toolbar toolbar;

    private List<Fragment> fragmentList;
    private List<String> titlesLists;

    public static final String SEARCH_TYPE_TAG = "search_type";
    private static SearchActivity.SEARCH_TYPE SEARCH_TYPE;

    public static final String CONTROLLER_TYPE_TAG = "controller_type";
    private ObtainerController obtainerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Intent initializationInformation = getIntent();

        SEARCH_TYPE = (SearchActivity.SEARCH_TYPE) initializationInformation.getSerializableExtra(SEARCH_TYPE_TAG);

        obtainerController = (ObtainerController) initializationInformation.getSerializableExtra(CONTROLLER_TYPE_TAG);



        ButterKnife.bind(this);

        loadViewPager();
    }

    private void loadViewPager(){
        setTabsTitles();
        setTabsAdapter();
        setTabsListener();
    }

    private void setTabsTitles(){
        for(String title: titlesLists){
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
    }

    private void setTabsAdapter(){
        PagerAdapter adapter = new ListPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
    }

    private void setTabsListener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        disableSearchEditText();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                onSearchButtonPressed();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void onSearchButtonPressed(){
        if(readyToSearch()){
            startSearchActivity();
        }
        else
        {
            enableSearchEdiText();
        }
    }

    private boolean readyToSearch(){
        if(searchEditText.getVisibility() == View.VISIBLE &&
                !searchEditText.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }

    private void enableSearchEdiText(){
        searchEditText.setVisibility(View.VISIBLE);
        searchEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void disableSearchEditText(){
        searchEditText.setVisibility(View.GONE);
        searchEditText.setText("");
    }

    private void startSearchActivity(){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(SearchActivity.SEARCH_ACTIVITY_QUERY_TAG, searchEditText.getText().toString());
        intent.putExtra(SearchActivity.SEARCH_ACTION_TAG, SEARCH_TYPE);
        startActivity(intent);
    }

    @Override
    public void onClick(ListItem listItem) {
        switch (SEARCH_TYPE){
            case SERIES:
                startSeriesDetailsActivity(listItem);
                break;
            case MOVIES:
            default:
                startMovieDetailsActivity(listItem);
                break;
        }
    }

    private void startMovieDetailsActivity(ListItem listItem){
        Intent intent = new Intent(this,MovieDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, listItem.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void startSeriesDetailsActivity(ListItem listItem){
        Intent intent = new Intent(this,MovieDetailsActivity.class);
        Bundle bundle = new Bundle();
    }
}
