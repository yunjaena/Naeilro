package com.koreatech.naeilro.ui.train;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.traincitycode.TrainCityInfo;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;
import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchInfo;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationInfo;
import com.koreatech.naeilro.network.interactor.TrainRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.train.presenter.TrainInfoFragmentContract;
import com.koreatech.naeilro.ui.train.presenter.TrainInfoFragmentPresenter;
import com.koreatech.naeilro.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class TrainInfoFragment extends Fragment implements TrainInfoFragmentContract.View, TrainStationSelectDialog.DialogCallback, TrainTypeSelectDialog.DialogCallback, View.OnClickListener {
    public static final String TAG = "TrainInfoFragment";
    public static final int TRAIN_ARRIVAL_REQUEST_CODE = 100;
    public static final int TRAIN_DEPARTURE_REQUEST_CODE = 101;
    public static final int TRAIN_CODE_REQUEST_CODE = 102;
    private TrainInfoFragmentPresenter trainInfoFragmentPresenter;
    private List<TrainInfo> trainInfoList;
    private List<TrainStationInfo> trainStationInfoList;
    private TextView departStationTextView;
    private TextView arrivalStationTextView;
    private TextView departDateTextView;
    private TextView trainTypeTextView;
    private TextView searchLinearLayout;
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
        searchLinearLayout = view.findViewById(R.id.train_search_linear_layout);
        departDateTextView.setOnClickListener(this);
        arrivalStationTextView.setOnClickListener(this);
        trainTypeTextView.setOnClickListener(this);
        departStationTextView.setOnClickListener(this);
        searchLinearLayout.setOnClickListener(this);
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
                openCalendarView();
                break;
            case R.id.train_type_text_view:
                openTrainTypeSelectDialog();
                break;
            case R.id.train_search_linear_layout:
                onClickedSearchButton();
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
//        TrainStationSelectDialog trainStationSelectDialog = new TrainStationSelectDialog(getActivity(), isDepart, this, trainStationInfoList);
//        trainStationSelectDialog.show();
//        Display display = getActivity().getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(trainStationSelectDialog.getWindow().getAttributes());
//        lp.width = (int) (size.x * 0.9f);
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//
//        trainStationSelectDialog.show();
//        Window window = trainStationSelectDialog.getWindow();
//        window.setAttributes(lp);


        Intent intent = new Intent(getActivity(), TrainStationBottomSheetActivity.class);
        intent.putExtra("RECYCLER_VIEW_TEXT", "기차역");
        intent.putStringArrayListExtra("LIST", getTrainStationList());
        if (isDepart) {
            intent.putExtra("TITLE", "출발역");
            intent.putExtra("INPUT", "역명을 입력해주세요");
            startActivityForResult(intent, TRAIN_DEPARTURE_REQUEST_CODE);
        } else {
            intent.putExtra("title", "도착역");
            intent.putExtra("INPUT", "역명을 입력해주세요");
            startActivityForResult(intent, TRAIN_ARRIVAL_REQUEST_CODE);
        }

    }

    private ArrayList<String> getTrainStationList() {
        ArrayList<String> trainStationList = new ArrayList<>();
        if (trainStationInfoList != null) {
            for (TrainStationInfo trainStation : trainStationInfoList) {
                trainStationList.add(trainStation.getStationName());
            }
        }
        return trainStationList;
    }

    public void openTrainTypeSelectDialog() {
        TrainTypeSelectDialog trainTypeSelectDialog = new TrainTypeSelectDialog(getActivity(), this, trainInfoList);
        trainTypeSelectDialog.show();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(trainTypeSelectDialog.getWindow().getAttributes());
        lp.width = (int) (size.x * 0.9f);
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        trainTypeSelectDialog.show();
        Window window = trainTypeSelectDialog.getWindow();
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

    @Override
    public void showTrainSearchList(List<TrainSearchInfo> trainSearchInfoList) {
        TrainSearchDialog trainSearchDialog = new TrainSearchDialog(getActivity(), trainSearchInfoList);
        trainSearchDialog.show();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(trainSearchDialog.getWindow().getAttributes());
        lp.width = (int) (size.x * 0.9f);
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        trainSearchDialog.show();
        Window window = trainSearchDialog.getWindow();
        window.setAttributes(lp);
    }


    public void onClickedSearchButton() {
        String arrivalCode = trainStationInfoList.get(arrivalStation).getStationCode();
        String departCode = trainStationInfoList.get(departStation).getStationCode();
        String dateString[] = departDate.split("-");
        String departDate = String.format(Locale.KOREA, "%04d%02d%02d", Integer.parseInt(dateString[0]), Integer.parseInt(dateString[1]), Integer.parseInt(dateString[2]));
        String trainCode = trainInfoList.get(trainType).getVehicleId();
        trainInfoFragmentPresenter.getTrainSearchList(departCode, arrivalCode, departDate, trainCode);
    }


    @Override
    public void trainItemSelect(int index) {
        trainType = index;
        updateUI();
    }

    public boolean isSameStation(int compareStation, boolean isDepart) {
        if (isDepart) {
            return arrivalStation == compareStation;
        } else {
            return departStation == compareStation;
        }
    }

    private void openCalendarView() {
        String[] dateSplitString = departDate.split("-");
        int year = Integer.parseInt(dateSplitString[0]);
        int month = Integer.parseInt(dateSplitString[1]);
        int day = Integer.parseInt(dateSplitString[2]);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view, yearSelect, monthSelect, daySelect) -> {
            departDate = String.format(Locale.KOREA, "%04d-%02d-%02d", yearSelect, monthSelect + 1, daySelect);
            updateUI();
        }, year, month - 1, day);

        datePickerDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ToastUtil.getInstance().makeShort(requestCode + " : " + resultCode);
        if (resultCode == RESULT_OK && data != null) {
            int selectIndex = data.getIntExtra("RESULT", 0);
            switch (requestCode) {
                case TRAIN_ARRIVAL_REQUEST_CODE:
                    stationItemSelect(selectIndex, false);
                    break;
                case TRAIN_DEPARTURE_REQUEST_CODE:
                    stationItemSelect(selectIndex, true);
                    break;
                case TRAIN_CODE_REQUEST_CODE:
                    break;
            }
        }
    }
}
