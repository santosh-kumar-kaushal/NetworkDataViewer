package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;
import assignment.coding.com.networkdataviewer.network.Connection;
import assignment.coding.com.networkdataviewer.ui.adapter.RecyclerViewAdapter;
import assignment.coding.com.networkdataviewer.ui.base.BaseFragment;
import assignment.coding.com.networkdataviewer.utils.NetworkUtil;


public class HomeFragment extends BaseFragment<HomeMVP.View, HomePresenter> implements HomeMVP.View {


    private RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;

    private ArrayList<RecordsModel> recordsModelArrayList = new ArrayList<>();

    private boolean isBounded;
    private ServiceConnection connection;

    @Override
    protected int fragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeMVP.Presenter getPresenter() {
        return new HomePresenter(this, getActivity());
    }


    @Override
    protected void onFragmentCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            if (!NetworkUtil.isNetworkReachable(getActivity())) {
                getPresenter().onWorkOffline();
            } else {
                getPresenter().onWorkOnline();
            }
        }
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        initAdapter();

    }


    private void initAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(recordsModelArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null && isBounded)
            getActivity().unbindService(connection);
    }

    @Override
    public void populateData(ArrayList<RecordsModel> recordsModelArrayList) {
        if(recordsModelArrayList!=null && recyclerViewAdapter!=null) {
            this.recordsModelArrayList = recordsModelArrayList;
            recyclerViewAdapter.setRecordsModelList(recordsModelArrayList);
        }
    }

    @Override
    public void onNotifyAdapter() {
        recyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onNavigateToDetails(@NonNull ArrayList<NetworkDataModel> models) {

    }

    @Override
    public void serviceBoundStatus(boolean isBound, Connection connection) {
        this.isBounded = isBound;
        this.connection = connection;
    }
}

