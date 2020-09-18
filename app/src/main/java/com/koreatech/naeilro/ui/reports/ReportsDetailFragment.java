package com.koreatech.naeilro.ui.reports;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.koreatech.core.recyclerview.RecyclerViewClickListener;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.network.interactor.ReportsRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity;
import com.koreatech.naeilro.ui.reports.adapter.ReportsDetailInfoRecyclerViewAdapter;
import com.koreatech.naeilro.ui.reports.adapter.ReportsImageRecyclerViewAdapter;
import com.koreatech.naeilro.ui.reports.presenter.ReportsDetailFragmentPresenter;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kr.co.prnd.readmore.ReadMoreTextView;

import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_AREA_CODE;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_ID;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_MAP_X;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_MAP_Y;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_THUMBNAIL;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TITLE;
import static com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity.CONTENT_TYPE;

public class ReportsDetailFragment extends Fragment implements ReportsDetailFragmentContract.View {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    private static final int ZOOM_LEVEL = 17;
    @BindView(R.id.reports_detail_title)
    TextView reportsDetailTitle;
    @BindView(R.id.reports_detail_overview)
    ReadMoreTextView reportsDetailOverview;
    @BindView(R.id.reports_detail_image)
    ImageView reportsImageView;
    @BindView(R.id.reports_detail_tel)
    TextView reportsDetailTel;
    @BindView(R.id.reports_detail_address)
    TextView reportsDetailAddress;
    @BindView(R.id.reports_detail_map_linear_layout)
    LinearLayout reportsDetailMapLinearLayout;
    @BindView(R.id.reports_detail_t_map_linear_layout)
    LinearLayout reportsDetailTMapLinearLayout;
    @BindView(R.id.reports_address_text_view)
    TextView reportsAddressTextView;
    private CheckBox restaurantCheckBox;
    private CheckBox convenienceStoreCheckBox;
    private TextView resetMapTextView;
    private TMapMarkerItem selectedTMapMarkerItem;
    private ArrayList<TMapPOIItem> restaurantIDArrayList;
    private ArrayList<TMapPOIItem> convenienceStoreIDArrayList;

    private View view;
    private ReportsDetailFragmentPresenter reportsDetailFragmentPresenter;
    private RecyclerView reportsDetailRecyclerView;
    private RecyclerView reportsImageRecyclerView;
    private ReportsDetailInfoRecyclerViewAdapter reportsDetailInfoRecyclerViewAdapter;
    private ReportsImageRecyclerViewAdapter reportsImageRecyclerViewAdapter;
    private int contentId;
    private String contentTypeId;
    private String contentTitle;
    private String contentThumbnail;
    private String mapX;
    private String mapY;
    private Unbinder unbinder;
    private String title;
    private TMapView tMapView;
    private String areaCode;

