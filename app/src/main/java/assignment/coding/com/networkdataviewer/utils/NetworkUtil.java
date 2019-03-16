package assignment.coding.com.networkdataviewer.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkUtil {


    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = null;
        if (cm != null) {
            current = cm.getActiveNetworkInfo();
        }
        else
        {
            Log.e(NetworkUtil.class.getName(),"ConnectivityManager is null");
        }
        return current != null && (current.isAvailable());
    }
}
