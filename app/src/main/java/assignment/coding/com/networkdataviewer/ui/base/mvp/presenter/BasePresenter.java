package assignment.coding.com.networkdataviewer.ui.base.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.evernote.android.state.StateSaver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import androidx.annotation.StringRes;
import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.exceptions.DataFetchingException;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;

public class BasePresenter<V extends BaseMVP.View> implements BaseMVP.Presenter{


    @Override
    public void onSaveInstanceState(Bundle outState) {
        StateSaver.saveInstanceState(this, outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        if (outState != null) StateSaver.restoreInstanceState(this, outState);
    }

    @StringRes
    private int getPrettifiedErrorMessage(@Nullable Throwable throwable) {
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
