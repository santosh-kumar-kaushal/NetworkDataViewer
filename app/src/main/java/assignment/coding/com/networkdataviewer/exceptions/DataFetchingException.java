package assignment.coding.com.networkdataviewer.exceptions;


public class DataFetchingException extends Exception {

    public DataFetchingException(String message) {
        super(message);
    }

    public DataFetchingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFetchingException(Throwable cause) {
        super(cause);
    }

}