package com.koreatech.naeilro.ui.train;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.traincitycode.TrainCityInfo;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;
import com.koreatech.naeilro.network.interactor.TrainRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.train.presenter.TrainInfoFragmentContract;
import com.koreatech.naeilro.ui.train.presenter.TrainInfoFragmentPresenter;

import java.util.List;


public class TrainInfoFragment extends Fragment implements TrainInfoFragmentContract.View {
    public static final String TAG = "TrainInfoFragment";
    private TrainInfoFragmentPresenter trainInfoFragmentPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        init();
        trainInfoFragmentPresenter.getTrainList();
        trainInfoFragmentPresenter.getTrainCityList();
        return inflater.inflate(R.layout.fragment_train_info, container, false);
    }

    public void init() {
        trainInfoFragmentPresenter = new TrainInfoFragmentPresenter(this, new TrainRestInteractor());
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void showMessage(String message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showMessage(int message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showTrainList(List<TrainInfo> trainInfoList) {

    }

    @Override
    public void showTrainCityList(List<TrainCityInfo> trainCityInfoList) {
        Log.d(TAG, "showTrainCityList: "+ trainCityInfoList.get(0).getCityName());
    }

    @Override
    public void setPresenter(TrainInfoFragmentPresenter presenter) {
        this.trainInfoFragmentPresenter = presenter;
    }
}
