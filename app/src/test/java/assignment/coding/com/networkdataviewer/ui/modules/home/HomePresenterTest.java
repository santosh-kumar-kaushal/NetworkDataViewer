package assignment.coding.com.networkdataviewer.ui.modules.home;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.data.converter.NetworkUIDataConverter;
import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Mock
    private HomePresenter homePresenter;
    @Mock
    private HomeMVP.View homeView;

    private String data;

    @Mock
    private NetworkUIDataConverter networkUIDataConverter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        data = "{\"help\": \"https://data.gov.sg/api/3/action/help_show?name=datastore_search\", \"success\": true, \"result\": {\"resource_id\": \"a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"fields\": [{\"type\": \"int4\", \"id\": \"_id\"}, {\"type\": \"text\", \"id\": \"quarter\"}, {\"type\": \"numeric\", \"id\": \"volume_of_mobile_data\"}], \"records\": [{\"volume_of_mobile_data\": \"0.000384\", \"quarter\": \"2004-Q3\", \"_id\": 1}, {\"volume_of_mobile_data\": \"0.000543\", \"quarter\": \"2004-Q4\", \"_id\": 2}, {\"volume_of_mobile_data\": \"0.00062\", \"quarter\": \"2005-Q1\", \"_id\": 3}, {\"volume_of_mobile_data\": \"0.000634\", \"quarter\": \"2005-Q2\", \"_id\": 4}, {\"volume_of_mobile_data\": \"0.000718\", \"quarter\": \"2005-Q3\", \"_id\": 5}], \"_links\": {\"start\": \"/api/action/datastore_search?limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"next\": \"/api/action/datastore_search?offset=5&limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\"}, \"limit\": 5, \"total\": 56}}";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPresenter() {
        Context context = mock(Context.class);
        homePresenter = new HomePresenter(homeView, context);
        assertNotNull(homePresenter);

    }

    @Test
    public void TestSuccess() {
        homePresenter.onSuccess();

    }

    @Test
    public void TestOnReceive() {
        homePresenter.onReceive(data);
    }

    @Test
    public void TestFilteredData() {
        NetworkDataModel networkDataModel = networkUIDataConverter.parseJsonAsObject(data);
        assertNotNull(networkDataModel);
        ArrayList<RecordsModel> recordsModels = homePresenter.filterYearDataForRecords(networkDataModel.getResultsModel().getRecordsModelList(), 200, 8, 18);
        assertEquals(4, recordsModels.size());
    }


}