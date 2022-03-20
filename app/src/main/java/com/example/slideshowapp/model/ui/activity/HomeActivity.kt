package com.example.slideshowapp.model.ui.activity

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Process.killProcess
import android.os.Process.myPid
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.slideshowapp.R
import com.example.slideshowapp.data.service.BroadcactSlideShow
import com.example.slideshowapp.model.ui.fragment.BaseFragment
import com.example.slideshowapp.model.ui.fragment.HomeFragment

class HomeActivity : BaseActivity() {
    companion object{
        const val FINISH = "finish"
    }
    override var fragment: BaseFragment = HomeFragment()
    lateinit var broadcastSlideShow: BroadcactSlideShow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val intent = intent
            if (intent.getStringExtra(FINISH).equals("true")) {
                Log.d("Eg", "finish")
                finishWork()
            }
        }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // метод, который вызывается при вызове меню
        Log.d("Egor", "HomeActivity onOptionsItemSelected()")
        when (item?.itemId) {
            R.id.setting -> baseContext.startActivity(Intent(this,SettingActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
        return super.onOptionsItemSelected(item)
    }




}