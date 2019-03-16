package assignment.coding.com.networkdataviewer.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;
import assignment.coding.com.networkdataviewer.ui.base.mvp.presenter.BasePresenter;
import assignment.coding.com.networkdataviewer.ui.widgets.dialog.ProgressDialogFragment;

public abstract class BaseActivity<V extends BaseMVP.View, P extends BasePresenter<V>> extends AppCompatActivity implements BaseMVP.View {

    Bundle presenterStateBundle = new Bundle();

    private boolean isProgressShowing;

    protected abstract BaseMVP.Presenter getPresenter();

    @LayoutRes
    protected abstract int activityLayout();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activityLayout());
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            getPresenter().onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(presenterStateBundle);
    }


    @Override
    public void showMessage(@StringRes int titleRes, @StringRes int msgRes) {
        showMessage(getString(titleRes), getString(msgRes));
    }

    @Override
    public void showMessage(@NonNull String titleRes, @NonNull String msgRes) {
        hideProgress();
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(@NonNull String msgRes) {
        showMessage(getString(R.string.error), msgRes);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgress(@StringRes int resId) {
        showProgress(R.string.in_progress,false);
    }



    /**
     * Methods adds {@link Fragment} to {@link BaseActivity}.
     */
    protected void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.container, fragment).commit();
    }

    private void showProgress(int resId, boolean cancelable) {
        String msg = getString(R.string.in_progress);
        if (resId != 0) {
            msg = getString(resId);
        }
        if (!isProgressShowing && !isFinishing()) {
            ProgressDialogFragment fragment = (ProgressDialogFragment) getSupportFragmentManager().findFragmentByTag(
                    ProgressDialogFragment.TAG);
            if (fragment == null) {
                isProgressShowing = true;
                fragment = ProgressDialogFragment.newInstance(msg, cancelable);
                fragment.show(getSupportFragmentManager(), ProgressDialogFragment.TAG);
            }
        }
    }

    @Override
    public void hideProgress() {
        ProgressDialogFragment fragment = (ProgressDialogFragment) getSupportFragmentManager().findFragmentByTag(ProgressDialogFragment.TAG);
        if (fragment != null) {
            fragment.dismiss();
            isProgressShowing = false;
        }
    }
}
