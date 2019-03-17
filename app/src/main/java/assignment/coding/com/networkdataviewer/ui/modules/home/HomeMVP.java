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

        void onNotifyAdapter(@Nullable ArrayList<RecordsModel> networkDataModels);

        void notifyItemInserted(int position);

        void notifyItemRemoved(int position);

        void onNavigateToDetails(@NonNull ArrayList<NetworkDataModel> models);

        void isLoadingFlag(boolean isLoading);

        void serviceBoundStatus(boolean isBound, Connection connection);

    }

    interface Presenter extends BaseMVP.Presenter {

        void  onLoadMore();

    }
}
