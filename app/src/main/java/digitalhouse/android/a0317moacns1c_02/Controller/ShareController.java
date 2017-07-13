package digitalhouse.android.a0317moacns1c_02.Controller;

import android.content.Intent;

import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;

/**
 * Created by Gregorio Martin on 12/7/2017.
 */

public class ShareController {

    private static final String SHARE_SERIE_TEXT = "Hi! Check out this Series: %1$s! %2$s";
    private static final String SHARE_MOVIE_TEXT = "Hi! Check out this Movie: %1$s! %2$s";
    private static final String SHARE_EPISODE_TEXT = "Hi! Check out this Episode: %1$s! %2$s";
    private static final String FINAL_MESSAGE = "You can view more info in Moviews App :)";
    private static ShareController instance;

    private Intent intent;

    private ShareController(){

    }

    public static ShareController getInstance(){
        if(instance == null) instance = new ShareController();
        return instance;
    }

    public Intent obtainShareIntent(ListItem listItem){
        intent = new Intent();
        if(listItem.getType().equals(ListItem.TYPE_MOVIE)){
            obtainShareMovieIntent(listItem);
        } else {
            obtainShareSeriesIntent(listItem);
        }
        intent.setType("text/plain");
        return intent;
    }

    private void obtainShareMovieIntent(ListItem listItem){
        intent.putExtra(Intent.EXTRA_TEXT, String.format(SHARE_MOVIE_TEXT, listItem.getTitle(), FINAL_MESSAGE));
    }

    private void obtainShareSeriesIntent(ListItem listItem){
        intent.putExtra(Intent.EXTRA_TEXT, String.format(SHARE_SERIE_TEXT, listItem.getTitle(), FINAL_MESSAGE));
    }

}
