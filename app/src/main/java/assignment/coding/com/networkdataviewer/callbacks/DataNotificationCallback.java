package assignment.coding.com.networkdataviewer.callbacks;

/**
 * This class is responsible for giving callback to asynctask consumer.
 */
public interface DataNotificationCallback {
    /**
     * Callback when connection is successful with the server.
     */
    void onSuccess();

    /**
     * Callback when data is received from server.
     *
     * @param response data from server after request.
     */
    void onReceive(String response);
}
