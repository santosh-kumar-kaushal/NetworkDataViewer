package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.content.Context;

import assignment.coding.com.networkdataviewer.utils.NetworkUtil;

public class HomeDelegateFactory {


    public static AbstractHomeDelegate getDelegate(Context context) {
        if (NetworkUtil.isNetworkReachable(context)) {
            return new OnlineHomeDelegate(context);
        } else {
            return new OfflineHomeDelegate(context);
        }
    }


}
