package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.callbacks.ConnectionCallback;
import assignment.coding.com.networkdataviewer.callbacks.DataNotificationCallback;
import assignment.coding.com.networkdataviewer.data.model.LinksModel;
import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;
import assignment.coding.com.networkdataviewer.data.model.ResultsModel;
import assignment.coding.com.networkdataviewer.network.BackendService;
import assignment.coding.com.networkdataviewer.network.Connection;
import assignment.coding.com.networkdataviewer.ui.base.mvp.presenter.BasePresenter;

public class HomePresenter extends BasePresenter<HomeMVP.View> implements HomeMVP.Presenter, DataNotificationCallback, ConnectionCallback {

    private Connection connection;

    private Context context;

    private boolean isBounded;

    private HomeMVP.View homeView;

    private static final String TAG = HomePresenter.class.getName();

    private ArrayList<RecordsModel> recordsModelArrayList = new ArrayList<>();


    HomePresenter(HomeMVP.View homeView, Context context) {
        this.homeView = homeView;
        Intent intent = new Intent(context, BackendService.class);
        connection = new Connection();
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        connection.setConnectionCallback(this);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onReceive(String response) {
        NetworkDataModel networkDataModel = parseJsonAsObject(response);
        recordsModelArrayList = networkDataModel.getResultsModel().getRecordsModelList();
        //for future use.
        Log.i(TAG, networkDataModel.toString());
    }


    /**
     * Method used to parse network information from json string from server.
     *
     * @param jsonString response from server.
     */
    //TODO - Move it to parser level.
    private NetworkDataModel parseJsonAsObject(String jsonString) {
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
        ArrayList<RecordsModel> recordsModels = filterYearDataForRecords(recordsModelList);
        homeView.populateData(recordsModels);
        return networkDataModel;
    }

    private ArrayList<RecordsModel> filterYearDataForRecords(ArrayList<RecordsModel> recordsModels) {
        int count = 8;
        ArrayList<RecordsModel> filteredRecordsList=new ArrayList<>();
        for (RecordsModel recordsModel : recordsModels) {
            int year = 2000 + count;
            if (recordsModel.getQuarter().contains(year + "")) {
                filteredRecordsList.add(recordsModel);
                count++;
                if (count>18) {
                    break;
                }

            }

        }
        return filteredRecordsList;
    }


    @Override
    public void onServiceBound(boolean isBounded) {
        this.isBounded = isBounded;
        if (isBounded) {
            connection.getmService().setDataNotificationCallback(this);
            connection.getmService().new FetchJsonData().execute("https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=56");
        }
    }


    @Override
    public void onWorkOffline(boolean workOffline) {

    }

    @Override
    public void onLoadMore() {
        recordsModelArrayList.add(null);
        homeView.notifyItemInserted(recordsModelArrayList.size() - 1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recordsModelArrayList.remove(recordsModelArrayList.size() - 1);
                int scrollPosition = recordsModelArrayList.size();
                homeView.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                while (currentSize - 1 < nextLimit) {
                    //networkDataModelList.add("Item " + currentSize);
                    currentSize++;
                }
                //TODO check this.
                homeView.onNotifyAdapter(recordsModelArrayList);
                homeView.isLoadingFlag(false);
            }
        }, 2000);

    }

}
