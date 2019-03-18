package assignment.coding.com.networkdataviewer.network;


import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import assignment.coding.com.networkdataviewer.callbacks.ConnectionCallback;

/**
 * Connection class which connects service to caller class.
 */
public class Connection implements ServiceConnection {
    /**
     * Instance of {@link BackendService}.
     */
    private BackendService mService;
    /**
     * Callback when connection is bound/un-bound.
     */
    private ConnectionCallback connectionCallback;

    /**
     * Setter for connection callback.
     *
     * @param connectionCallback {@link ConnectionCallback}.
     */
    public void setConnectionCallback(ConnectionCallback connectionCallback) {
        this.connectionCallback = connectionCallback;
    }

    /**
     * Getter for service.
     *
     * @return service.
     */
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

