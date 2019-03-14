package assignment.coding.com.networkdataviewer.ui.base;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;

public class MainActivity extends BaseActivity {

    @Override
    protected BaseMVP.Presenter getPresenter() {
        return null;
    }

    @Override
    protected int activityLayout() {
        return R.layout.activity_main;
    }

}
