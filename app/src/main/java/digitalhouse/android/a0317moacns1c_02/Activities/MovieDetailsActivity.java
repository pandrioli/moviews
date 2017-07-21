package digitalhouse.android.a0317moacns1c_02.Activities;import android.content.Intent;import android.graphics.PorterDuff;import android.graphics.drawable.Drawable;import android.support.annotation.Nullable;import android.support.design.widget.CollapsingToolbarLayout;import android.support.v4.app.FragmentManager;import android.support.v4.app.FragmentTransaction;import android.support.v4.content.ContextCompat;import android.support.v7.app.ActionBar;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.Toolbar;import android.view.MenuItem;import android.view.View;import android.widget.FrameLayout;import android.widget.ImageView;import android.widget.TextView;import android.widget.Toast;import com.airbnb.lottie.LottieAnimationView;import com.bumptech.glide.Glide;import com.bumptech.glide.load.DataSource;import com.bumptech.glide.load.engine.GlideException;import com.bumptech.glide.request.RequestListener;import com.bumptech.glide.request.target.Target;import com.squareup.picasso.Callback;import com.squareup.picasso.Picasso;import java.util.ArrayList;import java.util.Date;import butterknife.BindView;import butterknife.ButterKnife;import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;import digitalhouse.android.a0317moacns1c_02.Controller.GenreController;import digitalhouse.android.a0317moacns1c_02.Controller.ListUserController;import digitalhouse.android.a0317moacns1c_02.Controller.MovieController;import digitalhouse.android.a0317moacns1c_02.Fragments.MediaListFragment;import digitalhouse.android.a0317moacns1c_02.Fragments.ProductionTeamFragment;import digitalhouse.android.a0317moacns1c_02.Fragments.RateFragment;import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;import digitalhouse.android.a0317moacns1c_02.Mappers.ImageListMapper;import digitalhouse.android.a0317moacns1c_02.Mappers.ImageViewMapper;import digitalhouse.android.a0317moacns1c_02.Model.Credits.Crew;import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageContainer;import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ImageListItem;import digitalhouse.android.a0317moacns1c_02.Model.Media.VideoContainer;import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;import digitalhouse.android.a0317moacns1c_02.Model.Movie.MovieDetails;import digitalhouse.android.a0317moacns1c_02.Fragments.ImageListFragment;import digitalhouse.android.a0317moacns1c_02.Helpers.ActivityStackManager;import digitalhouse.android.a0317moacns1c_02.R;public class MovieDetailsActivity extends AppCompatActivity implements ImageListFragment.ImageClickeable {    public static final String MOVIE_ID_KEY = "movieId";    private Movie currentMovie;    private Boolean favorited;    private Boolean bookmarked;    FragmentManager fragmentManager;    @BindView(R.id.textViewMDTitle) TextView textViewTitle;    @BindView(R.id.textViewMDGenres) TextView textViewGenres;    @BindView(R.id.textViewMDDate) TextView textViewDate;    @BindView(R.id.textViewMDRuntime) TextView textViewRuntime;    @BindView(R.id.textViewMDOverview) TextView textViewOverview;    @BindView(R.id.imageViewMDPoster) ImageView imageViewPoster;    @BindView(R.id.seeReviews) View seeReviews;    @BindView(R.id.like) ImageView like;    @BindView(R.id.bookmark) ImageView bookmark;    @BindView(R.id.share) ImageView share;    @BindView(R.id.collapsing_toolbar)    CollapsingToolbarLayout collapsingToolbar;    @BindView(R.id.toolbar)    Toolbar toolbar;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        AnimationHelper.postponeTransition(this);        setContentView(R.layout.activity_movie_details);        fragmentManager = getSupportFragmentManager();        ButterKnife.bind(this);        Bundle bundleReceived = getIntent().getExtras();        Integer id = bundleReceived.getInt(MOVIE_ID_KEY);        MovieController.getInstance().getMovie(id, new ResultListener<Movie>() {            @Override            public void finish(Movie movie) {                if (movie==null) {                    Toast.makeText(MovieDetailsActivity.this, "Not available without connection", Toast.LENGTH_LONG).show();                    MovieDetailsActivity.this.startPostponedEnterTransition();                    MovieDetailsActivity.this.finish();                } else {                    currentMovie = movie;                    setupViews(movie.getMovieDetails());                    startMovieDetailsCastFragment(ImageListMapper.map(movie.getCredits()));                    startMovieDetailsProductionTeam(movie.getCredits().getCrew());                    startBackdropsFragment(movie.getImageContainer());                    startRatingsFragment(movie);                    startTrailersFragment(movie.getVideoContainer());                    favorited = ListUserController.getInstance().isMovieInFavorites(movie.getMovieDetails().getId());                    bookmarked = ListUserController.getInstance().isMovieInBookmarks(movie.getMovieDetails().getId());                    setUpButtons();                    setUpCollapsingToolbar();                }            }        });    }    private void setupViews(final MovieDetails movieDetails) {        textViewTitle.setText(movieDetails.getTitle());        textViewGenres.setText(GenreController.getInstance().getGenresString(movieDetails.getGenres()," | "));        Date releaseDate = DateHelper.parse(movieDetails.getRelease_date(),DateHelper.FORMAT_API);        textViewDate.setText(DateHelper.format(releaseDate, DateHelper.FORMAT_FULL));        textViewRuntime.setText(movieDetails.getRuntimeString());        textViewOverview.setText(movieDetails.getOverview());        String url = ImageHelper.getPosterURL(movieDetails.getPoster_path(),2);        Picasso.with(this).load(url).fit().into(imageViewPoster, new Callback() {            @Override            public void onSuccess() {                AnimationHelper.startPostponedTransition(MovieDetailsActivity.this);            }            @Override            public void onError() {                AnimationHelper.startPostponedTransition(MovieDetailsActivity.this);            }        });        imageViewPoster.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                startImageViewActivity(movieDetails.getPoster_path(), imageViewPoster);            }        });        seeReviews.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                startReviewsActivity();            }        });    }    private void setUpButtons(){        drawButtons();        int color = ContextCompat.getColor(this, R.color.accent);        bookmark.setColorFilter(color, PorterDuff.Mode.SRC_IN);        like.setColorFilter(color, PorterDuff.Mode.SRC_IN);        share.setColorFilter(color, PorterDuff.Mode.SRC_IN);        bookmark.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                if (bookmarked) {                    bookmarked = false;                    ListUserController.getInstance().removeMovieFromBookmarks(currentMovie.getMovieDetails().getId());                    drawButtons();                } else {                    bookmarked = true;                    ListUserController.getInstance().addMovieToBookmarks(currentMovie);                    drawButtons();                }            }        });        like.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                if (favorited) {                    favorited=false;                    ListUserController.getInstance().removeMovieFromFavorites(currentMovie.getMovieDetails().getId());                    drawButtons();                } else {                    favorited=true;                    ListUserController.getInstance().addMovieToFavorites(currentMovie);                    drawButtons();                }            }        });    }    private void drawButtons() {        if (favorited) like.setImageResource(R.drawable.fav_black_xhdpi);        else like.setImageResource(R.drawable.fav_border_xhdpi);        if (bookmarked) bookmark.setImageResource(R.drawable.bookmark_black_xhdpi);        else bookmark.setImageResource(R.drawable.bookmark_border_xhdpi);    }    private void startImageViewActivity(String imagePath, ImageView imageView) {        AnimationHelper.startLoader(this);        Intent intent = new Intent(this, ImageViewerActivity.class);        Bundle bundle = new Bundle();        bundle.putString(ImageViewerActivity.IMAGE_URL_KEY, ImageViewMapper.map(imagePath));        intent.putExtras(bundle);        startActivityForResult(intent,1, AnimationHelper.getTransitionBundle(this, imageView, "imageviewer"));    }    private void startReviewsActivity() {        Intent intent = new Intent(this, ReviewsActivity.class);        Bundle bundle = new Bundle();        bundle.putInt(ReviewsActivity.MOVIE_ID_KEY, currentMovie.getMovieDetails().getId());        intent.putExtras(bundle);        startActivity(intent);    }    private void setUpCollapsingToolbar(){        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));        collapsingToolbar.setTitle(currentMovie.getMovieDetails().getTitle());        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);        collapsingToolbar.setTitleEnabled(true);        if (toolbar != null)        {            setSupportActionBar(toolbar);            ActionBar actionBar = getSupportActionBar();            if (actionBar != null)            {                actionBar.setDisplayHomeAsUpEnabled(true);            }        }    }    private void startRatingsFragment(Movie movie){        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();        RateFragment fragment = RateFragment.newInstance(movie.getRatingsContainer());        fragmentTransaction.replace(R.id.framelayoutMDRatings, fragment);        fragmentTransaction.commit();    }    private void startTrailersFragment(VideoContainer videoContainer){        ArrayList<String> URLs;        URLs = videoContainer.getYouTubeThumbnailURLs();        if (URLs.size()>0) {            MediaListFragment mediaListFragment = MediaListFragment.newInstance(URLs);            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();            ft.replace(R.id.frameLayoutMDTrailers, mediaListFragment);            ft.commit();        }    }    private void startBackdropsFragment(ImageContainer imagesContainer){        ArrayList<String> URLs = (ArrayList<String>) imagesContainer.getURLsList();        if (URLs.isEmpty()) {            URLs.add(ImageHelper.getPosterURL(currentMovie.getMovieDetails().getPoster_path(),3));        }        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();        MediaListFragment mediaListFragment = MediaListFragment.newInstance(URLs);        fragmentTransaction.replace(R.id.frameLayoutMDImages, mediaListFragment);        fragmentTransaction.commit();    }    private void startMovieDetailsCastFragment(ArrayList<ImageListItem> imageList) {        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        ImageListFragment imageListFragment = ImageListFragment.newInstance(imageList, "Cast");        fragmentTransaction.replace(R.id.frameLayoutMDCast, imageListFragment);        fragmentTransaction.commit();    }    private void startMovieDetailsProductionTeam(ArrayList<Crew> crewList) {        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();        ProductionTeamFragment crewFragment = ProductionTeamFragment.newInstance(crewList);        fragmentTransaction.replace(R.id.frameLayoutMDProductionTeam, crewFragment);        fragmentTransaction.commit();    }    @Override    public boolean onOptionsItemSelected(MenuItem item)    {        switch (item.getItemId())        {            case android.R.id.home:                onBackPressed();        }        return super.onOptionsItemSelected(item);    }    @Override    protected void onStop() {        super.onStop();        AnimationHelper.stopLoader(this);    }    @Override    protected void onActivityResult(int requestCode, int resultCode, Intent data) {        AnimationHelper.stopLoader(this);    }    @Override    public void onClick(ImageListItem imageListItem, String title, Integer index, ImageView imageView) {        if (title.equals("Cast")) {            Bundle transitionBundle = AnimationHelper.getTransitionBundle(this, imageView, "profile");            Bundle bundle = new Bundle();            bundle.putInt(PersonDetailsActivity.PERSON_ID_KEY, imageListItem.getId());            Intent intent = new Intent(this, PersonDetailsActivity.class);            intent.putExtras(bundle);            startActivityForResult(intent,1, transitionBundle);            AnimationHelper.startLoader(this);        }    }}