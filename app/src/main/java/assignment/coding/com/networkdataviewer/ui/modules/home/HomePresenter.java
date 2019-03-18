package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.callbacks.ConnectionCallback;
import assignment.coding.com.networkdataviewer.callbacks.DataNotificationCallback;
import assignment.coding.com.networkdataviewer.data.converter.NetworkUIDataConverter;
import assignment.coding.com.networkdataviewer.data.database.DataBaseHandler;
import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;
import assignment.coding.com.networkdataviewer.network.BackendService;
import assignment.coding.com.networkdataviewer.network.Connection;
import assignment.coding.com.networkdataviewer.ui.base.mvp.presenter.BasePresenter;
import assignment.coding.com.networkdataviewer.utils.NetworkUtil;

/**
 * This class is responsible for holding business logic for home screen to be displayed.
 */
public class HomePresenter extends BasePresenter<HomeMVP.View> implements HomeMVP.Presenter, DataNotificationCallback, ConnectionCallback {
    /**
     * Connection instance for binding the service to UI.
     */
    private Connection connection;
    /**
     * Context.
     */
    private Context context;
    /**
     * Instance of {@link HomeMVP.View} for callback to UI.
     */
    private HomeMVP.View homeView;
    /**
     * Instance of {@link DataBaseHandler} for data storage for caching.
     */
    private DataBaseHandler dataBaseHandler;
    /**
     * Flag which is used to maintain service binding status.
     */
    private boolean isBounded;

    /**
     * Constructor.
     *
     * @param homeView contract.
     * @param context  context.
     */
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
        homeView.hideProgressBar();
    }

    @Override
    public void onReceive(String response) {
        ArrayList<RecordsModel> recordsModelArrayList;
        NetworkDataModel networkDataModel = NetworkUIDataConverter.parseJsonAsObject(response);
        recordsModelArrayList = filterYearDataForRecords(networkDataModel.getResultsModel().getRecordsModelList(), 2000, 8, 18);
        //Adding records model to database.
        for (RecordsModel recordsModel : recordsModelArrayList) {
            dataBaseHandler.addRecordsModel(recordsModel);
        }
        homeView.populateData(recordsModelArrayList);
        homeView.onNotifyAdapter();
    }

    /**
     * Filter data which needs to be displayed on UI.
     *
     * @param recordsModels models which has important data.
     * @param yearSession   year eg. 90's/ 200's.
     * @param startYear     start year for data to be displayed.
     * @param endYear       till end year for which data is displayed.
     * @return filtered {@link ArrayList<RecordsModel>}
     */
    private ArrayList<RecordsModel> filterYearDataForRecords(ArrayList<RecordsModel> recordsModels, int yearSession, int startYear, int endYear) {
        int quarter = 1;
        double totalData = 0;
        double previous = 0;
        double next = 0;
        String previousYear;
        String nextYear;
        ArrayList<RecordsModel> filteredRecordsList = new ArrayList<>();
        for (int i = 0; i < recordsModels.size() - 1; i++) {
            int year = yearSession + startYear;
            if (recordsModels.get(i).getQuarter().contains(year + "-Q" + quarter)) {
                totalData += recordsModels.get(i).getVolumeOfMobileData();
                quarter++;
                previousYear = recordsModels.get(i).getQuarter().substring(0, 4);
                nextYear = recordsModels.get(i + 1).getQuarter().substring(0, 4);
                if (previousYear.equals(nextYear)) {
                    previous = recordsModels.get(i).getVolumeOfMobileData();
                    next = recordsModels.get(i + 1).getVolumeOfMobileData();
                }
                //One year have four quarters.
                if (quarter > 4) {
                    if (previous > next) {
                        recordsModels.get(i).setIsDecreaseInVolume("true");
                    }
                    recordsModels.get(i).setTotalVolumeOfMobileData(totalData);
                    recordsModels.get(i).setYear(recordsModels.get(i).getQuarter().substring(0, 4));
                    filteredRecordsList.add(recordsModels.get(i));
                    startYear++;
                    quarter = 1;
                    totalData = 0;
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

    @Override
    public void onWorkOffline() {
        homeView.populateData(dataBaseHandler.getAllRecords());
        Toast.makeText(context, context.getString(R.string.network_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWorkOnline() {
        if (isBounded) {
            connection.getmService().setDataNotificationCallback(this);
            connection.getmService().new FetchJsonData().execute("https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=56");
        }
    }

}
