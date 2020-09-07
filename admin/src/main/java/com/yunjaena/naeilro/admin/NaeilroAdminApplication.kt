package com.yunjaena.naeilro.admin

import android.app.Application
import android.content.Context

class NaeilroAdminApplication : Application() {
    companion object {
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()
        context = this
    }

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