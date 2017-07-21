package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Controller.MovieController;
import digitalhouse.android.a0317moacns1c_02.Controller.ReviewController;
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
    @BindView(R.id.linearLayoutWriteReview)
    LinearLayout editReviewContainer;
    @BindView(R.id.editTextReview)
    EditText editTextReview;
    @BindView(R.id.buttonSendReview)
    Button buttonSendReview;
    @BindView(R.id.fabAddReview)
    FloatingActionButton fabAddReview;


    public static final String MOVIE_ID_KEY = "movieId";
    private Integer movieId;
    private Movie movie;
    private List<Review> reviewsApi;
    private List<Review> reviewsFirebase;
    private List<Review> reviews;
    private Boolean imageLoaded = false;
    private Boolean reviewsApiLoaded = false;
    private Boolean reviewsFirebaseLoaded = false;
    private ReviewsAdapter adapter;
    private Boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        ButterKnife.bind(this);
        AnimationHelper.startLoaderInView(this, frameLayoutReviews);
        editReviewContainer.setVisibility(View.GONE);
        movieId = getIntent().getExtras().getInt(MOVIE_ID_KEY);
        setupButtons();
        getReviews();
    }

    private void setupButtons() {
        fabAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEditMode();
            }
        });
        buttonSendReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReview();
            }
        });

    }

    private void toggleEditMode() {
        if (editMode) {
            editMode=false;
            fabAddReview.setVisibility(View.VISIBLE);
            editReviewContainer.setVisibility(View.GONE);
            hideKeyboard();
        } else {
            AnimationHelper.zoomAndFade(editReviewContainer, true, 0.3f, 1, 1, 500);
            editMode=true;
            fabAddReview.setVisibility(View.GONE);
            editReviewContainer.setVisibility(View.VISIBLE);
            editTextReview.setText("");
        }
    }

    private void stopLoader() {
        if (reviewsApiLoaded && reviewsFirebaseLoaded && imageLoaded)
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
        if (!(reviewsFirebaseLoaded && reviewsApiLoaded)) return;
        reviews = new ArrayList<>();
        reviews.addAll(reviewsFirebase);
        reviews.addAll(reviewsApi);
        if (reviews.isEmpty()) reviews.add(new Review());
        adapter = new ReviewsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void saveReview() {
        ReviewController.getInstance().saveReview(movie.getMovieDetails().getId(), editTextReview.getText().toString());
        toggleEditMode();
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void getReviews() {
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
                reviewsApi = result;
                reviewsApiLoaded = true;
                showReviews();
                stopLoader();
            }
        });
        ReviewController.getInstance().getReviews(movie.getMovieDetails().getId(), new ResultListener<List<Review>>() {
            @Override
            public void finish(List<Review> result) {
                reviewsFirebase = result;
                reviewsFirebaseLoaded = true;
                showReviews();
                stopLoader();
            }
        });
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
