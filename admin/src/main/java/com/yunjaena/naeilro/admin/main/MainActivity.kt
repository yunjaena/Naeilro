package com.yunjaena.naeilro.admin.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yunjaena.naeilro.admin.R
import com.yunjaena.naeilro.admin.train.TrainStationMangeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add_train_station_position_linear_layout.setOnClickListener {
            Intent(this@MainActivity, TrainStationMangeActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}
