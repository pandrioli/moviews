package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Controller.ListUserController;
import digitalhouse.android.a0317moacns1c_02.Controller.ShareController;
import digitalhouse.android.a0317moacns1c_02.CustomViews.BottomSheetBookmark;
import digitalhouse.android.a0317moacns1c_02.Fragments.BookmarkMovieSeriesFragment;
import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SerieDetails;
import digitalhouse.android.a0317moacns1c_02.R;

public class BookmarkActivity extends AppCompatActivity implements BookmarkMovieSeriesFragment.OnListFragmentInteractionListener, Serializable {

    public static final String moreOptionsTag = "more-options-bookmark";
    private String actualTab = "0";
    private BottomSheetBookmark bottomSheetBookmark;
    private BookmarkMovieSeriesFragment bookmarkMovieFragment;
    private BookmarkMovieSeriesFragment bookmarkSerieFragment;
    private BookmarkMovieSeriesFragment bookmarkGeneralFragment;
    private ListItem actualItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        ButterKnife.bind(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setUpViewPager();
        startFragments();

        bottomSheetBookmark = BottomSheetBookmark.getInstance(new BottomSheetBookmark.OnBottomSheetBookmarkListener() {
            @Override
            public void share() {
                startShareFragment();
            }

            @Override
            public void unsave() {
                ListUserController.getInstance().removeFromBookmarks(actualItem);
                bottomSheetBookmark.dismiss();
                refresh();
            }

            @Override
            public void markAsUnseen() {
                //TODO: implementar mark as unseen
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setUpViewPager(){
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                actualTab = String.valueOf(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if(getIntent().getAction() != null){
            int position = Integer.parseInt(getIntent().getAction());
            mViewPager.setCurrentItem(position);
        }
    }

    private void startShareFragment() {
        Intent sendIntent = ShareController.getInstance().obtainShareIntent(actualItem);
        startActivity(sendIntent);
        bottomSheetBookmark.dismiss();
    }


    public void startFragments(){
        bookmarkMovieFragment = BookmarkMovieSeriesFragment.newInstance(ListUserController.getInstance().getMoviesBookmarks(), false);
        bookmarkSerieFragment = BookmarkMovieSeriesFragment.newInstance(ListUserController.getInstance().getSeriesBookmarks(), false);
        bookmarkGeneralFragment = BookmarkMovieSeriesFragment.newInstance(ListUserController.getInstance().getBookmarks(), true);
    }

    public void refresh() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setAction(actualTab);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookmark, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(View view, ListItem item) {
        actualItem = item;
        if(moreOptionsTag.equals(view.getTag())){
            bottomSheetBookmark.show(getSupportFragmentManager(), "Custom Bottom Sheet");
        } else {
            startActivityDetails(item, view);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        AnimationHelper.stopLoader(this);
    }

    private void startActivityDetails(ListItem item, View view){
        Class startingClass = null;
        AnimationHelper.startLoader(this);
        Bundle bundle = new Bundle();
        ImageView imageView = (ImageView)view.findViewById(R.id.imageBookmarkMovie);
        Bundle transitionBundle = AnimationHelper.getTransitionBundle(this, imageView, "poster");
        if (item.getType().equals(ListItem.TYPE_MOVIE)) {
            startingClass = MovieDetailsActivity.class;
            bundle.putInt(MovieDetailsActivity.MOVIE_ID_KEY, item.getId());
        } else if (item.getType().equals(ListItem.TYPE_SERIE)) {
            startingClass = SerieActivity.class;
            bundle.putString(SerieActivity.SERIE_ID_KEY, item.getId().toString());
        }
        Intent intent = new Intent(this, startingClass);
        intent.putExtras(bundle);
        startActivity(intent, transitionBundle);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bookmark, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 1:
                    return bookmarkMovieFragment;
                case 2:
                    return bookmarkSerieFragment;
                default:
                case 0:
                    return bookmarkGeneralFragment;
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ALL BOOKMARKS";
                case 1:
                    return "MOVIES";
                case 2:
                    return "SERIES";
            }
            return null;
        }
    }
}
