package digitalhouse.android.a0317moacns1c_02.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Pablo on 20/07/2017.
 */

public class NetworkController {
    private static NetworkController instance;
    public static NetworkController getInstance() {
        return instance;
    }
    private Context context;
    public static void init(Context context) {
        instance = new NetworkController(context);
    }
    private NetworkController(Context context) {
        this.context = context;
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
