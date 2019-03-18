package assignment.coding.com.networkdataviewer.ui.widgets.dialog;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.ui.base.BaseDialogFragment;
import assignment.coding.com.networkdataviewer.ui.base.mvp.presenter.BasePresenter;

/**
 * Progress dialog to display progess on screen.
 */
public class ProgressDialogFragment extends BaseDialogFragment {
    /**
     * Constructor.
     */
    public ProgressDialogFragment() {
        suppressAnimation = true;
    }

    /**
     * Logger.
     */
    public static final String TAG = ProgressDialogFragment.class.getSimpleName();

    /**
     * New instance of {@link ProgressDialogFragment}.
     *
     * @param resources    resource.
     * @param msgId        message.
     * @param isCancelable
     * @return {@link ProgressDialogFragment}.
     */
    @NonNull
    public static ProgressDialogFragment newInstance(@NonNull Resources resources, @StringRes int msgId, boolean isCancelable) {
        return newInstance(resources.getString(msgId), isCancelable);
    }

    @NonNull
    public static ProgressDialogFragment newInstance(@NonNull String msg, boolean isCancelable) {
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        return fragment;
    }

    @Override
    protected int fragmentLayout() {
        return R.layout.progress_dialog_layout;
    }

    @Override
    protected void onFragmentCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(false);
        setCancelable(false);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setDimAmount(0);
        }
        return dialog;
    }

    @NonNull
    public BasePresenter getPresenter() {
        return new BasePresenter();
    }
}
