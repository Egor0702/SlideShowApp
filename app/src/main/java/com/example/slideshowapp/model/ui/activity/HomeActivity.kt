package com.example.slideshowapp.model.ui.activity

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.slideshowapp.R
import com.example.slideshowapp.model.ui.fragment.BaseFragment
import com.example.slideshowapp.model.ui.fragment.HomeFragment

class HomeActivity : BaseActivity() {
    override var fragment: BaseFragment = HomeFragment()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // метод, который вызывается при вызове меню
        Log.d("Egor", "HomeActivity onOptionsItemSelected()")
        when (item?.itemId) {
            R.id.setting -> applicationContext.startActivity(Intent(this,SettingActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}