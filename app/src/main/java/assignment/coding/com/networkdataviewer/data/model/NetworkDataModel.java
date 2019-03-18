package assignment.coding.com.networkdataviewer.data.model;

/**
 * Model is created from data received from api.
 */
public class NetworkDataModel {
    /**
     * Help.
     */
    private String help;
    /**
     * Flag which tells us about the success/failure of api.
     */
    private boolean successFlag;
    /**
     * {@link ResultsModel}.
     */
    private ResultsModel resultsModel;

    /**
     * Getter for help.
     *
     * @return help.
     */
    public String getHelp() {
        return help;
    }

    /**
     * Setter for help.
     *
     * @param help help.
     */
    public void setHelp(String help) {
        this.help = help;
    }

    /**
     * Getter for flag;
     *
     * @return successFlag
     */
    public boolean isSuccessFlag() {
        return successFlag;
    }

    /**
     * Setter for success flag.
     *
     * @param successFlag successFlag
     */
    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }

    /**
     * Getter for {@link ResultsModel}.
     *
     * @return {@link ResultsModel}.
     */
    public ResultsModel getResultsModel() {
        return resultsModel;
    }

    /**
     * Setter for {@link ResultsModel}.
     *
     * @param resultsModel {@link ResultsModel}.
     */
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
