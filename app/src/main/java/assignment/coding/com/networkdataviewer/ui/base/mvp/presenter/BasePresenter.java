package assignment.coding.com.networkdataviewer.ui.base.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.exceptions.DataFetchingException;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;
import assignment.coding.com.networkdataviewer.ui.widgets.dialog.ProgressDialogFragment;

/**
 * This is a base class for all the presenters where the common methods are available here.
 *
 * @param <V> view for the presenter.
 */
public class BasePresenter<V extends BaseMVP.View> implements BaseMVP.Presenter {

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Not used as of now.
    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        //Not used as of now.
    }

    /**
     * Error message which needs to be displayed for the occurred error.
     *
     * @param throwable throwable
     * @return id.
     */
    @StringRes
    public int getPrettifiedErrorMessage(@Nullable Throwable throwable) {
        int resId = R.string.network_error;
        if (throwable instanceof DataFetchingException) {
            resId = R.string.network_error;
        } else if (throwable instanceof IOException) {
            resId = R.string.request_error;
        } else if (throwable instanceof TimeoutException) {
            resId = R.string.unexpected_error;
        }
        return resId;
    }

}
