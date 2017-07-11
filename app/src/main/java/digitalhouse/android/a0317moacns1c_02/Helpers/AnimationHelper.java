package digitalhouse.android.a0317moacns1c_02.Helpers;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

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
}
