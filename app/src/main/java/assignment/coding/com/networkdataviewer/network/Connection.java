package assignment.coding.com.networkdataviewer.network;


import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import assignment.coding.com.networkdataviewer.callbacks.ConnectionCallback;


/**
 * Created by santosh on 1/26/2017.
 */

public class Connection implements ServiceConnection {

    private BackendService mService;

    private ConnectionCallback connectionCallback;

    public void setConnectionCallback(ConnectionCallback connectionCallback) {
        this.connectionCallback = connectionCallback;
    }

    public BackendService getmService() {
        return mService;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        BackendService.ServiceBinder binder = (BackendService.ServiceBinder) service;
        mService = binder.getService();
        if (connectionCallback != null) {
            connectionCallback.onServiceBound(true);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        if (connectionCallback != null) {
            connectionCallback.onServiceBound(false);
        }

    }
}

