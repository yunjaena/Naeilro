package com.yunjaena.naeilro.admin.train

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.koreatech.core.toast.ToastUtil
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView
import com.yunjaena.naeilro.admin.NaeilroAdminApplication
import com.yunjaena.naeilro.admin.NaeilroAdminApplication.Companion.context
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
    private lateinit var restaurantButton: Button
    private lateinit var tMapView: TMapView
    private var selectedLatitude: Double? = null
    private var selectedLongitude: Double? = null
    private var tMapMarker: TMapMarkerItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_station_mange)
        init()
    }

    private fun init() {
        initView()
        initTMapView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.train_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_get_data -> {
                onClickedGetData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun initTMapView() {
        tMapView = TMapView(Objects.requireNonNull(this@TrainStationMangeActivity))
        with(tMapView) {
            this.setSKTMapApiKey(NaeilroAdminApplication.getTMapApiKey())
            this.setZoomLevel(8)
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
        restaurantButton = restaurant_button
        addButton = add_button
        searchButton.setOnClickListener {
            if (searchEditText.text.toString().isNotEmpty()) {
                hideKeyBoard(this@TrainStationMangeActivity)
                searchPOIByKeyWord(searchEditText.text.toString())
            }
        }
        move_to_selected_coordinate_button.setOnClickListener {
            moveToSelectedCurrentMarker()
        }
        addButton.setOnClickListener {
            onClickedCoordinateButton()
        }
        restaurantButton.setOnClickListener {
            searchNearRestaurant()
        }

    }

    private fun searchNearRestaurant() {
        tMapMarker?.let {
            findWithPosition("음식점", it.latitude, it.longitude)
        }
    }


    private fun findWithPosition(name: String, latitude: Double, longitude: Double) {
        var point: TMapPoint = TMapPoint(latitude, longitude)
        var tmapData = TMapData()
        tmapData.findAroundNamePOI(point, name, 3, 100) {
            for (item in it) {
                addPin(item.poiName, item.poiAddress?: "", item.poiPoint.longitude, item.poiPoint.latitude, R.drawable.icon_marker)

            }
        }
    }

    private fun searchPOIByKeyWord(keyWord: String) {
        val tMapData: TMapData = TMapData()
        tMapView.removeAllMarkerItem()
        tMapView.zoomLevel = 12
        tMapData.findAllPOI(keyWord) {
            for (tMapPOIItem in it) {
                addPin(tMapPOIItem.poiName.toString(), tMapPOIItem.poiAddress?: "", tMapPOIItem.poiPoint.longitude, tMapPOIItem.poiPoint.latitude, R.drawable.map_marker)
            }
        }
    }

    override fun onCalloutRightButton(p0: TMapMarkerItem?) {
        selectCoordinate(p0!!.longitude, p0!!.latitude)
        tMapMarker = p0

    }

    fun addPin(name: String, subTitle: String, longitude: Double, latitude: Double, @DrawableRes drawable: Int) {
        val markerItem1 = TMapMarkerItem()
        val tMapPoint1 = TMapPoint(latitude, longitude) // SKT타워
        val bitmap: Bitmap = BitmapFactory.decodeResource(context.getResources(), drawable)
        val markerBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false)
        val selectBitmap: Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_arrow_forward_white_36dp)
        val callOutSelectBitmap = Bitmap.createScaledBitmap(selectBitmap, 50, 50, false)
        markerItem1.icon = markerBitmap // 마커 아이콘 지정
        markerItem1.setPosition(0.5f, 1.0f) // 마커의 중심점을 중앙, 하단으로 설정
        markerItem1.tMapPoint = tMapPoint1 // 마커의 좌표 지정
        markerItem1.name = name // 마커의 타이틀 지정
        markerItem1.canShowCallout = true
        markerItem1.enableClustering = true
        markerItem1.calloutTitle = name
        markerItem1.calloutSubTitle = subTitle
        markerItem1.calloutRightButtonImage = callOutSelectBitmap
        tMapView.addMarkerItem(name, markerItem1) // 지도에 마커 추가
        tMapView.setCenterPoint(longitude, latitude)
    }

    private fun selectCoordinate(longitude: Double, latitude: Double) {
        val text = "x : $longitude Y : $latitude"
        coordinateTextView.text = text
        selectedLatitude = latitude
        selectedLongitude = longitude
    }


    private fun hideKeyBoard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun moveToSelectedCurrentMarker() {
        if (selectedLatitude != null && selectedLongitude != null) {
            tMapView.setCenterPoint(selectedLongitude!!, selectedLatitude!!)
        }
    }

    private fun onClickedGetData() {
        ToastUtil.getInstance().makeLong("불러왔습니다.")
    }

    private fun onClickedCoordinateButton() {
        ToastUtil.getInstance().makeLong("추가되었습니다.")
    }

}
