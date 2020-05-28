package com.koreatech.naeilro.ui.train;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.traincitycode.TrainCityInfo;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationInfo;
import com.koreatech.naeilro.network.interactor.TrainRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.train.presenter.TrainInfoFragmentContract;
import com.koreatech.naeilro.ui.train.presenter.TrainInfoFragmentPresenter;
import com.koreatech.naeilro.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;


public class TrainInfoFragment extends Fragment implements TrainInfoFragmentContract.View, TrainStationSelectDialog.DialogCallback, View.OnClickListener {
    public static final String TAG = "TrainInfoFragment";
    private TrainInfoFragmentPresenter trainInfoFragmentPresenter;
    private List<TrainInfo> trainInfoList;
    private List<TrainStationInfo> trainStationInfoList;
    private TextView departStationTextView;
    private TextView arrivalStationTextView;
    private TextView departDateTextView;
    private TextView trainTypeTextView;
    private int departStation;
    private int arrivalStation;
    private String departDate;
    private int trainType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_train_info, container, false);
        init(view);
        trainInfoFragmentPresenter.getTrainList();
        trainInfoFragmentPresenter.getTrainStationList();
        return view;
    }

    public void init(View view) {
        trainInfoFragmentPresenter = new TrainInfoFragmentPresenter(this, new TrainRestInteractor());
        trainInfoList = new ArrayList<>();
        trainStationInfoList = new ArrayList<>();
        departStationTextView = view.findViewById(R.id.train_depart_station_text_view);
        arrivalStationTextView = view.findViewById(R.id.train_arrival_station_text_view);
        departDateTextView = view.findViewById(R.id.train_depart_date_text_view);
        trainTypeTextView = view.findViewById(R.id.train_type_text_view);
        departDateTextView.setOnClickListener(this);
        arrivalStationTextView.setOnClickListener(this);
        trainTypeTextView.setOnClickListener(this);
        departStationTextView.setOnClickListener(this);
        departDate = TimeUtil.getTodayDate();
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading_station_info);
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
        if (trainInfoList != null) {
            this.trainInfoList.clear();
            this.trainInfoList.addAll(trainInfoList);
            trainType = 0;
        }
    }

    @Override
    public void showTrainStationList(List<TrainStationInfo> trainStationInfoList) {
        if (trainStationInfoList != null) {
            this.trainStationInfoList.clear();
            this.trainStationInfoList.addAll(trainStationInfoList);
            if (trainStationInfoList.size() >= 2) {
                departStation = 0;
                arrivalStation = 1;
                updateUI();
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.train_depart_station_text_view:
                openTrainStationSelectDialog(true);
                break;
            case R.id.train_arrival_station_text_view:
                openTrainStationSelectDialog(false);
                break;
            case R.id.train_depart_date_text_view:
                break;
            case R.id.train_type_text_view:
                break;
        }
    }

    private void updateUI() {
        departStationTextView.setText(trainStationInfoList.get(departStation).getStationName());
        arrivalStationTextView.setText(trainStationInfoList.get(arrivalStation).getStationName());
        trainTypeTextView.setText(trainInfoList.get(trainType).getVehicleName());
        departDateTextView.setText(departDate);
    }

    @Override
    public void showTrainCityList(List<TrainCityInfo> trainCityInfoList) {
        Log.d(TAG, "showTrainCityList: " + trainCityInfoList.get(0).getCityName());
    }

    @Override
    public void setPresenter(TrainInfoFragmentPresenter presenter) {
        this.trainInfoFragmentPresenter = presenter;
    }

    public void openTrainStationSelectDialog(boolean isDepart) {
        TrainStationSelectDialog trainStationSelectDialog = new TrainStationSelectDialog(getActivity(), isDepart, this, trainStationInfoList);
        trainStationSelectDialog.show();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(trainStationSelectDialog.getWindow().getAttributes());
        lp.width = (int) (size.x * 0.9f);
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        trainStationSelectDialog.show();
        Window window = trainStationSelectDialog.getWindow();
        window.setAttributes(lp);
    }

    @Override
    public void stationItemSelect(int index, boolean isDepart) {
        if (isSameStation(index, isDepart)) {
            showMessage(R.string.train_depart_arrival_station_same_warning);
            return;
        }

        if (isDepart)
            departStation = index;
        else
            arrivalStation = index;
        updateUI();
    }

    public boolean isSameStation(int compareStation, boolean isDepart) {
        if (isDepart) {
            return departStation == compareStation;
        } else {
            return arrivalStation == compareStation;
        }

    }
}
