package assignment.coding.com.networkdataviewer.data.model;

import java.util.Objects;

/**
 * All the records which needs to be displayed to UI.
 */
public class RecordsModel {
    /**
     * Quarter in a year.
     */
    private String quarter;
    /**
     * mobile data used in a quarter.
     */
    private double volumeOfMobileData;
    /**
     * Quarter ID.
     */
    private int id;
    /**
     * Total mobile data in a year.
     */
    private double totalVolumeOfMobileData;
    /**
     * year.
     */
    private String year;
    /**
     * decrease in volume flag.
     */
    private String isDecreaseInVolume="";

    /**
     * Getter for the flag.
     * @return isDecreaseInVolume.
     */
    public String getIsDecreaseInVolume() {
        return isDecreaseInVolume;
    }

    /**
     * Setter for isDecreaseInVolume.
     * @param isDecreaseInVolume isDecreaseInVolume
     */
    public void setIsDecreaseInVolume(String isDecreaseInVolume) {
        this.isDecreaseInVolume = isDecreaseInVolume;
    }

    /**
     * Getter for year
     *
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * Setter for year
     * @param year year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Constructor.
     */
    public RecordsModel() {
    }

    /**
     * Constructor.
     * @param quarter
     * @param volumeOfMobileData
     * @param id
     * @param totalVolumeOfMobileData
     * @param year
     * @param isDecreaseInVolume
     */
    public RecordsModel(String quarter, double volumeOfMobileData, int id, double totalVolumeOfMobileData, String year, String isDecreaseInVolume) {
        this.quarter = quarter;
        this.volumeOfMobileData = volumeOfMobileData;
        this.id = id;
        this.totalVolumeOfMobileData = totalVolumeOfMobileData;
        this.year = year;
        this.isDecreaseInVolume = isDecreaseInVolume;
    }

    /**
     * Getter for totalVolumeOfMobileData
     * @return totalVolumeOfMobileData
     */
    public double getTotalVolumeOfMobileData() {
        return totalVolumeOfMobileData;
    }

    /**
     * Setter for totalVolumeOfMobileData
     * @param totalVolumeOfMobileData totalVolumeOfMobileData
     */
    public void setTotalVolumeOfMobileData(double totalVolumeOfMobileData) {
        this.totalVolumeOfMobileData = totalVolumeOfMobileData;
    }

    /**
     * Getter for quarter
     * @return quarter
     */
    public String getQuarter() {
        return quarter;
    }

    /**
     * setter for quarter
     * @param quarter quarter
     */
    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    /**
     * Getter for volumeOfMobileData
     * @return volumeOfMobileData
     */
    public double getVolumeOfMobileData() {
        return volumeOfMobileData;
    }

    /**
     * Setter for volumeOfMobileData
     * @param volumeOfMobileData volumeOfMobileData
     */
    public void setVolumeOfMobileData(double volumeOfMobileData) {
        this.volumeOfMobileData = volumeOfMobileData;
    }

    /**
     * Getter for id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id id
     */
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
