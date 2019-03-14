package assignment.coding.com.networkdataviewer.data.model;

public class NetworkDataModel {

    private String help;

    private boolean successFlag;

    private ResultsModel resultsModel;

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public boolean isSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }

    public ResultsModel getResultsModel() {
        return resultsModel;
    }

    public void setResultsModel(ResultsModel resultsModel) {
        this.resultsModel = resultsModel;
    }

    @Override
    public String toString() {
        return "NetworkDataModel{" +
                "help='" + help + '\'' +
                ", successFlag=" + successFlag +
                ", resultsModel=" + resultsModel +
                '}';
    }
}
