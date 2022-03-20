package com.example.slideshowapp.data.service

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.example.slideshowapp.model.ui.App
import com.example.slideshowapp.model.ui.activity.HomeActivity
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class SlideShowService : Service() {

    companion object{
        const val TIMESTART = "timestart"
        const val TIMEFINISH = "timefinish"
        const val FINISH = "finish"
    }
    val backgroundContext: CoroutineContext = Dispatchers.IO
    val foregroundContext: CoroutineContext = Dispatchers.Main
    var parentJob: Job = Job()
    val dateFormat = SimpleDateFormat("HH:mm")
    @Inject
     lateinit var context: Context

    override fun onCreate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val NOTIFICATION_CHANNEL_ID = "Chanel"
            val channelName: String = "Chanel"
            var chan: NotificationChannel? = null
            chan = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE)
            chan.lightColor = Color.BLUE
            chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager?.createNotificationChannel(chan)
            val builder: Notification.Builder = Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.sym_def_app_icon)
                    .setOngoing(true)
                    .setSmallIcon(R.mipmap.sym_def_app_icon)
                    .setContentTitle(getString(R.string.untitled))
                    .setAutoCancel(true)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
            val notification = builder.build()
            startForeground(2, notification)
        }
        val broadcactSlideShow = BroadcactSlideShow()
        val batteryLevelFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val downloadFilter= IntentFilter(Intent.ACTION_BOOT_COMPLETED)
        registerReceiver(broadcactSlideShow,downloadFilter)
        registerReceiver(broadcactSlideShow, batteryLevelFilter)
        App.appComponent.inject(this)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startCommand(intent?.extras?.getString(TIMESTART))
        finishCommand(intent?.extras?.getString(TIMEFINISH))
        return super.onStartCommand(intent, flags, startId)
        }
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
    }

    fun startCommand(time: String?){
        if (time!= null){
            Log.d("Eg", "Service startCommand")
            unsubscribe()
            parentJob = Job()
            CoroutineScope(foregroundContext + parentJob).launch {
                withContext(backgroundContext){
                    while (true){
                        val calendar : Calendar = Calendar.getInstance()
                        val date: Date = calendar.time
                        val timeSystem: String = dateFormat.format(date).trim()
//                        Log.d("Eg", "timeSystem = $timeSystem time = $time")
                        if (timeSystem.equals(time)) {
                            Log.d("Eg", "StartCommand наконец-то")
                            return@withContext
                        }
                    }
                }
                Log.d("Eg", "StartCommand вышли из цикла")
               val intent = Intent(context, HomeActivity::class.java).apply {
                   addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
               }
                context.startActivity(intent)
            }
        }else
            return
}
    fun finishCommand(time: String?){
        if (time!= null){
            unsubscribe()
            parentJob = Job()
            CoroutineScope(foregroundContext + parentJob).launch {
                withContext(backgroundContext) {
                    Log.d("Eg", "stop-stop1")
                    while (true) {
                        val calendar : Calendar = Calendar.getInstance()
                        val date = calendar.time
                        val timeSystem: String = dateFormat.format(date).trim()
                        Log.d("Eg", "timeSystem = $timeSystem time = $time")
                        if (timeSystem.equals(time))
                            return@withContext
                    }
                }
                Log.d("Eg", "stop-stop")
                val intent = Intent(context, HomeActivity::class.java).apply {
                    putExtra(FINISH, "true")
                }
                startActivity(intent)
            }
        }else
            return
}
    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}