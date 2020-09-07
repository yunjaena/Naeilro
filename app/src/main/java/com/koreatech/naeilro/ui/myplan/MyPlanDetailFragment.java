package com.koreatech.naeilro.ui.myplan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;
import com.koreatech.naeilro.network.interactor.MyPlanRestInteractor;
import com.koreatech.naeilro.ui.myplan.adapter.MyPlanNodelAdapter;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanDetailPresenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyPlanDetailFragment extends Fragment implements MyPlanDetailContract.View {
    private View view;
    private MyPlanDetailPresenter myPlanDetailPresenter;
    private RecyclerView myPlanNodeRecyclerView;
    private MyPlanNodelAdapter myPlanDetailAdapter;
    private int removeIndex = -1;
    private List<MyPlanNode> nodeList = new ArrayList<>();
    private NavController navController;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_plan_detail, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
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
        myPlanDetailAdapter = new MyPlanNodelAdapter(myPlanList,getContext());
        myPlanNodeRecyclerView.setAdapter(myPlanDetailAdapter);
        myPlanDetailAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(view.getId() == R.id.node_cancel_button){
                    removeIndex = position;
                    myPlanDetailPresenter.deleteNode(nodeList.get(position).getNodeNumber(), nodeList.get(position).getContentType(), nodeList.get(position).getContendID());
                }else{
                    String contentTypeId = nodeList.get(position).getContentType();
                    int contentId = Integer.parseInt(nodeList.get(position).getContendID());
                    Bundle bundle = new Bundle();
                    bundle.putString("title", nodeList.get(position).getTitle());
                    bundle.putInt("contentId", contentId);
                    if(contentTypeId.equals("12")){//관광지
                        navController.navigate(R.id.action_navigation_my_plan_detail_to_navigation_tourspot_detail, bundle);
                    }
                    else if(contentTypeId.equals("14")){//문화시설
                        navController.navigate(R.id.action_navigation_my_plan_detail_to_navigation_detail_facility, bundle);
                    }
                    else if(contentTypeId.equals("15")){//축제/행사
                        navController.navigate(R.id.action_navigation_my_plan_detail_to_navigation_detail_festival, bundle);
                    }
                    else if(contentTypeId.equals("28")){//레포츠
                        navController.navigate(R.id.action_navigation_my_plan_detail_to_navigation_detail_reports, bundle);
                    }
                    else if(contentTypeId.equals("32")){//숙박
                        navController.navigate(R.id.action_navigation_my_plan_detail_to_navigation_house_detail, bundle);
                    }
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void showMessage(String message) {
        ToastUtil.getInstance().makeShort(message);
        nodeList.remove(removeIndex);
        myPlanDetailAdapter.notifyDataSetChanged();

    }

    @Override
    public void failDeleteNode() {
        ToastUtil.getInstance().makeShort(R.string.my_plan_delete_fail);
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
        nodeList.addAll(myPlanList);
        initRecyclerView(nodeList);

    }

    @Override
    public void failGetPlanInfo() {
        ToastUtil.getInstance().makeShort(R.string.my_plan_get_plan_fail);
    }

    @Override
    public void setPresenter(MyPlanDetailPresenter presenter) {

    }
}
