package com.example.slideshowapp.data

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(private val sharedPrefs: SharedPreferences) {

    companion object{
        const val TIME = "time"
    }

    fun getTime() = sharedPrefs.getLong(TIME,1000L)

    fun setTime(long:Long) = sharedPrefs.edit().apply {
        putLong(TIME,long)
    }.apply()
}