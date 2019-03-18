package assignment.coding.com.networkdataviewer.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Utility class to check network connectivity.
 */
public class NetworkUtil {

    /**
     * Method checks for network.
     *
     * @param context context.
     * @return true if network is reachable.
     */
    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = null;
        if (cm != null) {
            current = cm.getActiveNetworkInfo();
        } else {
            Log.e(NetworkUtil.class.getName(), "ConnectivityManager is null");
        }
        return current != null && (current.isAvailable());
    }
}
