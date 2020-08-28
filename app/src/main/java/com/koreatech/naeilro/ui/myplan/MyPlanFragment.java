package com.koreatech.naeilro.ui.myplan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;
import com.koreatech.naeilro.network.interactor.MyPlanRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.myplan.adapter.MyPlanCollectionAdapter;
import com.koreatech.naeilro.ui.myplan.adapter.MyPlanListAdapter;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanPresenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyPlanFragment extends Fragment implements MyPlanContract.View {
    private RecyclerView myPlanRecyclerView;
    private MyPlanListAdapter myPlanListAdapter;
    private MyPlanPresenter myPlanPresenter;
    private View view;
    private NavController navController;
    ArrayList<MyPlan> myPlans = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_plan, container, false);
        init(view);
        myPlanPresenter.getMyPlanCollectionList();
        return view;
    }
    public void init(View view){
        navController = Navigation.findNavController((MainActivity)getContext(), R.id.nav_host_fragment);
        myPlanRecyclerView = view.findViewById(R.id.my_plan_list_recycler_view);
        myPlanPresenter = new MyPlanPresenter(this, new MyPlanRestInteractor());

    }
    public void initRecyclerView(List<MyPlan> myPlanList){

        myPlanListAdapter = new MyPlanListAdapter(myPlanList);
        myPlanRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myPlanRecyclerView.setAdapter(myPlanListAdapter);
        myPlanListAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                Log.e("fragment", myPlans.get(position).getPlanNumber());
                bundle.putString("planNumber", myPlans.get(position).getPlanNumber());
                navController.navigate(R.id.action_navigation_my_plan_to_navigation_my_plan_detail,bundle);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });

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
    public void showMyPlanCollection(List<MyPlan> myPlanList) {
        myPlans.addAll(myPlanList);
        initRecyclerView(myPlanList);
    }

    @Override
    public void failGetPlanInfo() {

    }

    @Override
    public void setPresenter(MyPlanPresenter presenter) {
        this.myPlanPresenter = presenter;
    }
}
