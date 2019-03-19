package assignment.coding.com.networkdataviewer.ui.base.mvp.presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import assignment.coding.com.networkdataviewer.exceptions.DataFetchingException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BasePresenterTest {

    @Mock
    private BasePresenter basePresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetPrettifiedErrorMessage()
    {
        basePresenter.getPrettifiedErrorMessage(new DataFetchingException("Error in fetching data"));
    }
}