package assignment.coding.com.networkdataviewer.data.model;

import java.util.Objects;

public class RecordsModel {

    private String quarter;

    private double volumeOfMobileData;

    private int id;

    private double totalVolumeOfMobileData;

    public RecordsModel() {
    }

    public RecordsModel(String quarter, double volumeOfMobileData, int id) {
        this.quarter = quarter;
        this.volumeOfMobileData = volumeOfMobileData;
        this.id = id;
    }

    public double getTotalVolumeOfMobileData() {
        return totalVolumeOfMobileData;
    }

    public void setTotalVolumeOfMobileData(double totalVolumeOfMobileData) {
        this.totalVolumeOfMobileData = totalVolumeOfMobileData;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public double getVolumeOfMobileData() {
        return volumeOfMobileData;
    }

    public void setVolumeOfMobileData(double volumeOfMobileData) {
        this.volumeOfMobileData = volumeOfMobileData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecordsModel{" +
                "quarter='" + quarter + '\'' +
                ", volumeOfMobileData=" + volumeOfMobileData +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordsModel)) return false;
        RecordsModel that = (RecordsModel) o;
        return getId() == that.getId() &&
                Objects.equals(getQuarter(), that.getQuarter()) &&
                Objects.equals(getVolumeOfMobileData(), that.getVolumeOfMobileData());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getQuarter(), getVolumeOfMobileData(), getId());
    }
}
