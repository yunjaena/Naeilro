package com.koreatech.naeilro.ui.facility;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.interactor.FacilityRestInteractor;
import com.koreatech.naeilro.ui.facility.adapter.FacilityDetailInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.facility.adapter.FacilityImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.facility.presenter.FacilityDetailFragmentPresenter;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_ID;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TYPE;

public class FacilityDetailFragment extends Fragment implements FacilityDetailFragmentContract.View {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    private View view;
    private Unbinder unbinder;
    /* View component */
    private ImageView facilityDetailImage;
    private TextView facilityDetailTitle;
    private TextView facilityDetailOverview;
    private TextView facilityDetailInfoTextView;
    private LinearLayout facilityImageLinearLayout;
    private ImageView facilityExtraImageView;
    private RecyclerView facilityExtraImageRecyclerView;
    private RecyclerView facilityInfoRecyclerView;
    private LinearLayout facilityDetailMapLinearLayout;
    private LinearLayout facilityDetailTMapLinearLayout;
    private TextView facilityAddressTextView;
    private TMapView tMapView;
    private FacilityDetailInfoRecyclerViewAdapter facilityDetailInfoRecyclerViewAdapter;
    private FacilityDetailFragmentPresenter facilityDetailPresenter;
    private LinearLayoutManager linearLayoutManager;
    private List<Facility> imagefacilityInfoList;
    private FacilityImageRecyclerViewAdapter facilityDetailImageRecyclerViewAdapter;
    private int contentId;
    private String contentTypeID;
    private String detailTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_facility_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        contentId = getArguments().getInt("contentId");
        detailTitle = getArguments().getString("title");
        init(view);
        return view;
    }
    @OnClick(R.id.add_my_plan_facility)
    public void clickFacilityMyPlanButton(){
        Intent intent = new Intent(getActivity(), MyPlanBottomSheetActivity.class);
        intent.putExtra(CONTENT_ID, String.valueOf(contentId));
        intent.putExtra(CONTENT_TYPE, contentTypeID);
        startActivity(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }
    public void init(View view) {
        imagefacilityInfoList = new ArrayList<>();
        initView(view);
        initTMap(view);
        initFacilityExtraImageRecyclerView();
        facilityDetailPresenter = new FacilityDetailFragmentPresenter(new FacilityRestInteractor(), this);
        facilityDetailPresenter.getCommonInfo(contentId);
        facilityDetailPresenter.getDetailInfo(contentId);
        facilityDetailPresenter.getImageInfo(contentId);


    }
    private void initView(View view) {
        facilityDetailImage = view.findViewById(R.id.facility_detail_image);
        facilityDetailTitle = view.findViewById(R.id.facility_detail_title);
        facilityDetailOverview = view.findViewById(R.id.facility_detail_overview);
        facilityDetailInfoTextView = view.findViewById(R.id.facility_detail_info_text_view);
        facilityImageLinearLayout = view.findViewById(R.id.facility_image_linear_layout);
        facilityExtraImageView = view.findViewById(R.id.facility_extra_image_view);
        facilityExtraImageRecyclerView = view.findViewById(R.id.facility_extra_image_recycler_view);
        facilityDetailMapLinearLayout = view.findViewById(R.id.facility_detail_map_linear_layout);
        facilityDetailTMapLinearLayout = view.findViewById(R.id.facility_detail_t_map_linear_layout);
        facilityAddressTextView = view.findViewById(R.id.facility_address_text_view);
        facilityDetailMapLinearLayout.setVisibility(View.GONE);
        facilityImageLinearLayout.setVisibility(View.GONE);
        facilityInfoRecyclerView = view.findViewById(R.id.facility_info_recycler_view);

    }
    private void initTMap(View view) {
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapView.setCenterPoint(centerLon, centerLat);
        facilityDetailTMapLinearLayout.addView(tMapView);

    }
    public void setAddressInfo(double x, double y, String title, String address) {
        showMapPoint(x, y, title, address);
        facilityAddressTextView.setText(address);
    }

    private void showMapPoint(double x, double y, String title, String address) {
        facilityDetailMapLinearLayout.setVisibility(View.VISIBLE);
        TMapMarkerItem markerItem = new TMapMarkerItem();
        TMapPoint tMapPoint1 = new TMapPoint(y, x);
        markerItem.setVisible(TMapMarkerItem.VISIBLE);
        markerItem.setPosition(0f, 0f);
        markerItem.setTMapPoint(tMapPoint1);
        markerItem.setName(title);
        markerItem.setCanShowCallout(true);
        markerItem.setCalloutTitle(title);
        markerItem.setCalloutSubTitle(address);
        tMapView.addMarkerItem(title, markerItem);
        tMapView.setCenterPoint(x, y, true);
        tMapView.setZoomLevel(15);
        tMapView.initView();
    }

    @Override
    public void showDetailInfoList(List<Facility> facilityList) {
        facilityInfoRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        facilityDetailInfoRecyclerViewAdapter = new FacilityDetailInfoRecyclerViewAdapter(facilityList);
        facilityInfoRecyclerView.setAdapter(facilityDetailInfoRecyclerViewAdapter);
        //setDetailInfo(facilityList);
        //facilityDetailFragmentPresenter.getImageInfo(contentId);
    }

    @Override
    public void showImageInfoList(List<Facility> facilityList) {
        /*
        facilityImageRecyclerView = view.findViewById(R.id.facility_Image_recyclerview);
        facilityImageRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        facilityImageRecyclerViewAdapter = new FacilityImageRecyclerViewAdapter(facilityList);
        facilityImageRecyclerView.setAdapter(facilityImageRecyclerViewAdapter);

         */
        setImageExtra(facilityList);
    }

    @Override
    public void showCommonInfo(Facility facility) {
        contentTypeID = facility.getContenttypeid();
        setAddressInfo(Double.parseDouble(facility.getMapx()), Double.parseDouble(facility.getMapy()), detailTitle, facility.getAddr1());
        setFirstImageView(facility.getFirstimage());
        setTitle(detailTitle);
        setSummary(facility.getOverview());
    }
    private void setImageExtra(List<Facility> facilityItems) {
        if (facilityItems == null || facilityItems.size() == 0) return;
        facilityImageLinearLayout.setVisibility(View.VISIBLE);
        imagefacilityInfoList.addAll(facilityItems);
        facilityDetailImageRecyclerViewAdapter.notifyDataSetChanged();
        setFacilityExtraImageView(0);
    }

    private void initFacilityExtraImageRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        facilityDetailImageRecyclerViewAdapter = new FacilityImageRecyclerViewAdapter( imagefacilityInfoList,getContext());
        facilityDetailImageRecyclerViewAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Glide.with(getContext())
                        .load(imagefacilityInfoList.get(position).getOriginimgurl())
                        .error(R.drawable.ic_no_image)
                        .into(facilityExtraImageView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        facilityExtraImageRecyclerView.setLayoutManager(linearLayoutManager);
        facilityExtraImageRecyclerView.setAdapter(facilityDetailImageRecyclerViewAdapter);
    }

    private void setDetailInfo(List<Facility> facilityItems) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Facility facilityInfo : facilityItems) {
            stringBuilder.append("<b>").append(" · ").append(facilityInfo.getInfoname()).append("</b>").append(" : ").append(facilityInfo.getInfotext()).append("<br>").append("<br>");
        }
        facilityDetailInfoTextView.setText(fromHtml(stringBuilder.toString()));
    }



    private void setFacilityExtraImageView(int position) {
        if (imagefacilityInfoList.isEmpty() || imagefacilityInfoList.size() < position) return;
        Glide.with(getContext())
                .load(imagefacilityInfoList.get(position).getOriginimgurl())
                .error(R.drawable.ic_no_image)
                .into(facilityExtraImageView);
    }

    private void setFirstImageView(String url) {
        Glide.with(getContext()).load(url)
                .error(R.drawable.ic_no_image)
                .into(facilityDetailImage);
    }

    private void setSummary(String text) {
        if (text == null) return;
        facilityDetailOverview.setText(fromHtml(text));
    }

    private void setTitle(String text) {
        if (text == null) return;
        facilityDetailTitle.setText(text);
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
        this.facilityDetailPresenter = presenter;
    }
    public static Spanned fromHtml(String source) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(source);
        }
        return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
    }
}
