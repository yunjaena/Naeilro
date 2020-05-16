package com.koreatech.naeilro.ui.weather;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.OneWeekWeather;
import com.koreatech.naeilro.util.TemperatureUtil;
import com.koreatech.naeilro.util.TimeUtil;

import java.util.Locale;

public class WeatherInfoDialog extends Dialog {
    private OneWeekWeather oneWeekWeather;
    private TextView cityTextView;
    private ImageView currentWeatherImageView;
    private TextView currentDateTextView;
    private TextView currentMinTemperatureTextView;
    private TextView currentMaxTemperatureTextView;



    public WeatherInfoDialog(@NonNull Context context) {
        super(context);
    }

    public WeatherInfoDialog(@NonNull Context context, OneWeekWeather oneWeekWeather) {
        super(context);
        this.oneWeekWeather = oneWeekWeather;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.dialog_weather_info);
        init();

    }

    public void init() {
        cityTextView = findViewById(R.id.weather_info_city_textview);
        currentWeatherImageView = findViewById(R.id.weather_info_current_weather_info);
        currentMinTemperatureTextView = findViewById(R.id.weather_info_current_min_temperature);
        currentMaxTemperatureTextView = findViewById(R.id.weather_info_current_max_temperature);
        currentDateTextView = findViewById(R.id.weather_info_current_date_textview);
        cityTextView.setText(oneWeekWeather.getCity());
        currentDateTextView.setText(TimeUtil.getTimestampToDate(String.valueOf(oneWeekWeather.getDailyWeather().get(0).getDayTime())));
        Glide.with(getContext()).load(oneWeekWeather.getDailyWeather().get(0).getWeather().get(0).getIcon()).into(currentWeatherImageView);
        currentMinTemperatureTextView.setText(
                String.format(Locale.KOREA,"%.0f",TemperatureUtil.kelvinToCelsius(oneWeekWeather.getDailyWeather().get(0).getTemperature().getDayMinTemperature())));
        currentMaxTemperatureTextView.setText(
                String.format(Locale.KOREA,"%.0f",TemperatureUtil.kelvinToCelsius(oneWeekWeather.getDailyWeather().get(0).getTemperature().getDayMaxTemperature())));

        initOneWeek();
    }

    public void initOneWeek(){
        for(int i = 1 ; i < 7 ; i++){
            String dateTextViewStringId = "weather_info_weather_info_"+i+"_date_textview";
            String weatherImageViewStringId = "weather_info_weather_info_"+i+"_imageview";
            String weatherDescriptionStringId = "weather_info_weather_info_"+i+"_describe_textview";
            int dateTextViewId = getContext().getResources().getIdentifier(dateTextViewStringId, "id", getContext().getPackageName());
            int weatherImageViewId = getContext().getResources().getIdentifier(weatherImageViewStringId, "id", getContext().getPackageName());
            int weatherDescriptionTextViewId = getContext().getResources().getIdentifier(weatherDescriptionStringId, "id", getContext().getPackageName());
            TextView dateTextView = findViewById(dateTextViewId);
            ImageView weatherImageView = findViewById(weatherImageViewId);
            TextView weatherDescriptionTextView = findViewById(weatherDescriptionTextViewId);
            dateTextView.setText(TimeUtil.getTimestampToDate(String.valueOf(oneWeekWeather.getDailyWeather().get(i).getDayTime())));
            Glide.with(getContext()).load(oneWeekWeather.getDailyWeather().get(i).getWeather().get(0).getIcon()).into(weatherImageView);
            weatherDescriptionTextView.setText(
                    String.format(Locale.KOREA,"%.0f/%.0f\n%s",TemperatureUtil.kelvinToCelsius(oneWeekWeather.getDailyWeather().get(i).getTemperature().getDayMinTemperature())
            ,TemperatureUtil.kelvinToCelsius(oneWeekWeather.getDailyWeather().get(i).getTemperature().getDayMaxTemperature()), oneWeekWeather.getDailyWeather().get(i).getWeather().get(0).getCurrentWeatherDescription()
                    ));
        }
    }



}
