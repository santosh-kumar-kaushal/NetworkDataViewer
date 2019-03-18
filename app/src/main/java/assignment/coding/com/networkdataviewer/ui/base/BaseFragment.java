package assignment.coding.com.networkdataviewer.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;
import assignment.coding.com.networkdataviewer.ui.base.mvp.presenter.BasePresenter;
import assignment.coding.com.networkdataviewer.ui.widgets.dialog.ProgressDialogFragment;

/**
 * Base class for all the fragments used in project, it must extend this class.
 *
 * @param <V> view contract.
 * @param <P> presenter.
 */
public abstract class BaseFragment<V extends BaseMVP.View, P extends BasePresenter<V>> extends Fragment implements BaseMVP.View {

    /**
     * Flag which maintains the state of  progress bar.
     */
    private boolean isProgressShowing;
    /**
     * Callback to the view.
     */
    protected BaseMVP.View callback;

    /**
     * Layout to be displayed to UI.
     *
     * @return layout id.
     */
    @LayoutRes
    protected abstract int fragmentLayout();

    /**
     * Getter for presenter.
     *
     * @return {@link BaseMVP.Presenter}
     */
    protected abstract BaseMVP.Presenter getPresenter();

    /**
     * Callback when fragment is created.
     *
     * @param view               view.
     * @param savedInstanceState bundle.
     */
    protected abstract void onFragmentCreated(@NonNull View view, @Nullable Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseMVP.View) {
            callback = (BaseMVP.View) context;
            getPresenter();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            getPresenter().onRestoreInstanceState(savedInstanceState);
        }
    }

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (fragmentLayout() != 0) {
            return inflater.inflate(fragmentLayout(), container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onFragmentCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress(@StringRes int resId) {
        callback.showProgress(resId);
    }


    @Override
    public void showMessage(@StringRes int titleRes, @StringRes int msgRes) {
        callback.showMessage(titleRes, msgRes);
    }

    @Override
    public void showMessage(@NonNull String titleRes, @NonNull String msgRes) {
        callback.showMessage(titleRes, msgRes);
    }

    @Override
    public void showErrorMessage(@NonNull String msgRes) {
        callback.showErrorMessage(msgRes);
    }

    /**
     * This method checks if view is safe/ not in destroying state.
     *
     * @return safe view.
     */
    protected boolean isSafe() {
        return getView() != null && getActivity() != null && !getActivity().isFinishing();
    }
    /**
     * Method shows progress on the UI.
     *
     * @param resId resource ID.
     *
     * @param cancelable flag which is used for making alert dialog cancelable/non cancelable.
     */
    public void showProgressBar(int resId, boolean cancelable) {
        String msg = getString(R.string.in_progress);
        if (resId != 0) {
            msg = getString(resId);
        }
        if (!isProgressShowing && !getActivity().isFinishing()) {
            ProgressDialogFragment fragment = (ProgressDialogFragment)getActivity(). getSupportFragmentManager().findFragmentByTag(
                    ProgressDialogFragment.TAG);
            if (fragment == null) {
                isProgressShowing = true;
                fragment = ProgressDialogFragment.newInstance(msg, cancelable);
                fragment.show(getActivity().getSupportFragmentManager(), ProgressDialogFragment.TAG);
            }
        }
    }

    @Override
    public void hideProgress() {
        ProgressDialogFragment fragment = (ProgressDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag(ProgressDialogFragment.TAG);
        if (fragment != null) {
            fragment.dismiss();
            isProgressShowing = false;
        }
    }

}
