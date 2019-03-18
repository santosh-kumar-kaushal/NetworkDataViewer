package assignment.coding.com.networkdataviewer.exceptions;

/**
 * Exception when there is an error in fetching data.
 */
public class DataFetchingException extends Exception {
    /**
     * Constructor.
     *
     * @param message message.
     */
    public DataFetchingException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message message.
     * @param cause   reason for throwing exception.
     */
    public DataFetchingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     *
     * @param cause reason for throwing exception.
     */
    public DataFetchingException(Throwable cause) {
        super(cause);
    }

}