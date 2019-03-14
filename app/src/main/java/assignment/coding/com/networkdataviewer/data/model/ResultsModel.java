package assignment.coding.com.networkdataviewer.data.model;

import java.util.ArrayList;
import java.util.Objects;

public class ResultsModel {


    private String resourceId;

    private ArrayList<ResultsModel> resultsModelList;

    private LinksModel linksModel;

    private int limit;

    private int total;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public ArrayList<ResultsModel> getResultsModelList() {
        return resultsModelList;
    }

    public void setResultsModelList(ArrayList<ResultsModel> resultsModelList) {
        this.resultsModelList = resultsModelList;
    }

    public LinksModel getLinksModel() {
        return linksModel;
    }

    public void setLinksModel(LinksModel linksModel) {
        this.linksModel = linksModel;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ResultsModel{" +
                "resourceId='" + resourceId + '\'' +
                ", resultsModelList=" + resultsModelList +
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
                Objects.equals(getResultsModelList(), that.getResultsModelList()) &&
                Objects.equals(getLinksModel(), that.getLinksModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResourceId(), getResultsModelList(), getLinksModel(), getLimit(), getTotal());
    }
}
