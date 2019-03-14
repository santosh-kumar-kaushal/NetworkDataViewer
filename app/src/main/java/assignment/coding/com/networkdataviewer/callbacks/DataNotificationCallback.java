package assignment.coding.com.networkdataviewer.callbacks;


public interface DataNotificationCallback {

    void onSuccess();

    void onReceive(String response);
}
