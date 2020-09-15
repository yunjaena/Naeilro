package com.koreatech.naeilro.ui.house;

import android.content.Intent;
import android.os.Bundle;
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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.interactor.HouseRestInteractor;
import com.koreatech.naeilro.ui.house.adapter.HouseImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.house.presenter.HouseDetailFragmentPresenter;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_AREA_CODE;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_ID;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_MAP_X;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_MAP_Y;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_THUMBNAIL;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TITLE;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TYPE;
import static com.koreatech.naeilro.ui.tourspot.TourSpotDetailFragment.fromHtml;


public class HouseDetailFragment extends Fragment implements HouseDetailFragmentContract.View {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    @BindView(R.id.accomtaion_number)
    TextView accomdationTextView;
    @BindView(R.id.room_type)
    TextView roomTypeTextView;
    @BindView(R.id.check_in)
    TextView checkInTextView;
    @BindView(R.id.check_out)
    TextView checkOutTextView;
    @BindView(R.id.reservation_guide)
    TextView reservationTextView;
    @BindView(R.id.check_cook)
    TextView checkCookTextView;
    @BindView(R.id.parkinglot)
    TextView parkinglotTextView;
    @BindView(R.id.food_place)
    TextView foodPlaceTextView;
    @BindView(R.id.sub_facility)
    TextView subFacilityTextView;
    @BindView(R.id.barbecue)
    TextView barbecueTextView;
    @BindView(R.id.campfire)
    TextView camfireTextView;
    @BindView(R.id.pick_up)
    TextView pickUpTextView;
    private View view;
    private Unbinder unbinder;
    private ImageView houseDetailImage;
    private TextView houseDetailTitle;
    private TextView houseDetailOverview;
    private TextView houseDetailInfoTextView;
    private LinearLayout houseImageLinearLayout;
    private ImageView houseExtraImageView;
    private RecyclerView houseExtraImageRecyclerView;
    private LinearLayout houseDetailMapLinearLayout;
    private LinearLayout houseDetailTMapLinearLayout;
    private TextView houseAddressTextView;
    private TMapView tMapView;
    //private HouseDetailInfoRecyclerViewAdapter houseDetailInfoRecyclerViewAdapter;
    private HouseDetailFragmentPresenter houseDetailPresenter;
    private LinearLayoutManager linearLayoutManager;
    private List<HouseInfo> imagehouseInfoList;
    private HouseImageRecyclerViewAdapter houseDetailImageRecyclerViewAdapter;
    private int contentId;
    private String contentTypeId;
    private String contentTitle;
    private String contentThumbnail;
    private String detailTitle;
    private String mapX;
    private String mapY;
    private String areaCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_house_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        contentId = getArguments().getInt("contentId");
        detailTitle = getArguments().getString("title");
        contentTitle = detailTitle;
        init(view);
        return view;
    }

    @OnClick(R.id.add_my_plan_house)
    public void clickHouseMyPlanButton() {
        Intent intent = new Intent(getActivity(), MyPlanBottomSheetActivity.class);
        intent.putExtra(CONTENT_ID, String.valueOf(contentId));
        intent.putExtra(CONTENT_TYPE, contentTypeId);
        intent.putExtra(CONTENT_TITLE, contentTitle);
        intent.putExtra(CONTENT_THUMBNAIL, contentThumbnail);
        intent.putExtra(CONTENT_MAP_X, mapX);
        intent.putExtra(CONTENT_MAP_Y, mapY);
        intent.putExtra(CONTENT_AREA_CODE, areaCode);
        startActivity(intent);
    }


    public void init(View view) {
        imagehouseInfoList = new ArrayList<>();
        initView(view);
        initTMap(view);
        inithouseExtraImageRecyclerView();
        houseDetailPresenter = new HouseDetailFragmentPresenter(new HouseRestInteractor(), this);
        houseDetailPresenter.getCommonInfo(contentId);
        houseDetailPresenter.getIntroInfo(contentId);
        houseDetailPresenter.getImageInfo(contentId);
    }

    private void initView(View view) {
        houseDetailImage = view.findViewById(R.id.house_detail_image);
        houseDetailTitle = view.findViewById(R.id.house_detail_title);
        houseDetailOverview = view.findViewById(R.id.house_detail_overview);
        houseDetailInfoTextView = view.findViewById(R.id.house_detail_info_text_view);
        houseImageLinearLayout = view.findViewById(R.id.house_image_linear_layout);
        houseExtraImageView = view.findViewById(R.id.house_extra_image_view);
        houseExtraImageRecyclerView = view.findViewById(R.id.house_extra_image_recycler_view);
        houseDetailMapLinearLayout = view.findViewById(R.id.house_detail_map_linear_layout);
        houseDetailTMapLinearLayout = view.findViewById(R.id.house_detail_t_map_linear_layout);
        houseAddressTextView = view.findViewById(R.id.house_address_text_view);
        houseDetailMapLinearLayout.setVisibility(View.GONE);
        houseImageLinearLayout.setVisibility(View.GONE);


    }

    private void initTMap(View view) {
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapView.setCenterPoint(centerLon, centerLat);
        houseDetailTMapLinearLayout.addView(tMapView);

    }

    public void setAddressInfo(double x, double y, String title, String address) {
        showMapPoint(x, y, title, address);
        houseAddressTextView.setText(address);
    }

    private void showMapPoint(double x, double y, String title, String address) {
        houseDetailMapLinearLayout.setVisibility(View.VISIBLE);
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
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading_house_info);
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void showDetailInfoList(List<HouseInfo> houseList) {

    }

    @Override
    public void showCommonInfo(HouseInfo houseInfo) {
        contentTypeId = houseInfo.getContenttypeid();
        contentThumbnail = houseInfo.getFirstimage();
        mapX = houseInfo.getMapx();
        mapY = houseInfo.getMapy();
        areaCode = houseInfo.getAreacode();
        setAddressInfo(Double.parseDouble(houseInfo.getMapx()), Double.parseDouble(houseInfo.getMapy()), detailTitle, houseInfo.getAddr1());
        setFirstImageView(houseInfo.getFirstimage());
        setSummary(houseInfo.getOverview());
        setTitle(detailTitle);
    }

    private void setImageExtra(List<HouseInfo> houseItems) {
        if (houseItems == null || houseItems.size() == 0) return;
        houseImageLinearLayout.setVisibility(View.VISIBLE);
        imagehouseInfoList.addAll(houseItems);
        houseDetailImageRecyclerViewAdapter.notifyDataSetChanged();
        setHouseExtraImageView(0);
    }

    private void inithouseExtraImageRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        houseDetailImageRecyclerViewAdapter = new HouseImageRecyclerViewAdapter(imagehouseInfoList, getContext());
        houseDetailImageRecyclerViewAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Glide.with(getContext())
                        .load(imagehouseInfoList.get(position).getOriginimgurl())
                        .error(R.drawable.ic_no_image)
                        .into(houseExtraImageView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        houseExtraImageRecyclerView.setLayoutManager(linearLayoutManager);
        houseExtraImageRecyclerView.setAdapter(houseDetailImageRecyclerViewAdapter);
    }

    @Override
    public void showImageInfoList(List<HouseInfo> houseList) {
        setImageExtra(houseList);
    }


    @Override
    public void showHouseIntroInfo(HouseInfo houseInfo) {
        if (houseInfo.getAccomcountlodging() != null)
            accomdationTextView.setText("  " + houseInfo.getAccomcountlodging());
        else
            accomdationTextView.setText("");
        if (houseInfo.getRoomtype() != null)
            roomTypeTextView.setText("  " + houseInfo.getRoomtype());
        else
            roomTypeTextView.setText("");
        if (houseInfo.getCheckintime() != null)
            checkInTextView.setText("  " + houseInfo.getCheckintime());
        else
            checkInTextView.setText("");
        if (houseInfo.getCheckouttime() != null)
            checkOutTextView.setText("  " + houseInfo.getCheckouttime());
        else
            checkOutTextView.setText("");
        if (houseInfo.getReservationlodging() != null)
            reservationTextView.setText("  " + houseInfo.getReservationlodging());
        else
            reservationTextView.setText("");
        if (houseInfo.getChkcooking() != null)
            checkCookTextView.setText("  " + houseInfo.getChkcooking());
        else
            checkCookTextView.setText("");
        if (houseInfo.getParkinglodging() != null)
            parkinglotTextView.setText("  " + houseInfo.getParkinglodging());
        else
            parkinglotTextView.setText("");
        if (houseInfo.getFoodplace() != null)
            foodPlaceTextView.setText("  " + houseInfo.getFoodplace());
        else
            foodPlaceTextView.setText("");
        if (houseInfo.getSubfacility() != null)
            subFacilityTextView.setText("  " + houseInfo.getSubfacility());
        else
            subFacilityTextView.setText("");
        if (houseInfo.getBarbecue() != null)
            barbecueTextView.setText("  " + houseInfo.getBarbecue());
        else
            barbecueTextView.setText("");
        if (houseInfo.getCampfire() != null)
            camfireTextView.setText("  " + houseInfo.getCampfire());
        else
            camfireTextView.setText("  " + houseInfo.getCampfire());
        if (houseInfo.getPickup() != null)
            pickUpTextView.setText("  " + houseInfo.getPickup());
        else
            pickUpTextView.setText("");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }


    @Override
    public void setPresenter(HouseDetailFragmentPresenter presenter) {
        this.houseDetailPresenter = presenter;
    }

    private void setHouseExtraImageView(int position) {
        if (imagehouseInfoList.isEmpty() || imagehouseInfoList.size() < position) return;
        Glide.with(getContext())
                .load(imagehouseInfoList.get(position).getOriginimgurl())
                .error(R.drawable.ic_no_image)
                .into(houseExtraImageView);
    }

    private void setFirstImageView(String url) {
        Glide.with(getContext()).load(url)
                .apply(new RequestOptions().bitmapTransform(new RoundedCorners(24)))
                .error(R.drawable.ic_no_image)
                .into(houseDetailImage);
    }

    private void setSummary(String text) {
        if (text == null) return;
        houseDetailOverview.setText(fromHtml(text));
    }

    private void setTitle(String text) {
        if (text == null) return;
        houseDetailTitle.setText(text);
    }

}
