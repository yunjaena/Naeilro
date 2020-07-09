package com.koreatech.naeilro.ui.festival;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.network.interactor.FestivalRestInteractor;
import com.koreatech.naeilro.ui.festival.presenter.FestivalDetailFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FestivalDetailFragment extends Fragment implements FestivalDetailFragmentContract.View {
    private View view;
    private FestivalDetailFragmentPresenter festivalDetailFragmentPresenter;
    private Unbinder unbinder;
    private NavController navController;
    private int contentTypeId;
    private int contentId;
    private String tel;
    private String title;
    @BindView(R.id.festival_detail_image)
    ImageView festivalDetailImage;
    @BindView(R.id.festival_detail_title)
    TextView festivalDetailTitle;
    @BindView(R.id.festival_detail_overview)
    TextView festivalDetailOverview;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_festival_detail, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    public void init(View view) {
        festivalDetailFragmentPresenter = new FestivalDetailFragmentPresenter(new FestivalRestInteractor(), this);
        contentTypeId = getArguments().getInt("contentTypeId");
        contentId = getArguments().getInt("contentId");
        title = getArguments().getString("title");
        tel = getArguments().getString("tel");
        festivalDetailTitle.setText(title);
        festivalDetailFragmentPresenter.getFestivalCommonInfo(contentTypeId, contentId);
        festivalDetailFragmentPresenter.getFestivalIntroInfo(contentTypeId, contentId);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder != null) unbinder.unbind();
    }
    @Override
    public void showFestivalCommon(Festival festival) {

        festivalDetailOverview.setText(setFilter(festival.getOverview()));
        String imageUrl = festival.getFirstimage();
        if (imageUrl == null) {
            Glide.with(festivalDetailImage).load(R.drawable.ic_no_image).into(festivalDetailImage);
        } else {
            Glide.with(festivalDetailImage).load(imageUrl).into(festivalDetailImage);
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
            if(arrStr[i].equals("&")){
                if (arrStr[i + 1].equals("l") || arrStr[i+1].equals("g")) {
                    if(arrStr[i+2].equals(("t"))){
                        i+=3;
                    }
                }
            }
            if(flag == false){
                filteredString = filteredString.concat(arrStr[i]);
            }
        }
        return  filteredString;
    }
}
