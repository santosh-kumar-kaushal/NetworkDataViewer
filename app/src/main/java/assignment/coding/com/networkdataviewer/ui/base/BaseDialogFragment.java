package assignment.coding.com.networkdataviewer.ui.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evernote.android.state.StateSaver;

import androidx.annotation.StringRes;
import assignment.coding.com.networkdataviewer.ui.base.mvp.BaseMVP;
import assignment.coding.com.networkdataviewer.ui.base.mvp.presenter.BasePresenter;


public abstract class BaseDialogFragment<V extends BaseMVP.View, P extends BasePresenter<V>> extends DialogFragment implements BaseMVP.View {
    protected BaseMVP.View callback;

    protected boolean suppressAnimation = false;

    @LayoutRes
    protected abstract int fragmentLayout();

    protected abstract void onFragmentCreated(@NonNull View view, @Nullable Bundle savedInstanceState);

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseMVP.View) {
            callback = (BaseMVP.View) context;
        }
    }

    @Override public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
        getPresenter().onSaveInstanceState(outState);
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            StateSaver.restoreInstanceState(this, savedInstanceState);
            getPresenter().onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void dismiss() {
        if (suppressAnimation) {
            super.dismiss();
            return;
        }
    }

    @SuppressLint("RestrictedApi") @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (fragmentLayout() != 0) {
            View view = inflater.inflate(fragmentLayout(), container, false);
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onFragmentCreated(view, savedInstanceState);
    }

    @Override public void showProgress(@StringRes int resId) {
        callback.showProgress(resId);
    }

    @Override public void showBlockingProgress(int resId) {
        callback.showBlockingProgress(resId);
    }

    @Override public void hideProgress() {
        callback.hideProgress();
    }

    @Override public void showMessage(@StringRes int titleRes, @StringRes int msgRes) {
        callback.showMessage(titleRes, msgRes);
    }

    @Override public void showMessage(@NonNull String titleRes, @NonNull String msgRes) {
        callback.showMessage(titleRes, msgRes);
    }

    @Override public void showErrorMessage(@NonNull String msgRes) {
        callback.showErrorMessage(msgRes);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
    }

    public BasePresenter getPresenter() {
        return new BasePresenter();
    }
}
