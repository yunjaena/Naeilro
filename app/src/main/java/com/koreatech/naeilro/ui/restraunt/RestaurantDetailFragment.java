package com.koreatech.naeilro.ui.restraunt;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.network.interactor.FacilityRestInteractor;
import com.koreatech.naeilro.network.interactor.RestaurantRestInteractor;
import com.koreatech.naeilro.ui.facility.adapter.FacilityDetailInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.facility.adapter.FacilityImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.facility.presenter.FacilityDetailFragmentPresenter;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity;
import com.koreatech.naeilro.ui.restraunt.adapater.RestaurantDetailInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.restraunt.adapater.RestaurantImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.restraunt.presenter.RestaurantDetailContract;
import com.koreatech.naeilro.ui.restraunt.presenter.RestaurantDetailPresenter;
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

import static android.content.Context.RESTRICTIONS_SERVICE;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_AREA_CODE;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_ID;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_MAP_X;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_MAP_Y;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_THUMBNAIL;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TITLE;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TYPE;


public class RestaurantDetailFragment extends Fragment implements RestaurantDetailContract.View {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    private Unbinder unbinder;
    private View view;

    private ImageView restaurantDetailImage;
    private TextView restaurantDetailTitle;
    private TextView restaurantDetailOverview;
    private TextView restaurantDetailInfoTextView;
    private LinearLayout restaurantImageLinearLayout;
    private ImageView restaurantExtraImageView;
    private RecyclerView restaurantExtraImageRecyclerView;
    private RecyclerView restaurantInfoRecyclerView;
    private LinearLayout restaurantDetailMapLinearLayout;
    private LinearLayout restaurantDetailTMapLinearLayout;
    private TextView restaurantAddressTextView;
    private TMapView tMapView;
    private RestaurantDetailInfoRecyclerViewAdapter restaurantDetailInfoRecyclerViewAdapter;
    private RestaurantDetailPresenter restaurantDetailPresenter;
    private LinearLayoutManager linearLayoutManager;
    private List<RestaurantInfo> imageRestaurantInfoList;
    private RestaurantImageRecyclerViewAdapter restaurantDetailImageRecyclerViewAdapter;
    private int contentId;
    private String contentTypeID;
    private String contentTitle;
    private String contentThumbnail;
    private String detailTitle;
    private String mapX;
    private String mapY;
    private String areaCode;
    @BindView(R.id.rest_parkinlot)
    TextView parkinglotTextView;
    @BindView(R.id.rest_opening)
    TextView openingTextView;
    @BindView(R.id.rest_day)
    TextView restDayTextView;
    @BindView(R.id.main_food)
    TextView mainFoodTextView;
    @BindView(R.id.left_food)
    TextView leftFoodTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        contentId = getArguments().getInt("contentId");
        Log.e("fragment", Integer.toString(contentId));
        detailTitle = getArguments().getString("title");
        contentTitle = detailTitle;
        init(view);
        return view;
    }
    public static Spanned fromHtml(String source) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(source);
        }
        return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
    }
    @OnClick(R.id.add_my_plan_restaurant)
    public void clickFacilityMyPlanButton() {
        Intent intent = new Intent(getActivity(), MyPlanBottomSheetActivity.class);
        intent.putExtra(CONTENT_ID, String.valueOf(contentId));
        intent.putExtra(CONTENT_TYPE, contentTypeID);
        intent.putExtra(CONTENT_TITLE, contentTitle);
        intent.putExtra(CONTENT_THUMBNAIL, contentThumbnail);
        intent.putExtra(CONTENT_MAP_X, mapX);
        intent.putExtra(CONTENT_MAP_Y, mapY);
        intent.putExtra(CONTENT_AREA_CODE, areaCode);
        startActivity(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }
    public void init(View view) {
        imageRestaurantInfoList = new ArrayList<>();
        initView(view);
        initTMap(view);
        initFacilityExtraImageRecyclerView();
        restaurantDetailPresenter = new RestaurantDetailPresenter(new RestaurantRestInteractor(), this);
        restaurantDetailPresenter.getCommonInfo(contentId);

    }
    private void initView(View view) {
        restaurantDetailImage = view.findViewById(R.id.restaurant_detail_image);
        restaurantDetailTitle = view.findViewById(R.id.restaurant_detail_title);
        restaurantDetailOverview = view.findViewById(R.id.restaurant_detail_overview);
        restaurantDetailInfoTextView = view.findViewById(R.id.restaurant_detail_info_text_view);
        restaurantImageLinearLayout = view.findViewById(R.id.restaurant_image_linear_layout);
        restaurantExtraImageView = view.findViewById(R.id.restaurant_extra_image_view);
        restaurantExtraImageRecyclerView = view.findViewById(R.id.restaurant_extra_image_recycler_view);
        restaurantDetailMapLinearLayout = view.findViewById(R.id.restaurant_detail_map_linear_layout);
        restaurantDetailTMapLinearLayout = view.findViewById(R.id.restaurant_detail_t_map_linear_layout);
        restaurantAddressTextView = view.findViewById(R.id.restaurant_address_text_view);
        restaurantDetailMapLinearLayout.setVisibility(View.GONE);
        restaurantImageLinearLayout.setVisibility(View.GONE);
        restaurantInfoRecyclerView = view.findViewById(R.id.restaurant_info_recycler_view);

    }
    private void initTMap(View view) {
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapView.setCenterPoint(centerLon, centerLat);
        restaurantDetailTMapLinearLayout.addView(tMapView);

    }

    public void setAddressInfo(double x, double y, String title, String address) {
        showMapPoint(x, y, title, address);
        restaurantAddressTextView.setText(address);
    }

    private void showMapPoint(double x, double y, String title, String address) {
        restaurantDetailMapLinearLayout.setVisibility(View.VISIBLE);
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
    public void showDetailInfoList(RestaurantInfo restaurant) {
        parkinglotTextView.setText(fromHtml(getRestaurntDetailInfoString(restaurant.getParkingfood())));
        openingTextView.setText(fromHtml(getRestaurntDetailInfoString(restaurant.getOpentimefood())));
        restDayTextView.setText(fromHtml(getRestaurntDetailInfoString(restaurant.getRestdatefood())));
        mainFoodTextView.setText(fromHtml(getRestaurntDetailInfoString(restaurant.getFirstmenu())));
        leftFoodTextView.setText(fromHtml(getRestaurntDetailInfoString(restaurant.getTreatmenu())));
        restaurantDetailPresenter.getImageInfo(contentId);
    }
    public String getRestaurntDetailInfoString(String s){
        if(s == null){
            return " - ";
        }else{
            return s;
        }
    }

    @Override
    public void showImageInfoList(List<RestaurantInfo> restaurantList) {
        /*
        restaurantImageRecyclerView = view.findViewById(R.id.restaurant_Image_recyclerview);
        restaurantImageRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        restaurantImageRecyclerViewAdapter = new FacilityImageRecyclerViewAdapter(restaurantList);
        restaurantImageRecyclerView.setAdapter(restaurantImageRecyclerViewAdapter);

         */
        setImageExtra(restaurantList);
    }

    @Override
    public void showCommonInfo(RestaurantInfo restaurant) {
        contentTypeID = Integer.toString(restaurant.getContentTypeID());
        contentThumbnail = restaurant.getFirstImage();
        mapX = Double.toString(restaurant.getMapX());
        mapY = Double.toString(restaurant.getMapY());
        areaCode = Integer.toString(restaurant.getAreaCode());
        setAddressInfo(restaurant.getMapX(), restaurant.getMapY(), detailTitle, restaurant.getAddress());
        setFirstImageView(restaurant.getFirstImage());
        setTitle(detailTitle);
        setSummary(restaurant.getOverview());
        restaurantDetailPresenter.getDetailInfo(contentId);
    }

    private void setImageExtra(List<RestaurantInfo> restaurantItems) {
        if (restaurantItems == null || restaurantItems.size() == 0) return;
        restaurantImageLinearLayout.setVisibility(View.VISIBLE);
        imageRestaurantInfoList.addAll(restaurantItems);
        restaurantDetailImageRecyclerViewAdapter.notifyDataSetChanged();
        setFacilityExtraImageView(0);
    }

    private void initFacilityExtraImageRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        restaurantDetailImageRecyclerViewAdapter = new RestaurantImageRecyclerViewAdapter(imageRestaurantInfoList, getContext());
        restaurantDetailImageRecyclerViewAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Glide.with(getContext())
                        .load(imageRestaurantInfoList.get(position).getOriginimgurl())
                        .error(R.drawable.ic_no_image)
                        .into(restaurantExtraImageView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        restaurantExtraImageRecyclerView.setLayoutManager(linearLayoutManager);
        restaurantExtraImageRecyclerView.setAdapter(restaurantDetailImageRecyclerViewAdapter);
    }

    private void setDetailInfo(List<RestaurantInfo> restaurantItems) {
        StringBuilder stringBuilder = new StringBuilder();
        for (RestaurantInfo restaurantInfo : restaurantItems) {
            stringBuilder.append("<b>").append(" · ").append(restaurantInfo.getInfoname()).append("</b>").append(" : ").append(restaurantInfo.getInfotext()).append("<br>").append("<br>");
        }
        restaurantDetailInfoTextView.setText(fromHtml(stringBuilder.toString()));
    }

    private void setFacilityExtraImageView(int position) {
        if (imageRestaurantInfoList.isEmpty() || imageRestaurantInfoList.size() < position) return;
        Glide.with(getContext())
                .load(imageRestaurantInfoList.get(position).getOriginimgurl())
                .error(R.drawable.ic_no_image)
                .into(restaurantExtraImageView);
    }

    private void setFirstImageView(String url) {
        Glide.with(getContext()).load(url)
                .error(R.drawable.ic_no_image)
                .into(restaurantDetailImage);
    }

    private void setSummary(String text) {
        if (text == null) return;
        restaurantDetailOverview.setText(fromHtml(text));
    }

    private void setTitle(String text) {
        if (text == null) return;
        restaurantDetailTitle.setText(text);
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog("로딩중");
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void setPresenter(RestaurantDetailPresenter presenter) {
        this.restaurantDetailPresenter = presenter;
    }
}
