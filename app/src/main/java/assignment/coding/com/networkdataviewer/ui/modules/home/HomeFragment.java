package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;
import assignment.coding.com.networkdataviewer.network.Connection;
import assignment.coding.com.networkdataviewer.ui.adapter.RecyclerViewAdapter;
import assignment.coding.com.networkdataviewer.ui.base.BaseFragment;


public class HomeFragment extends BaseFragment<HomeMVP.View, HomePresenter> implements HomeMVP.View {


    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;

    ArrayList<RecordsModel> recordsModelArrayList = new ArrayList<>();

    boolean isLoading = false;
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
        //No operation here for future use.
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        initAdapter();
        initScrollListener();

    }


    private void initAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(recordsModelArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == recordsModelArrayList.size() - 1) {
                        //bottom of list!
                        getPresenter().onLoadMore();
                        isLoading = true;
                    }
                }
            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null && isBounded)
            getActivity().unbindService(connection);
    }

    @Override
    public void populateData(ArrayList<RecordsModel> recordsModelArrayList) {
        recyclerViewAdapter.setRecordsModelList(recordsModelArrayList);
    }

    @Override
    public void onNotifyAdapter(@Nullable ArrayList<RecordsModel> networkDataModels) {
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyItemInserted(int position) {
        recyclerViewAdapter.notifyItemInserted(position);
    }

    @Override
    public void notifyItemRemoved(int position) {
        recyclerViewAdapter.notifyItemRemoved(position);
    }


    @Override
    public void onNavigateToDetails(@NonNull ArrayList<NetworkDataModel> models) {

    }

    @Override
    public void isLoadingFlag(boolean isLoading) {
        this.isLoading = isLoading;
    }

    @Override
    public void serviceBoundStatus(boolean isBound, Connection connection) {
        this.isBounded = isBound;
        this.connection = connection;
    }
}

