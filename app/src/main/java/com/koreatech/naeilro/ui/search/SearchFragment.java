package com.koreatech.naeilro.ui.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.search.SearchInfo;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private static final int[] CATEGORY_TYPE_LIST = {12, 14, 28, 14, 15};
    private RecyclerView searchResultRecyclerView;
    private Spinner searchSpinner;
    private SearchView searchView;
    private List<SearchInfo> searchInfoList;
    private int categoryType;
    private int pageNum;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        init(root);
        return root;
    }

    private void init(View view) {
        pageNum = 1;
        searchResultRecyclerView = view.findViewById(R.id.search_recycler_view);
        searchInfoList = new ArrayList<>();
        initSearchView(view);
        initSpinner(view);
    }



    public void searchText(String text) {
        if (text.isEmpty()) return;
        searchInfoList.clear();
        pageNum = 1;
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

}
