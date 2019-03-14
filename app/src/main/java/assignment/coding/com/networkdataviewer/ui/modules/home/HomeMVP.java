package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;

public interface HomeMVP {


    interface View extends BaseMVP.View {

        void onNotifyAdapter(@Nullable ArrayList<NetworkDataModel> networkDataModels, int page);

        void  onLoadMore();

        void onNavigateToDetails(@NonNull ArrayList<NetworkDataModel> models);

    }

    interface Presenter extends BaseMVP.Presenter {

        void onWorkOffline(boolean workOffline);

    }
}
