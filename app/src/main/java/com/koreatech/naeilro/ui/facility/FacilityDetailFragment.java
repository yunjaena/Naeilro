package com.koreatech.naeilro.ui.facility;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.interactor.FacilityRestInteractor;
import com.koreatech.naeilro.network.interactor.ReportsRestInteractor;
import com.koreatech.naeilro.ui.facility.adapter.FacilityDetailInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.facility.adapter.FacilityImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.facility.presenter.FacilityDetailFragmentPresenter;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.reports.adapter.ReportsDetailInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.reports.adapter.ReportsImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.reports.presenter.ReportsDetailFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import android.text.Html;

public class FacilityDetailFragment extends Fragment implements FacilityDetailFragmentContract.View {

    private View view;
    private FacilityDetailFragmentPresenter facilityDetailFragmentPresenter;
    private RecyclerView facilityDetailRecyclerView;
    private RecyclerView facilityImageRecyclerView;
    private FacilityDetailInfoRecyclerViewAdapter facilityDetailInfoRecyclerViewAdapter;
    private FacilityImageRecyclerViewAdapter facilityImageRecyclerViewAdapter;
    private int contentId;
    private Unbinder unbinder;
    @BindView(R.id.facility_detail_title)
    TextView facilityDetailTitle;
    @BindView(R.id.facility_detail_overview)
    TextView facilityDetailOverview;
    @BindView(R.id.facility_detail_image)
    ImageView facilityImageView;
    @BindView(R.id.facility_detail_tel)
    TextView facilityDetailTel;
    @BindView(R.id.facility_detail_address)
    TextView facilityDetailAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_facility_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        facilityDetailFragmentPresenter.getComonInfo(contentId);
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }
    public void init(View view) {
        facilityDetailFragmentPresenter = new FacilityDetailFragmentPresenter(new FacilityRestInteractor(), this);
        contentId = getArguments().getInt("contentId");
        facilityDetailFragmentPresenter.getComonInfo(contentId);

    }

    @Override
    public void showDetailInfoList(List<Facility> facilityList) {
        facilityDetailRecyclerView = view.findViewById(R.id.facility_detailInfo_recyclerview);
        facilityDetailRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        facilityDetailInfoRecyclerViewAdapter = new FacilityDetailInfoRecyclerViewAdapter(facilityList);
        facilityDetailRecyclerView.setAdapter(facilityDetailInfoRecyclerViewAdapter);
        facilityDetailFragmentPresenter.getImageInfo(contentId);
    }

    @Override
    public void showImageInfoList(List<Facility> facilityList) {
        facilityImageRecyclerView = view.findViewById(R.id.facility_Image_recyclerview);
        facilityImageRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        facilityImageRecyclerViewAdapter = new FacilityImageRecyclerViewAdapter(facilityList);
        facilityImageRecyclerView.setAdapter(facilityImageRecyclerViewAdapter);
    }

    @Override
    public void showCommonInfo(Facility facility) {
        if (facility.getFirstimage() == null) {
            Glide.with(facilityImageView).load(R.drawable.ic_no_image).into(facilityImageView);
        } else {
            Glide.with(facilityImageView).load(facility.getFirstimage()).into(facilityImageView);
        }
        facilityDetailTitle.setText(facility.getTitle());
        facilityDetailOverview.setText("소개 : "+ fromHtml(facility.getOverview()));
        if (facility.getTel() == null)
            facilityDetailTel.setVisibility(View.GONE);
        else
            facilityDetailTel.setText("전화번호 : " + facility.getTel());
        if (facility.getAddr1() == null)
            facilityDetailAddress.setVisibility(View.GONE);
        else
            facilityDetailAddress.setText("주소 : " + facility.getAddr1());
        facilityDetailFragmentPresenter.getDetailInfo(contentId);
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading_facility_info);
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void setPresenter(FacilityDetailFragmentPresenter presenter) {
        this.facilityDetailFragmentPresenter = presenter;
    }
    public static Spanned fromHtml(String source) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(source);
        }
        return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
    }
}
