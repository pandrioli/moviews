package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Adapters.TabItemListPagerAdapter;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.ListTmdbController;
import digitalhouse.android.a0317moacns1c_02.Fragments.ItemListFragment;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.R;

public class TopsActivity extends AppCompatActivity implements ItemListFragment.ItemClickeable {

    @BindView(R.id.tabLayoutTops)
    TabLayout tabLayout;
    @BindView(R.id.viewPagerTops)
    ViewPager viewPager;
    @BindView(R.id.editTextTopsFrom)
    EditText editTextFrom;
    @BindView(R.id.editTextTopsTo)
    EditText editTextTo;


    private List<ItemListFragment> fragments;
    private List<String> titles;
    private List<Integer> genres;
    private List<ListItem> movieResults;
    private List<ListItem> serieResults;
    private TabItemListPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops);
        ButterKnife.bind(this);
        genres = new ArrayList<>();
        titles = new ArrayList<>();
        titles.add("TOP MOVIES");
        titles.add("TOP SERIES");
        fragments = new ArrayList<>();
        fragments.add(ItemListFragment.newInstance(new ArrayList<ListItem>()));
        fragments.add(ItemListFragment.newInstance(new ArrayList<ListItem>()));
        adapter = new TabItemListPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setupEditTexts();
        searchMoviesAndSeries();
    }

    private void setupEditTexts() {
        editTextFrom.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextFrom.setSelectAllOnFocus(true);
        editTextFrom.setText("1950");
        editTextTo.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextTo.setSelectAllOnFocus(true);
        Calendar calendar = Calendar.getInstance();
        editTextTo.setText(String.format("%1d", calendar.get(Calendar.YEAR)));
        editTextFrom.addTextChangedListener(new TextWatcher() {
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
        });
        editTextTo.addTextChangedListener(new TextWatcher() {
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
        });
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void searchMoviesAndSeries(){
        String from = editTextFrom.getText().toString();
        String to = editTextTo.getText().toString();
        ListTmdbController.getInstance(this).getTopMovies(from, to, genres, new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> result) {
                movieResults = result;
                sortResults(movieResults);
                adapter.getFragmentList().set(0, ItemListFragment.newInstance(result));
                adapter.notifyDataSetChanged();
            }
        });
        ListTmdbController.getInstance(this).getTopSeries(from, to, genres, new ResultListener<ArrayList<ListItem>>() {
            @Override
            public void finish(ArrayList<ListItem> result) {
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

    @Override
    public void onClick(ListItem listItem) {

    }
}
