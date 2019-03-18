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
import assignment.coding.com.networkdataviewer.ui.adapter.HomeRecyclerViewAdapter;
import assignment.coding.com.networkdataviewer.ui.base.BaseFragment;
import assignment.coding.com.networkdataviewer.utils.NetworkUtil;

/**
 * Home fragment is responsible to display total consumed data on yearly basis.
 */
public class HomeFragment extends BaseFragment<HomeMVP.View, HomePresenter> implements HomeMVP.View {

    /**
     * Recycler view to display the data on rows.
     */
    private RecyclerView recyclerView;
    /**
     * Adapter which inflates the rows and data over the views.
     */
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    /**
     * List of RecordsModel.
     */
    private ArrayList<RecordsModel> recordsModelArrayList = new ArrayList<>();
    /**
     * Flag which maintains state for service connection bound.
     */
    private boolean isBounded;
    /**
     * Service connection.
     */
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
        showProgressBar(0,true);

    }


    private void initAdapter() {
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(recordsModelArrayList);
        recyclerView.setAdapter(homeRecyclerViewAdapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null && isBounded)
            getActivity().unbindService(connection);
    }

    @Override
    public void populateData(ArrayList<RecordsModel> recordsModelArrayList) {
        if (recordsModelArrayList != null && homeRecyclerViewAdapter != null) {
            this.recordsModelArrayList = recordsModelArrayList;
            homeRecyclerViewAdapter.setRecordsModelList(recordsModelArrayList);
        }
    }

    @Override
    public void onNotifyAdapter() {
        homeRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onNavigateToDetails(@NonNull ArrayList<NetworkDataModel> models) {
            //For future if decrease in volume cell needs click event.
    }

    @Override
    public void serviceBoundStatus(boolean isBound, Connection connection) {
        this.isBounded = isBound;
        this.connection = connection;
    }

    @Override
    public void hideProgressBar() {
        hideProgress();
    }
}

