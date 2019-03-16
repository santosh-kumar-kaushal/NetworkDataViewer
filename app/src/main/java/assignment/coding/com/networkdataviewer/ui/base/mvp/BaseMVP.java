package assignment.coding.com.networkdataviewer.ui.base.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public interface BaseMVP {


    interface View {

        void showProgress(@StringRes int resId);

        void hideProgress();

        void showMessage(@StringRes int titleRes, @StringRes int msgRes);

        void showMessage(@NonNull String titleRes, @NonNull String msgRes);

        void showErrorMessage(@NonNull String msgRes);
    }

    interface Presenter {

        void onSaveInstanceState(Bundle outState);

        void onRestoreInstanceState(Bundle outState);

    }
}
