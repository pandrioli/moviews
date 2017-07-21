package digitalhouse.android.a0317moacns1c_02.Controller;

import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Callbacks.ResultListener;
import digitalhouse.android.a0317moacns1c_02.Model.General.Review;
import digitalhouse.android.a0317moacns1c_02.Model.General.ReviewContainer;

/**
 * Created by Pablo on 21/07/2017.
 */

public class ReviewController {
    private static ReviewController instance;
    private FirebaseUser user;
    private DatabaseReference reviewsReference;

    public static ReviewController getInstance() {
        if (instance==null) instance = new ReviewController();
        return instance;
    }

    private ReviewController() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        reviewsReference = FirebaseDatabase.getInstance().getReference().child("reviews");
    }

    public void saveReview(Integer movieId, String content) {
        DatabaseReference reference = reviewsReference.child(movieId.toString()).push();
        Review review = new Review();
        if (user.isAnonymous()) review.setAuthor("Anonymous");
        else review.setAuthor(user.getDisplayName());
        review.setUserId(user.getUid());
        review.setContent(content);
        review.setId(reference.getKey());
        reference.setValue(review);
    }

    public void deleteReview(Integer movieId, String reviewId) {
        DatabaseReference reference = reviewsReference.child(movieId.toString()).child(reviewId);
        reference.removeValue();
    }

    public void getReviews(Integer movieId, final ResultListener<List<Review>> resultListener) {
        reviewsReference.child(movieId.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Review> reviews = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    reviews.add(data.getValue(Review.class));
                }
                resultListener.finish(reviews);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                resultListener.finish(new ArrayList<Review>());
            }
        });
    }

}
