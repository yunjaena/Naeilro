package com.koreatech.naeilro.ui.festival;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.network.interactor.FestivalRestInteractor;
import com.koreatech.naeilro.ui.festival.presenter.FestivalDetailFragmentPresenter;
import com.koreatech.naeilro.ui.myplan.MyPlanBottomSheetActivity;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
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
import static com.koreatech.naeilro.ui.tourspot.TourSpotDetailFragment.fromHtml;


public class FestivalDetailFragment extends Fragment implements FestivalDetailFragmentContract.View {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    private static final int ZOOM_LEVEL = 17;
    @BindView(R.id.festival_detail_image)
    ImageView festivalDetailImage;
    @BindView(R.id.festival_detail_title)
    TextView festivalDetailTitle;
    @BindView(R.id.festival_detail_overview)
    ReadMoreTextView festivalDetailOverview;
    @BindView(R.id.festival_info_korean_text_view)
    TextView festivalInfoKoreanTextView;
    @BindView(R.id.festival_info_linear_layout)
    LinearLayout festivalInfoLinearLayout;
    @BindView(R.id.festival_start_date)
    TextView festivalStartDate;
    @BindView(R.id.festival_end_date)
    TextView festivalEndDate;
    @BindView(R.id.festival_playtime)
    TextView festivalPlayTime;
    @BindView(R.id.event_place)
    TextView eventPlace;
    @BindView(R.id.usetimefestival)
    TextView usedCost;
    @BindView(R.id.spendtimefestival)
    TextView spendTimeFestival;
    @BindView(R.id.discountinfofestival)
    TextView discountInfoFestival;
    @BindView(R.id.festival_detail_map_linear_layout)
    LinearLayout festivalDetailMapLinearLayout;
    @BindView(R.id.festival_detail_t_map_linear_layout)
    LinearLayout festivalDetailTMapLinearLayout;
    @BindView(R.id.festival_address_text_view)
    TextView festivalAddressTextView;
    private View view;
    private CheckBox restaurantCheckBox;
    private CheckBox convenienceStoreCheckBox;
    private TextView resetMapTextView;
    private TMapMarkerItem selectedTMapMarkerItem;
    private ArrayList<TMapPOIItem> restaurantIDArrayList;
    private ArrayList<TMapPOIItem> convenienceStoreIDArrayList;

    private FestivalDetailFragmentPresenter festivalDetailFragmentPresenter;
    private Unbinder unbinder;
    private NavController navController;
    private int contentTypeId;
    private String contentTypeIdString;
    private int contentId;
    private String contentTitle;
    private String contentThumbnail;
    private String tel;
    private String title;
    private TMapView tMapView;
    private String addr;
    private String mapX;
    private String mapY;
    private String areaCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_festival_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @OnClick(R.id.add_my_plan_festival)
    public void clickFestivalMyPlanButton() {
        Intent intent = new Intent(getActivity(), MyPlanBottomSheetActivity.class);
        intent.putExtra(CONTENT_ID, String.valueOf(contentId));
        intent.putExtra(CONTENT_TYPE, contentTypeIdString);
        intent.putExtra(CONTENT_TITLE, contentTitle);
        intent.putExtra(CONTENT_THUMBNAIL, contentThumbnail);
        intent.putExtra(CONTENT_MAP_X, mapX);
        intent.putExtra(CONTENT_MAP_Y, mapY);
        intent.putExtra(CONTENT_AREA_CODE, areaCode);
        startActivity(intent);
    }