    public static Spanned fromHtml(String source) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(source);
        }
        return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reports_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);

        return view;
    }

    @OnClick(R.id.add_my_plan_reports)
    public void clickReportsMyPlanButton() {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    public void init(View view) {
        restaurantIDArrayList = new ArrayList<>();
        convenienceStoreIDArrayList = new ArrayList<>();
        reportsDetailFragmentPresenter = new ReportsDetailFragmentPresenter(new ReportsRestInteractor(), this);
        reportsDetailRecyclerView = view.findViewById(R.id.reports_detailInfo_recyclerview);
        contentId = getArguments().getInt("contentId");
        title = getArguments().getString("title");
        contentTitle = title;
        initTMap(view);
        reportsDetailFragmentPresenter.getComonInfo(contentId);
        reportsDetailFragmentPresenter.getDetailInfo(contentId);
        restaurantCheckBox = view.findViewById(R.id.restaurant_check_box);
        convenienceStoreCheckBox = view.findViewById(R.id.convenience_store_check_box);
        resetMapTextView = view.findViewById(R.id.reset_text_view);
        restaurantCheckBox.setOnCheckedChangeListener(this::setRestaurantCheckBox);
        convenienceStoreCheckBox.setOnCheckedChangeListener(this::setConvenienceStoreCheckBox);
        resetMapTextView.setOnClickListener(v -> resetPosition());
    }

    private void initTMap(View view) {
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapView.setCenterPoint(centerLon, centerLat);
        tMapView.setOnCalloutRightButtonClickListener(this::goToDetailPageByMarker);
        reportsDetailTMapLinearLayout.addView(tMapView);

    }

    private void goToDetailPageByMarker(TMapMarkerItem tMapMarkerItem){
        String[] s = tMapMarkerItem.getCalloutSubTitle().split(" ");
        String searchName = s[s.length - 1] + " " + tMapMarkerItem.getCalloutTitle();
        ToastUtil.getInstance().makeShort(searchName);
    }


    public void setRestaurantCheckBox(View view, boolean isChecked) {
        String id = "음식점";
        if (selectedTMapMarkerItem == null) return;
        if (isChecked) {
            findAroundByName(id, R.drawable.ic_restaurant_color);
        } else {
            removeMapMarkerByID(id);
        }
    }

    public void setConvenienceStoreCheckBox(View view, boolean isChecked) {
        String id = "편의점";
        if (selectedTMapMarkerItem == null) return;
        if (isChecked) {
            findAroundByName(id, R.drawable.ic_facility_color);
        } else {
            removeMapMarkerByID(id);
        }
    }

    private void findAroundByName(String id, @DrawableRes int drawable) {
        if (selectedTMapMarkerItem == null) return;
        TMapPoint tMapPoint = new TMapPoint(selectedTMapMarkerItem.latitude, selectedTMapMarkerItem.longitude);
        new TMapData().findAroundKeywordPOI(tMapPoint, id, 3, 50, arrayList -> {
            if(id.equals("편의점")){
                removeMapMarkerByID(id);
                convenienceStoreIDArrayList.addAll(arrayList);
            }else{
                removeMapMarkerByID(id);
                restaurantIDArrayList.addAll(arrayList);
            }
            for (TMapPOIItem point : arrayList) {
                addPin(point.getPOIName(), point.getPOIAddress().replace("null", ""), point.getPOIPoint().getLongitude(), point.getPOIPoint().getLatitude(), drawable);
            }
            resetPosition();
        });
    }

    private void removeMapMarkerByID(String id){
        if(id.equals("편의점")){
            for(TMapPOIItem mapPOIItem : convenienceStoreIDArrayList){
                tMapView.removeMarkerItem(mapPOIItem.getPOIName());
            }
            convenienceStoreIDArrayList.clear();
        }else{
            for(TMapPOIItem mapPOIItem : restaurantIDArrayList){
                tMapView.removeMarkerItem(mapPOIItem.getPOIName());
            }
            restaurantIDArrayList.clear();
        }
    }

    private void resetPosition(){
        if(selectedTMapMarkerItem == null) return;
        tMapView.setCenterPoint(selectedTMapMarkerItem.longitude, selectedTMapMarkerItem.latitude, true);
        tMapView.setZoomLevel(ZOOM_LEVEL);
    }

    private void addPin(String name, String subTitle, Double longitude, Double latitude, @DrawableRes int drawable) {
        TMapMarkerItem markerItem1 = new TMapMarkerItem();
        TMapPoint tMapPoint1 = new TMapPoint(latitude, longitude); // SKT타워
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), drawable);
        Bitmap markerBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
        Bitmap selectBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_arrow_forward_white_36dp);
        Bitmap callOutSelectBitmap = Bitmap.createScaledBitmap(selectBitmap, 50, 50, false);
        markerItem1.setIcon(markerBitmap); // 마커 아이콘 지정
        markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
        markerItem1.setTMapPoint(tMapPoint1); // 마커의 좌표 지정
        markerItem1.setName(name); // 마커의 타이틀 지정
        markerItem1.setCanShowCallout(true);
        markerItem1.setEnableClustering(false);
        markerItem1.setCalloutTitle(name);
        markerItem1.setCalloutSubTitle(subTitle);
        markerItem1.setCalloutRightButtonImage(callOutSelectBitmap);
        tMapView.addMarkerItem(name, markerItem1); // 지도에 마커 추가
        tMapView.setCenterPoint(longitude, latitude);
    }

    @Override
    public void showDetailInfoList(List<Reports> reportsList) {
        reportsDetailRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        reportsDetailInfoRecyclerViewAdapter = new ReportsDetailInfoRecyclerViewAdapter(reportsList);
        reportsDetailInfoRecyclerViewAdapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                reportsDetailOverview.toggle();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        reportsDetailRecyclerView.setAdapter(reportsDetailInfoRecyclerViewAdapter);
        reportsDetailFragmentPresenter.getImageInfo(contentId);
    }

    @Override
    public void showImageInfoList(List<Reports> reportsList) {
        reportsImageRecyclerView = view.findViewById(R.id.reports_Image_recyclerview);
        reportsImageRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        reportsImageRecyclerViewAdapter = new ReportsImageRecyclerViewAdapter(reportsList);
        reportsImageRecyclerView.setAdapter(reportsImageRecyclerViewAdapter);

    }

    @Override
    public void showCommonInfo(Reports reports) {
        contentTypeId = reports.getContenttypeid();
        contentThumbnail = reports.getFirstimage();
        areaCode = reports.getAreacode();
        mapX = reports.getMapx();
        mapY = reports.getMapy();
        setAddressInfo(Double.parseDouble(reports.getMapx()), Double.parseDouble(reports.getMapy()), title, reports.getAddr1());
        setFirstImageView(reports.getFirstimage());
        setTitle(title);
        setSummary(reports.getOverview());


    }

    private void showMapPoint(double x, double y, String title, String address) {
        reportsDetailMapLinearLayout.setVisibility(View.VISIBLE);
        TMapMarkerItem markerItem = new TMapMarkerItem();
        TMapPoint tMapPoint1 = new TMapPoint(y, x);
        selectedTMapMarkerItem = markerItem;
        markerItem.setVisible(TMapMarkerItem.VISIBLE);
        markerItem.setPosition(0f, 0f);
        markerItem.setTMapPoint(tMapPoint1);
        markerItem.setName(title);
        markerItem.setCanShowCallout(true);
        markerItem.setCalloutTitle(title);
        markerItem.setCalloutSubTitle(address);
        tMapView.addMarkerItem(title, markerItem);
        tMapView.setCenterPoint(x, y, true);
        tMapView.setZoomLevel(ZOOM_LEVEL);
        tMapView.initView();
    }

    public void setAddressInfo(double x, double y, String title, String address) {
        showMapPoint(x, y, title, address);
        reportsAddressTextView.setText(address);
    }

    private void setFirstImageView(String url) {
        Glide.with(getContext()).load(url)
                .apply(new RequestOptions().bitmapTransform(new RoundedCorners(24)))
                .error(R.drawable.ic_no_image)
                .into(reportsImageView);
    }

    private void setTitle(String text) {
        if (text == null) return;
        reportsDetailTitle.setText(text);
    }

    private void setSummary(String text) {
        if (text == null) return;
        reportsDetailOverview.setText(fromHtml(text));
        reportsDetailOverview.setChangeListener(this::toggle);
        toggle(reportsDetailOverview.getState());
    }

    private void toggle(ReadMoreTextView.State state){
        if(state == ReadMoreTextView.State.COLLAPSED){
            reportsDetailRecyclerView.setVisibility(View.GONE);
        }else{
            reportsDetailRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading_reports_info);
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void setPresenter(ReportsDetailFragmentPresenter presenter) {
        this.reportsDetailFragmentPresenter = presenter;
    }
}
