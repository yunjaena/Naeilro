package com.koreatech.naeilro.ui.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.search.SearchInfo;
import com.koreatech.naeilro.network.interactor.SearchRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.search.adapter.SearchAdapter;
import com.koreatech.naeilro.ui.search.presenter.SearchContract;
import com.koreatech.naeilro.ui.search.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchContract.View, RecyclerViewClickListener {
    private static final int[] CATEGORY_TYPE_LIST = {12, 14, 28, 14, 15};
    private static final int MAX_ITEM = 20;
    private RecyclerView searchResultRecyclerView;
    private Spinner searchSpinner;
    private LinearLayout searchNoResultLinearLayout;
    private SearchView searchView;
    private List<SearchInfo> searchInfoList;
    private int categoryType;
    private int pageNum;
    private SearchPresenter searchPresenter;
    private String currentSearchText;
    private LinearLayoutManager linearLayoutManager;
    private SearchAdapter searchAdapter;
    private NavController navController;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        init(root);
        return root;
    }

    private void init(View view) {
        pageNum = 1;
        currentSearchText = "";
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        searchNoResultLinearLayout = view.findViewById(R.id.search_no_result_linear_layout);
        searchPresenter = new SearchPresenter(this, new SearchRestInteractor());
        searchInfoList = new ArrayList<>();
        initSearchView(view);
        initSpinner(view);
        initRecyclerView(view);
    }


    public void searchText(String text) {
        if (text.isEmpty()) return;
        searchInfoList.clear();
        currentSearchText = text;
        pageNum = 1;
        searchPresenter.searchItemWithKeyword(currentSearchText, categoryType, pageNum);
    }

    private void initRecyclerView(View view) {
        searchResultRecyclerView = view.findViewById(R.id.search_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        searchAdapter = new SearchAdapter(searchInfoList);
        searchAdapter.setRecyclerViewClickListener(this);
        searchResultRecyclerView.setLayoutManager(linearLayoutManager);
        searchResultRecyclerView.setAdapter(searchAdapter);
        searchResultRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (lastVisible >= pageNum * MAX_ITEM - 1) {
                    pageNum += 1;
                    searchPresenter.searchItemWithKeyword(currentSearchText, categoryType, pageNum);
                }
            }
        });
    }


    private void initSpinner(View view) {
        searchSpinner = view.findViewById(R.id.search_spinner);
        searchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryType = CATEGORY_TYPE_LIST[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSearchView(View view) {
        searchView = view.findViewById(R.id.search_search_view);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty() && query.length() >= 2) {
                    hideKeyBoard(getActivity());
                    searchText(query);
                } else {
                    ToastUtil.getInstance().makeShort(R.string.input_more_than_two_letters_warning);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    private void hideKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showSearchInfoList(List<SearchInfo> searchInfoList) {
        searchNoResultLinearLayout.setVisibility(View.GONE);
        this.searchInfoList.addAll(searchInfoList);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyItem() {
        searchNoResultLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading);
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
    public void setPresenter(SearchPresenter presenter) {
        this.searchPresenter = presenter;
    }

    public void handleRecyclerViewClicked(int position) {
        Bundle bundle = new Bundle();
        String contentTypeId = String.valueOf(searchInfoList.get(position).getContentTypeID());
        bundle.putString("title", searchInfoList.get(position).getTitle());
        bundle.putInt("contentId", searchInfoList.get(position).getContentID());
        if (contentTypeId.equals("12")) {//관광지
            navController.navigate(R.id.action_navigation_home_to_navigation_tourspot_detail, bundle);
        } else if (contentTypeId.equals("14")) {//문화시설
            navController.navigate(R.id.action_navigation_home_to_navigation_detail_facility, bundle);
        } else if (contentTypeId.equals("15")) {//축제/행사
            navController.navigate(R.id.action_navigation_home_to_navigation_detail_festival, bundle);
        } else if (contentTypeId.equals("28")) {//레포츠
            navController.navigate(R.id.action_navigation_home_to_navigation_detail_reports, bundle);
        } else if (contentTypeId.equals("32")) {//숙박
            navController.navigate(R.id.action_navigation_home_to_navigation_house_detail, bundle);
        }
    }

    @Override
    public void onClick(View view, int position) {
        handleRecyclerViewClicked(position);
    }


    @Override
    public void onLongClick(View view, int position) {

    }
}
