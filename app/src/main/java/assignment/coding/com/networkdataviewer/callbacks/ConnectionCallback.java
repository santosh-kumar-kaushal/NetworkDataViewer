package assignment.coding.com.networkdataviewer.callbacks;

/**
 * This interface is a connection callback interface which is used to know if service
 * is bounded with UI components.
 */
public interface ConnectionCallback {
    /**
     * Callback when service is bound to UI classes.
     *
     * @param isBounded flag which tells about the status.
     */
    void onServiceBound(boolean isBounded);
}
