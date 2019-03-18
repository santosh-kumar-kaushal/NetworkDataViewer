package assignment.coding.com.networkdataviewer.data.model;

import java.util.Objects;

/**
 * Links model stores links which is received from service.
 */
public class LinksModel {

    /**
     * start link.
     */
    private String start;
    /**
     * next link.
     */
    private String next;

    /**
     * Getter for start link.
     *
     * @return start link.
     */
    public String getStart() {
        return start;
    }

    /**
     * Setter for start.
     *
     * @param start start.
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Getter for next.
     *
     * @return next.
     */
    public String getNext() {
        return next;
    }

    /**
     * Setter for next.
     *
     * @param next next
     */
    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "LinksModel{" +
                "start='" + start + '\'' +
                ", next='" + next + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinksModel)) return false;
        LinksModel that = (LinksModel) o;
        return Objects.equals(getStart(), that.getStart()) &&
                Objects.equals(getNext(), that.getNext());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getNext());
    }
}
