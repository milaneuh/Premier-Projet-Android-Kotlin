package com.example.premierprojet.utils

import android.os.SystemClock
import com.example.premierprojet.beans.WeatherBean
import kotlin.random.Random.Default.nextBoolean

class WSUtils {
    companion object {
        fun loadWeather(latitude: Double, longitude: Double): WeatherBean {
            SystemClock.sleep(5000)  //Attendre 5 secondes
            return WeatherBean("Localisation",0,longitude,latitude)
        }
    }
}