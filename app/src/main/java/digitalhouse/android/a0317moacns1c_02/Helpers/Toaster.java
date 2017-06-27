package digitalhouse.android.a0317moacns1c_02.Helpers;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Pablo on 27/06/2017.
 */

public class Toaster {
    private static Toaster instance;
    private Context context;
    public static Toaster getInstance() {
        return instance;
    }
    public static void init(Context context) {
        instance = new Toaster(context);
    }
    private Toaster(Context context) {
        this.context = context;
    }
    public void toast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