    public void init(View view) {
        restaurantIDArrayList = new ArrayList<>();
        convenienceStoreIDArrayList = new ArrayList<>();
        festivalDetailFragmentPresenter = new FestivalDetailFragmentPresenter(new FestivalRestInteractor(), this);
        contentTypeId = getArguments().getInt("contentTypeId");
        contentTypeIdString = Integer.toString(contentTypeId);
        contentId = getArguments().getInt("contentId");
        addr = getArguments().getString("address");
        title = getArguments().getString("title");
        tel = getArguments().getString("tel");
        festivalDetailTitle.setText(title);
        initTMap(view);
        festivalDetailFragmentPresenter.getFestivalCommonInfo(contentId);
        festivalDetailFragmentPresenter.getFestivalIntroInfo(contentId);
        festivalInfoKoreanTextView.setOnClickListener(v -> festivalDetailOverview.toggle());
        festivalInfoLinearLayout.setOnClickListener(v -> festivalDetailOverview.toggle());
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
        festivalDetailTMapLinearLayout.addView(tMapView);

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
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    @Override
    public void showFestivalCommon(Festival festival) {

        //festivalDetailOverview.setText(setFilter(festival.getOverview()));
        contentTitle = festival.getTitle();
        contentThumbnail = festival.getFirstimage();
        areaCode = festival.getAreacode();
        mapX = festival.getMapx();
        mapY = festival.getMapy();
        setAddressInfo(Double.parseDouble(festival.getMapx()), Double.parseDouble(festival.getMapy()), festival.getTitle(), addr);
        setFirstImageView(festival.getFirstimage());
        setTitle(festival.getTitle());
        setSummary(festival.getOverview());
    }

    public void setAddressInfo(double x, double y, String title, String address) {
        showMapPoint(x, y, title, address);
        festivalAddressTextView.setText(address);
    }

    private void setFirstImageView(String url) {
        Glide.with(getContext()).load(url)
                .apply(new RequestOptions().bitmapTransform(new RoundedCorners(24)))
                .error(R.drawable.ic_no_image)
                .into(festivalDetailImage);
    }

    private void setTitle(String text) {
        if (text == null) return;
        festivalDetailTitle.setText(text);
    }

    private void setSummary(String text) {
        if (text == null) return;
        festivalDetailOverview.setText(fromHtml(text));
        festivalDetailOverview.setChangeListener(this::toggle);
        toggle(festivalDetailOverview.getState());
    }

    private void toggle(ReadMoreTextView.State state){
        if (state == ReadMoreTextView.State.COLLAPSED) {
            festivalInfoKoreanTextView.setVisibility(View.GONE);
            festivalInfoLinearLayout.setVisibility(View.GONE);
        } else {
            festivalInfoKoreanTextView.setVisibility(View.VISIBLE);
            festivalInfoLinearLayout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showFestivalIntro(Festival festival) {
        festivalStartDate.setText("   " + festival.getEventstartdate());
        festivalEndDate.setText("   " + festival.getEventenddate());
        if (festival.getPlaytime() == null)
            festivalPlayTime.setText("   -");
        else
            festivalPlayTime.setText("   " + festival.getPlaytime());

        eventPlace.setText("   " + festival.getEventplace());

        if (festival.getUsetimefestival() == null)
            usedCost.setText("   -");
        else
            usedCost.setText("   " + festival.getUsetimefestival());

        if (festival.getSpendtimefestival() == null)
            spendTimeFestival.setText("   -");
        else
            spendTimeFestival.setText("   " + festival.getSpendtimefestival());

        if (festival.getDiscountinfofestival() == null)
            discountInfoFestival.setText("   x");
        else
            discountInfoFestival.setText("   " + festival.getDiscountinfofestival());

    }

    private void showMapPoint(double x, double y, String title, String address) {
        festivalDetailMapLinearLayout.setVisibility(View.VISIBLE);
        TMapMarkerItem markerItem = new TMapMarkerItem();
        selectedTMapMarkerItem = markerItem;
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
        tMapView.setZoomLevel(ZOOM_LEVEL);
        tMapView.initView();
    }


    @Override
    public void setPresenter(FestivalDetailFragmentPresenter presenter) {
        this.festivalDetailFragmentPresenter = presenter;
    }

    public String setFilter(String overview) {
        String arrStr[] = overview.split("");
        String filteredString = "";
        boolean flag = false;
        String[] filter = {"<", ">", "=", "+", "?", "^", "$", "@", "*", "※", "/", "\\", "&"};
        for (int i = 0; i < arrStr.length; i++) {
            if (arrStr[i].equals("<")) {
                flag = true;
            }
            if (arrStr[i].equals(">")) {
                flag = false;
                continue;
            }
            if (arrStr[i].equals("&")) {
                if (arrStr[i + 1].equals("l") || arrStr[i + 1].equals("g")) {
                    if (arrStr[i + 2].equals(("t"))) {
                        i += 3;
                    }
                }
            }
            if (flag == false) {
                filteredString = filteredString.concat(arrStr[i]);
            }
        }
        return filteredString;
    }
}
