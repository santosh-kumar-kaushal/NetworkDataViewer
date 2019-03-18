package assignment.coding.com.networkdataviewer.data.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Results object to model conversion.
 */
public class ResultsModel {

    /**
     * Resource ID.
     */
    private String resourceId;
    /**
     * List of all the records.
     */
    private ArrayList<RecordsModel> recordsModelList;
    /**
     * Links.
     */
    private LinksModel linksModel;
    /**
     * Limit.
     */
    private int limit;
    /**
     * Total number of records count.
     */
    private int total;

    /**
     * Getter for resourceId
     * @return resourceId
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Setter for resourceId
     * @param resourceId resourceId
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Getter for recordsModelList
     * @return recordsModelList
     */
    public ArrayList<RecordsModel> getRecordsModelList() {
        return recordsModelList;
    }

    /**
     * Setter for recordsModelList
     * @param recordsModelList recordsModelList
     */
    public void setRecordsModelList(ArrayList<RecordsModel> recordsModelList) {
        this.recordsModelList = recordsModelList;
    }

    /**
     * Getter for linksModel
     * @return linksModel
     */
    public LinksModel getLinksModel() {
        return linksModel;
    }

    /**
     * Setter for linksModel
     * @param linksModel linksModel
     */
    public void setLinksModel(LinksModel linksModel) {
        this.linksModel = linksModel;
    }

    /**
     * Getter for limit
     * @return limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Setter for limit
     * @param limit limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     *  Getter for total
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Setter for total
     * @param total total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ResultsModel{" +
                "resourceId='" + resourceId + '\'' +
                ", recordsModelList=" + recordsModelList +
                ", linksModel=" + linksModel +
                ", limit=" + limit +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultsModel)) return false;
        ResultsModel that = (ResultsModel) o;
        return getLimit() == that.getLimit() &&
                getTotal() == that.getTotal() &&
                Objects.equals(getResourceId(), that.getResourceId()) &&
                Objects.equals(getRecordsModelList(), that.getRecordsModelList()) &&
                Objects.equals(getLinksModel(), that.getLinksModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResourceId(), getRecordsModelList(), getLinksModel(), getLimit(), getTotal());
    }
}
