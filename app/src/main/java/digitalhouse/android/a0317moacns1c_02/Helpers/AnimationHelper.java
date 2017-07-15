package digitalhouse.android.a0317moacns1c_02.Helpers;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by Pablo on 11/07/2017.
 */

public class AnimationHelper {
    public static void zoomAndFade(final View view, final boolean show, float minScale, float xPivot, float yPivot, long duration) {
        float startScale;
        float endScale;
        if (show) {
            startScale = minScale;
            endScale = 1;
        } else {
            startScale = 1;
            endScale = minScale;
        }
        float startAlpha;
        float endAlpha;
        if (show) {
            startAlpha = 0;
            endAlpha = 1;
        } else {
            startAlpha = 1;
            endAlpha = 0;
        }
        ScaleAnimation scaleAnim = new ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                Animation.RELATIVE_TO_SELF, xPivot,
                Animation.RELATIVE_TO_SELF , yPivot);
        scaleAnim.setDuration(duration);
        if (show) scaleAnim.setInterpolator(new DecelerateInterpolator());
        else scaleAnim.setInterpolator(new AccelerateInterpolator());
        AlphaAnimation alphaAnimation = new AlphaAnimation(startAlpha,endAlpha);
        alphaAnimation.setDuration(duration);
        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(alphaAnimation);
        animation.addAnimation(scaleAnim);
        animation.setFillAfter(true);
        animation.setRepeatCount(0);
        view.startAnimation(animation);
    }

    @TargetApi(21)
    public static Bundle getTransitionBundle(Activity activity, View view, String transition) {
        if (Build.VERSION.SDK_INT >=21) activity.getWindow().setSharedElementsUseOverlay(false);
        return ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity,view,transition)
                .toBundle();
    }

    @TargetApi(21)
    public static void postponeTransition(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.postponeEnterTransition();
        }
    }

    @TargetApi(21)
    public static void startPostponedTransition(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.startPostponedEnterTransition();
        }
    }


    public static void startLoader(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        View loaderView = activity.getLayoutInflater().inflate(R.layout.loader, (ViewGroup)view, false);
        ((ViewGroup) view).addView(loaderView);
    }

    public static void stopLoader(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        View loaderView = view.findViewById(R.id.loaderContainer);
        ((ViewGroup) view).removeView(loaderView);
    }


    public static void startLoaderInView(Activity activity, View view) {
        View loaderView = activity.getLayoutInflater().inflate(R.layout.loader, (ViewGroup)view, false);
        loaderView.setBackgroundColor(Color.WHITE);
        ((ViewGroup) view).addView(loaderView);
    }

    public static void stopLoaderInView(Activity activity, View view) {
        View loaderView = view.findViewById(R.id.loaderContainer);
        ((ViewGroup) view).removeView(loaderView);
    }


}
