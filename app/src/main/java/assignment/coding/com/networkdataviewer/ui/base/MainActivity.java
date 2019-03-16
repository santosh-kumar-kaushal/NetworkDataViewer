package assignment.coding.com.networkdataviewer.ui.base;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;
import assignment.coding.com.networkdataviewer.ui.modules.home.HomeFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected void onStart() {
        super.onStart();
        addFragment(new HomeFragment());
    }

    @Override
    protected BaseMVP.Presenter getPresenter() {
        return null;
    }

    @Override
    protected int activityLayout() {
        return R.layout.activity_main;
    }

}
