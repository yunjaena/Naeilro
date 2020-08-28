package com.koreatech.naeilro.ui.myplan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;
import com.koreatech.naeilro.network.interactor.MyPlanRestInteractor;
import com.koreatech.naeilro.ui.myplan.adapter.MyPlanDetailAdapter;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanDetailPresenter;

import java.util.List;

public class MyPlanDetailFragment extends Fragment implements MyPlanDetailContract.View {
    private View view;
    private MyPlanDetailPresenter myPlanDetailPresenter;
    private RecyclerView myPlanNodeRecyclerView;
    private MyPlanDetailAdapter myPlanDetailAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_plan_detail, container, false);
        init(view);
        String planNumber = getArguments().getString("planNumber");
        myPlanDetailPresenter.getMyPlanNodeList(planNumber);

        return view;
    }
    public void init(View view){
        myPlanNodeRecyclerView = view.findViewById(R.id.my_plan_node_recycler_view);
        myPlanDetailPresenter = new MyPlanDetailPresenter(this, new MyPlanRestInteractor());



    }
    public void initRecyclerView(List<MyPlanNode> myPlanList){
        myPlanNodeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myPlanDetailAdapter = new MyPlanDetailAdapter(myPlanList,getContext());
        myPlanNodeRecyclerView.setAdapter(myPlanDetailAdapter);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMyPlanNode(List<MyPlanNode> myPlanList) {
        initRecyclerView(myPlanList);
    }

    @Override
    public void failGetPlanInfo() {

    }

    @Override
    public void setPresenter(MyPlanDetailPresenter presenter) {

    }
}
