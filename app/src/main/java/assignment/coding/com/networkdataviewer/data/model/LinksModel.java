package assignment.coding.com.networkdataviewer.data.model;

import java.util.Objects;

public class LinksModel {

    private String start;

    private String next;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getNext() {
        return next;
    }

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
