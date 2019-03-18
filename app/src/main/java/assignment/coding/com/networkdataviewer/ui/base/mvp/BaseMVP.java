package assignment.coding.com.networkdataviewer.ui.base.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * BaseMVP is a base class which will be used by all MVP's classes used in project.
 */
public interface BaseMVP {

    /**
     * View.
     */
    interface View {
        /**
         * Method shows progress bar.
         *
         * @param resId resource string id.
         */
        void showProgress(@StringRes int resId);

        /**
         * Hide progress.
         */
        void hideProgress();

        /**
         * Method shows alert message.
         *
         * @param titleRes title for the alert.
         * @param msgRes   resource id for alert.
         */
        void showMessage(@StringRes int titleRes, @StringRes int msgRes);

        /**
         * Method shows alert message.
         *
         * @param titleRes title for the alert.
         * @param msgRes   text for the alert.
         */
        void showMessage(@NonNull String titleRes, @NonNull String msgRes);

        /**
         * Method shows error message.
         *
         * @param msgRes resource ID.
         */
        void showErrorMessage(@NonNull String msgRes);
    }

    /**
     * Presenter.
     */
    interface Presenter {
        /**
         * Method saves required data for orientation change support.
         *
         * @param outState bundle state.
         */
        void onSaveInstanceState(Bundle outState);

        /**
         * Method restore required data for orientation change support.
         *
         * @param outState bundle state.
         */
        void onRestoreInstanceState(Bundle outState);

    }
}
