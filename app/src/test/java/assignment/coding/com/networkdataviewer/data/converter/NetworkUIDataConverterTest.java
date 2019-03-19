package assignment.coding.com.networkdataviewer.data.converter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import assignment.coding.com.networkdataviewer.data.model.NetworkDataModel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class NetworkUIDataConverterTest {

    private NetworkUIDataConverter networkUIDataConverter;

    private String data;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        networkUIDataConverter = mock(NetworkUIDataConverter.class);
        data = "{\"help\": \"https://data.gov.sg/api/3/action/help_show?name=datastore_search\", \"success\": true, \"result\": {\"resource_id\": \"a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"fields\": [{\"type\": \"int4\", \"id\": \"_id\"}, {\"type\": \"text\", \"id\": \"quarter\"}, {\"type\": \"numeric\", \"id\": \"volume_of_mobile_data\"}], \"records\": [{\"volume_of_mobile_data\": \"0.000384\", \"quarter\": \"2004-Q3\", \"_id\": 1}, {\"volume_of_mobile_data\": \"0.000543\", \"quarter\": \"2004-Q4\", \"_id\": 2}, {\"volume_of_mobile_data\": \"0.00062\", \"quarter\": \"2005-Q1\", \"_id\": 3}, {\"volume_of_mobile_data\": \"0.000634\", \"quarter\": \"2005-Q2\", \"_id\": 4}, {\"volume_of_mobile_data\": \"0.000718\", \"quarter\": \"2005-Q3\", \"_id\": 5}], \"_links\": {\"start\": \"/api/action/datastore_search?limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"next\": \"/api/action/datastore_search?offset=5&limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\"}, \"limit\": 5, \"total\": 56}}";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testParseJsonAsObject() {
       NetworkDataModel networkDataModel= networkUIDataConverter.parseJsonAsObject(data);
        assertNotNull(networkDataModel);
    }

    @Test
    public void testParseJsonAsObjectAsNull() {
        NetworkDataModel networkDataModel= networkUIDataConverter.parseJsonAsObject(null);
        assertNull(networkDataModel);
        assertEquals(4, networkDataModel.getResultsModel().getRecordsModelList().size());
    }
}