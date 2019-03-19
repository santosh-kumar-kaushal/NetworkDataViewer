package assignment.coding.com.networkdataviewer.data.converter;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.data.model.LinksModel;
import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;
import assignment.coding.com.networkdataviewer.data.model.ResultsModel;

/**
 * Converts server data to models.
 */
public class NetworkUIDataConverter {

    public NetworkUIDataConverter() {
    }

    /**
     * Logger.
     */
    private static final String TAG = NetworkUIDataConverter.class.getName();

    /**
     * Method used to parse network information from json string from server.
     *
     * @param jsonString response from server.
     */
    public  NetworkDataModel parseJsonAsObject(String jsonString) {
        ArrayList<RecordsModel> recordsModelList = new ArrayList<>();
        NetworkDataModel networkDataModel = new NetworkDataModel();
        ResultsModel resultsModel = new ResultsModel();
        LinksModel linksModel = new LinksModel();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String help = jsonObject.getString("help");
            networkDataModel.setHelp(help);
            boolean success = jsonObject.getBoolean("success");
            networkDataModel.setSuccessFlag(success);
            //parse result
            JSONObject resultObject = jsonObject.getJSONObject("result");
            String resourceId = resultObject.getString("resource_id");
            resultsModel.setResourceId(resourceId);
            int limit = resultObject.getInt("limit");
            resultsModel.setLimit(limit);
            int total = resultObject.getInt("total");
            resultsModel.setTotal(total);
            JSONArray records = (JSONArray) resultObject.get("records");

            for (int recordNo = 0; recordNo < records.length(); recordNo++) {
                RecordsModel recordsModel = new RecordsModel();
                JSONObject record = records.getJSONObject(recordNo);
                String quarter = record.getString("quarter");
                recordsModel.setQuarter(quarter);
                double volumeOfMobileData = record.getDouble("volume_of_mobile_data");
                recordsModel.setVolumeOfMobileData(volumeOfMobileData);
                int id = record.getInt("_id");
                recordsModel.setId(id);
                recordsModelList.add(recordsModel);
            }
            resultsModel.setRecordsModelList(recordsModelList);
            JSONObject linksObject = resultObject.getJSONObject("_links");
            String start = linksObject.getString("start");
            linksModel.setStart(start);
            String next = linksObject.getString("next");
            linksModel.setNext(next);
            resultsModel.setLinksModel(linksModel);
            networkDataModel.setResultsModel(resultsModel);
        } catch (JSONException ignored) {
            Log.e(TAG, "Exception in parsing data");
        }
        return networkDataModel;
    }


}
