package com.yunjaena.naeilro.admin.train

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapView
import com.yunjaena.naeilro.admin.NaeilroAdminApplication
import com.yunjaena.naeilro.admin.R
import kotlinx.android.synthetic.main.activity_train_station_mange.*
import java.util.*

class TrainStationMangeActivity : AppCompatActivity(), TMapView.OnCalloutRightButtonClickCallback {
    private val centerLon = 127.48318433761597
    private val centerLat = 36.41592967015607
    private lateinit var beforeButton: Button
    private lateinit var afterButton: Button
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var tMapLinearLayout: LinearLayout
    private lateinit var coordinateTextView: TextView
    private lateinit var addButton: Button
    private lateinit var tMapView: TMapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_station_mange)
        init()
    }

    private fun init() {
        initView()
        initTMapView()
    }

    private fun initTMapView() {
        tMapView = TMapView(Objects.requireNonNull(this@TrainStationMangeActivity))
        with(tMapView){
            this.setSKTMapApiKey(NaeilroAdminApplication.getTMapApiKey())
            this.setZoomLevel(4)
            this.setCenterPoint(centerLon, centerLat)
            this.setOnCalloutRightButtonClickListener(this@TrainStationMangeActivity)
        }
        tMapLinearLayout.addView(tMapView)
    }

    private fun initView() {
        beforeButton = before_button
        afterButton = after_button
        searchEditText = search_edit_text
        searchButton = search_button
        tMapLinearLayout = t_map_linear_layout
        coordinateTextView = coordinate_text_view
        addButton = add_button
    }

    override fun onCalloutRightButton(p0: TMapMarkerItem?) {

    }
}
