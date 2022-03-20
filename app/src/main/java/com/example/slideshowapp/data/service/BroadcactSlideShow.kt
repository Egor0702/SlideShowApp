package com.example.slideshowapp.data.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.example.slideshowapp.model.ui.activity.HomeActivity

class BroadcactSlideShow : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("Eg", "BroadcactSlideShow")
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent?.action)) {
            Log.d("Eg", "BroadcactSlideShow if")
//            val serviceIntent = Intent(context, SlideShowService::class.java)
//            context?.startService(serviceIntent)

            val i = Intent(context, HomeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            Log.d("Eg", "context = ${context==null} i = ${i==null}")
           context?.startActivity(i)
        }
        if(Intent.ACTION_BATTERY_CHANGED.equals(intent?.action)){
            Log.d("Eg", "BroadcactSlideShow battery if")
            val i = Intent(context, HomeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            Log.d("Eg", "context = ${context==null} i = ${i==null}")
            context?.startActivity(i)
        }
        }
    }
