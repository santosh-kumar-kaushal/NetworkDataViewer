package assignment.coding.com.networkdataviewer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomeRecyclerViewAdapterTest {

    @Mock
    HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    @Mock
    HomeRecyclerViewAdapter.ItemViewHolder viewHolder;

    @Mock  RecyclerView mRecyclerView;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        homeRecyclerViewAdapter=null;
    }


    @Test
    public void testSetAdapter() {
        mRecyclerView.setAdapter(homeRecyclerViewAdapter);
        verify(mRecyclerView).setAdapter(any(HomeRecyclerViewAdapter.class));
    }

    @Test
    public void setRecordsModelList() {
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {
    }

    @Test
    public void getItemCount() {
    }

    @Test
    public void getItemViewType() {
    }

    @Test
    public void populateItemRows() {
        homeRecyclerViewAdapter.populateItemRows(viewHolder,2);
    }
}