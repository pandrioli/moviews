package digitalhouse.android.a0317moacns1c_02.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.MovieController;
import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.General.Review;
import digitalhouse.android.a0317moacns1c_02.Model.Movie.Movie;
import digitalhouse.android.a0317moacns1c_02.R;


public class ReviewsActivity extends AppCompatActivity {
    @BindView(R.id.imageViewReviewBackdrop)
    ImageView imageViewBackdrop;
    @BindView(R.id.textViewReviewsTitle)
    TextView textViewTitle;
    @BindView(R.id.recyclerViewReviews)
    RecyclerView recyclerView;
    @BindView(R.id.framelayoutReviews)
    FrameLayout frameLayoutReviews;

    public static final String MOVIE_ID_KEY = "movieId";
    private Integer movieId;
    private Movie movie;
    private List<Review> reviews;
    private Boolean imageLoaded = false;
    private Boolean reviewsLoaded = false;
    private ReviewsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        ButterKnife.bind(this);
        AnimationHelper.startLoaderInView(this, frameLayoutReviews);
        movieId = getIntent().getExtras().getInt(MOVIE_ID_KEY);
        MovieController.getInstance().getMovie(movieId, new ResultListener<Movie>() {
            @Override
            public void finish(Movie result) {
                movie = result;
                setupImageAndTitle();
                stopLoader();
            }
        });
        MovieController.getInstance().getReviews(movieId, new ResultListener<List<Review>>() {
            @Override
            public void finish(List<Review> result) {
                reviews = result;
                reviewsLoaded = true;
                showReviews();
                stopLoader();
            }
        });
    }

    private void stopLoader() {
        if (reviewsLoaded && imageLoaded)
        AnimationHelper.stopLoaderInView(frameLayoutReviews);
    }

    private void setupImageAndTitle() {
        String title = "Reviews for "+movie.getMovieDetails().getTitle();
        textViewTitle.setText(title);
        Picasso.with(this)
                .load(ImageHelper.getBackdropURL(movie.getMovieDetails().getBackdrop_path(), 1)).fit()
                .into(imageViewBackdrop, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageLoaded = true;
                        stopLoader();
                    }

                    @Override
                    public void onError() {
                        imageLoaded = true;
                        stopLoader();
                    }
                });
    }

    private void showReviews() {
        adapter = new ReviewsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private class ReviewsAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ReviewsActivity.this).inflate(R.layout.cell_review, parent, false);
            return new ReviewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ReviewsViewHolder rHolder = (ReviewsViewHolder) holder;
            Review review = reviews.get(position);
            if (review.getAuthor()==null) {
                rHolder.author.setText("No reviews found");
                rHolder.content.setText("Be the first to write one!");
            }
            else {
                rHolder.author.setText("by "+review.getAuthor());
                rHolder.content.setText(review.getContent());
            }
        }

        @Override
        public int getItemCount() {
            return reviews.size();
        }

        private class ReviewsViewHolder extends RecyclerView.ViewHolder {
            private TextView author;
            private TextView content;

            public ReviewsViewHolder(View itemView) {
                super(itemView);
                author = (TextView)itemView.findViewById(R.id.textViewReviewAuthor);
                content = (TextView)itemView.findViewById(R.id.textViewReviewContent);
            }

        }


    }

}
