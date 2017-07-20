package digitalhouse.android.a0317moacns1c_02.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.CustomViews.BottomBar;
import digitalhouse.android.a0317moacns1c_02.CustomViews.PageIndicator;
import digitalhouse.android.a0317moacns1c_02.R;

public class OnBoardingActivity extends AppCompatActivity {
    @BindView(R.id.viewPagerOnBoarding)
    ViewPager viewPager;
    @BindView(R.id.textViewOnBoardingTitle)
    TextView textViewTitle;
    @BindView(R.id.imageViewOnBoardingBack)
    ImageView imageViewHeader;
    @BindView(R.id.pageIndicatorOnBoarding)
    PageIndicator pageIndicator;
    @BindView(R.id.bottomBarOnBoarding)
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        ButterKnife.bind(this);
        pageIndicator.setTotalPages(5);
        bottomBar.setOnBoarding(true);
        bottomBar.setVisibility(View.INVISIBLE);
        OnBoardingAdapter onBoardingAdapter = new OnBoardingAdapter(getSupportFragmentManager());
        viewPager.setAdapter(onBoardingAdapter);
        bottomBar.setCurrentIcon(0);
        pageIndicator.setPage(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position>0) {
                    bottomBar.setVisibility(View.VISIBLE);
                    bottomBar.setCurrentIcon(position-1);
                } else bottomBar.setVisibility(View.INVISIBLE);
                pageIndicator.setPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private class OnBoardingAdapter extends FragmentStatePagerAdapter {

        public OnBoardingAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new Fragment();
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
