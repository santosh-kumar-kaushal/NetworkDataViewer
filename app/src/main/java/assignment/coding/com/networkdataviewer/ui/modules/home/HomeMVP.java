package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;
import assignment.coding.com.networkdataviewer.network.Connection;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;

/**
 * Contract between {@link HomeFragment} and {@link HomePresenter}.
 */
public interface HomeMVP extends BaseMVP{

    /**
     * View.
     */
    interface View extends BaseMVP.View {
        /**
         * Method populates data on the view.
         *
         * @param networkDataModelList {@link ArrayList<RecordsModel>}.
         */
        void populateData(ArrayList<RecordsModel> networkDataModelList);

        /**
         * Notifies adapter when data is updated.
         */
        void onNotifyAdapter();

        /**
         * Callback when cell needs an click event to dispaly some other view/content.
         *
         * @param models {@link ArrayList<NetworkDataModel>}.
         */
        void onNavigateToDetails(@NonNull ArrayList<NetworkDataModel> models);

        /**
         * Callback for bound status of service.
         *
         * @param isBound    isBound.
         * @param connection connection.
         */
        void serviceBoundStatus(boolean isBound, Connection connection);

        /**
         * Method hides progress.
         */
        void hideProgressBar();



    }

    /**
     * Presenter.
     */
    interface Presenter extends BaseMVP.Presenter {
        /**
         * Method used for working in offline mechanism with cached data.
         */
        void onWorkOffline();

        /**
         * Method used for working in online mechanism live data.
         */
        void onWorkOnline();


    }
}
