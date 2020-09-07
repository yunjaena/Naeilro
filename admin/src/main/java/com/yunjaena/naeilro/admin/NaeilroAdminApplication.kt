package com.yunjaena.naeilro.admin

import android.app.Application
import android.content.Context
import com.koreatech.core.toast.ToastUtil

class NaeilroAdminApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        init()
    }

    private fun init(){
        ToastUtil.getInstance().init(context)
    }

    companion object {
        lateinit var context: Context
        fun getDataApiKey(): String? {
            return context.resources.getString(R.string.data_api_key)
        }

        fun getTMapApiKey(): String? {
            return context.resources.getString(R.string.t_map_key)
        }

        fun getWeatherApiKey(): String? {
            return context.resources.getString(R.string.weather_api_key)
        }


    }
}