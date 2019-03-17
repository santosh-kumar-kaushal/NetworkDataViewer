package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.callbacks.ConnectionCallback;
import assignment.coding.com.networkdataviewer.callbacks.DataNotificationCallback;
import assignment.coding.com.networkdataviewer.data.converter.NetworkUIDataConverter;
import assignment.coding.com.networkdataviewer.data.database.DataBaseHandler;
import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.data.model.NetworkUIData;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;
import assignment.coding.com.networkdataviewer.network.BackendService;
import assignment.coding.com.networkdataviewer.network.Connection;
import assignment.coding.com.networkdataviewer.ui.base.mvp.presenter.BasePresenter;
import assignment.coding.com.networkdataviewer.utils.NetworkUtil;

public class HomePresenter extends BasePresenter<HomeMVP.View> implements HomeMVP.Presenter, DataNotificationCallback, ConnectionCallback {

    private Connection connection;

    private Context context;

    private HomeMVP.View homeView;

    private ArrayList<RecordsModel> recordsModelArrayList = new ArrayList<>();

    private DataBaseHandler dataBaseHandler;

    private boolean isBounded;


    HomePresenter(HomeMVP.View homeView, Context context) {
        this.context = context;
        this.homeView = homeView;
        dataBaseHandler = new DataBaseHandler(context);
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
        NetworkDataModel networkDataModel = NetworkUIDataConverter.parseJsonAsObject(response);
        //Adding records model to database.
        for (RecordsModel recordsModel : networkDataModel.getResultsModel().getRecordsModelList()) {
            dataBaseHandler.addRecordsModel(recordsModel);
        }
        ArrayList<RecordsModel> recordsModelArrayList = filterYearDataForRecords(networkDataModel.getResultsModel().getRecordsModelList(), 2000, 8, 18);
        ArrayList<NetworkUIData> networkUIDataArrayList = getDataForDisplay(recordsModelArrayList);
        Log.i("data", networkUIDataArrayList.toString());
        homeView.populateData(recordsModelArrayList);
    }


    private ArrayList<NetworkUIData> getDataForDisplay(ArrayList<RecordsModel> recordsModelList) {
        ArrayList<NetworkUIData> networkUIDataList = new ArrayList<>();
        int count = 0;
        double totalDataVolume = 0;
        NetworkUIData networkUIData;
        for (RecordsModel recordsModel : recordsModelList) {
            networkUIData = new NetworkUIData();
            if (count < 4) {
                totalDataVolume += recordsModel.getVolumeOfMobileData();
                count++;
                networkUIData.setTotalDataVolume(totalDataVolume);
            } else {
                count = 0;
            }
            networkUIDataList.add(networkUIData);
        }
        return networkUIDataList;
    }


//    private ArrayList<RecordsModel> filterYearDataForRecords(ArrayList<RecordsModel> recordsModels, int yearSession, int startYear, int endYear) {
////        int quarter = 1;
////        double totalData = 0;
////        double previous = 0;
////        double next = 0;
////        ArrayList<RecordsModel> filteredRecordsList = new ArrayList<>();
////        for (int i = 0; i < recordsModels.size(); i++) {
////            int year = yearSession + startYear;
////            if (recordsModels.get(i).getQuarter().contains(year + "-Q" + quarter)) {
////                totalData += recordsModels.get(i).getVolumeOfMobileData();
////                quarter++;
////                //One year has four quarters.
////                if (quarter > 4) {
////                    recordsModels.get(i).setTotalVolumeOfMobileData(totalData);
////                    filteredRecordsList.add(recordsModels.get(i));
////                    startYear++;
////                    quarter = 1;
////                    totalData = 0;
////                }
////                if (startYear > endYear) {
////                    break;
////                }
////            }
////        }
////        return filteredRecordsList;
////    }


    private ArrayList<RecordsModel> filterYearDataForRecords(ArrayList<RecordsModel> recordsModels, int yearSession, int startYear, int endYear) {
        int quarter = 1;
        ArrayList<RecordsModel> filteredRecordsList = new ArrayList<>();
        for (RecordsModel recordsModel : recordsModels) {
            int year = yearSession + startYear;
            if (recordsModel.getQuarter().contains(year + "-Q" + quarter)) {
                filteredRecordsList.add(recordsModel);
                quarter++;
                //One year have four quarters.
                if (quarter > 4) {
                    startYear++;
                    quarter = 1;
                }
                if (startYear > endYear) {
                    break;
                }
            }
        }
        return filteredRecordsList;
    }


    @Override
    public void onServiceBound(boolean isBounded) {
        this.isBounded = isBounded;
        if (!NetworkUtil.isNetworkReachable(context)) {
            onWorkOffline();
        } else {
            onWorkOnline();
        }
        homeView.serviceBoundStatus(isBounded, connection);
    }


    private void onWorkOffline() {
        ArrayList<RecordsModel> recordsModelArrayList = dataBaseHandler.getAllRecords();
        homeView.populateData(recordsModelArrayList);
        Toast.makeText(context, context.getString(R.string.network_error), Toast.LENGTH_LONG).show();
    }


    private void onWorkOnline() {
        if (isBounded) {
            connection.getmService().setDataNotificationCallback(this);
            connection.getmService().new FetchJsonData().execute("https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=56");
        } else {
            Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_LONG).show();
        }
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
