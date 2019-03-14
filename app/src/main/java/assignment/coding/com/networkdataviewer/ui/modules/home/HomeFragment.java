package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.callbacks.ConnectionCallback;
import assignment.coding.com.networkdataviewer.callbacks.DataNotificationCallback;
import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.network.BackendService;
import assignment.coding.com.networkdataviewer.network.Connection;
import assignment.coding.com.networkdataviewer.ui.base.BaseFragment;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;


public class HomeFragment extends BaseFragment<HomeMVP.View,HomePresenter> implements HomeMVP.View,DataNotificationCallback, ConnectionCallback {

    private Connection connection;
    private boolean isBounded;

    @Override
    protected int fragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected BaseMVP.Presenter getPresenter() {
        return new HomePresenter();
    }


    @Override
    protected void onFragmentCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = new Intent(getActivity(), BackendService.class);
        connection = new Connection();
        Objects.requireNonNull(getActivity()).bindService(intent, connection, Context.BIND_AUTO_CREATE);
        connection.setConnectionCallback(this);

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onReceive(String response) {
        parseJsonAsObject(response);
    }


    /**
     * Method used to parse user information from json string from server.
     *
     * @param jsonString response from server.
     */
    private void parseJsonAsObject(String jsonString) {
    }

    @Override
    public void onServiceBound(boolean isBounded) {
        this.isBounded = isBounded;
        if (isBounded) {
            connection.getmService().setDataNotificationCallback(this);
            connection.getmService().new FetchJsonData().execute("pass url here");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBounded)
            Objects.requireNonNull(getActivity()).unbindService(connection);
    }

    @Override
    public void onNotifyAdapter(@Nullable ArrayList<NetworkDataModel> networkDataModels, int page) {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onNavigateToDetails(@NonNull ArrayList<NetworkDataModel> models) {

    }
}

