package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.CustomViews.BottomBar;
import digitalhouse.android.a0317moacns1c_02.CustomViews.PageIndicator;
import digitalhouse.android.a0317moacns1c_02.Fragments.OnBoardingFragment;
import digitalhouse.android.a0317moacns1c_02.R;

public class OnBoardingActivity extends AppCompatActivity {
    private static final Integer TOTAL_PAGES = 7;
    @BindView(R.id.viewPagerOnBoarding)
    ViewPager viewPager;
    @BindView(R.id.textViewOnBoardingTitle)
    TextView textViewTitle;
    @BindView(R.id.imageViewOnBoardingBack)
    ImageView imageViewBackground;
    @BindView(R.id.pageIndicatorOnBoarding)
    PageIndicator pageIndicator;
    @BindView(R.id.bottomBarOnBoarding)
    BottomBar bottomBar;
    @BindView(R.id.imageViewOnBoardingLeft)
    ImageView imageViewLeft;
    @BindView(R.id.imageViewOnBoardingRight)
    ImageView imageViewRight;
    @BindView(R.id.buttonStartMoviews)
    Button buttonStartMoviews;
    @BindView(R.id.imageViewOnBoardingLogo)
    ImageView imageViewLogo;

    private List<String> titles;
    private List<Integer> images;
    private List<OnBoardingFragment> fragments;
    private float previousOffset = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        ButterKnife.bind(this);
        setupFragments();
        setupTitlesAndImages();
        setupViews();
        updateViews(0);
    }

    private void setupViews() {
        pageIndicator.setTotalPages(TOTAL_PAGES);
        bottomBar.setOnBoarding();
        OnBoardingAdapter onBoardingAdapter = new OnBoardingAdapter(getSupportFragmentManager());
        viewPager.setAdapter(onBoardingAdapter);
        viewPager.addOnPageChangeListener(new OnBoardingPageChangeListener());
        buttonStartMoviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishOnboarding();
            }
        });
    }


    private void updateViews(Integer page) {
        if (page==0) {
            textViewTitle.setVisibility(View.GONE);
            imageViewLogo.setVisibility(View.VISIBLE);
            imageViewLeft.setVisibility(View.INVISIBLE);
        } else {
            textViewTitle.setVisibility(View.VISIBLE);
            imageViewLogo.setVisibility(View.GONE);
            imageViewLeft.setVisibility(View.VISIBLE);
        }
        if (page == TOTAL_PAGES-1) {
            imageViewRight.setVisibility(View.INVISIBLE);
            buttonStartMoviews.setVisibility(View.VISIBLE);
        }
        else {
            imageViewRight.setVisibility(View.VISIBLE);
            buttonStartMoviews.setVisibility(View.GONE);
        }
        pageIndicator.setPage(page);
        if (page>0 && page<TOTAL_PAGES-1) {
            bottomBar.setVisibility(View.VISIBLE);
            bottomBar.setCurrentIcon(page-1);
        } else bottomBar.setVisibility(View.INVISIBLE);
        textViewTitle.setText(titles.get(page));
        imageViewBackground.setImageResource(images.get(page));
    }

    private void setupTitlesAndImages() {
        titles = new ArrayList<>();
        titles.add("");
        titles.add("Home");
        titles.add("Top Rated");
        titles.add("Search");
        titles.add("Bookmarks");
        titles.add("Profile");
        titles.add("");
        images = new ArrayList<>();
        images.add(R.drawable.background_welcome_10);
        images.add(R.drawable.background_welcome_2);
        images.add(R.drawable.background_welcome_3);
        images.add(R.drawable.background_welcome_4);
        images.add(R.drawable.background_welcome_5);
        images.add(R.drawable.background_welcome_6);
        images.add(R.drawable.background_welcome_7);
    }

    private void setupFragments() {
        fragments = new ArrayList<>();
        for (int i=0; i<TOTAL_PAGES; i++) {
            fragments.add(OnBoardingFragment.newInstance(i));
        }
    }

    private void finishOnboarding() {
        // Get the shared preferences
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        // Set onboarding_complete to true
        preferences.edit().putBoolean("onboarding_complete",true).apply();

        // Launch the main Activity, called MainActivity
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

        // Close the OnboardingActivity
        finish();
    }

    private class OnBoardingPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Float alpha = Math.abs(0.5f-positionOffset)/0.5f;
            imageViewLogo.setAlpha(alpha);
            textViewTitle.setAlpha(alpha);
            imageViewBackground.setAlpha((float)Math.sqrt(alpha)*.8f);
            Integer direction = positionOffset>previousOffset?1:0;
            previousOffset=positionOffset;
            if ((positionOffset>0.5f && direction==1) || (positionOffset<0.5 && direction==0))
                updateViews(position+direction);
        }

        @Override
        public void onPageSelected(int position) {
            updateViews(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    private class OnBoardingAdapter extends FragmentStatePagerAdapter {

        public OnBoardingAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return TOTAL_PAGES;
        }
    }
}
