package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;
import assignment.coding.com.networkdataviewer.network.Connection;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;

public interface HomeMVP {


    interface View extends BaseMVP.View {

        void populateData(ArrayList<RecordsModel> networkDataModelList);

        void onNotifyAdapter();

        void onNavigateToDetails(@NonNull ArrayList<NetworkDataModel> models);

        void serviceBoundStatus(boolean isBound, Connection connection);

    }

    interface Presenter extends BaseMVP.Presenter {

        void onWorkOffline();

        void onWorkOnline();


    }
}
