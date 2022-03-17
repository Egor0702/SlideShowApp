package com.example.slideshowapp.model.ui

import android.app.Activity
import android.app.Application
import com.example.slideshowapp.domain.usecase.GetListPhoto
import com.example.slideshowapp.model.presenters.viewmodel.ViewModelPhoto
import com.example.slideshowapp.model.presenters.viewmodel.injection.AppModule
import com.example.slideshowapp.model.presenters.viewmodel.injection.InterfaceModule
import com.example.slideshowapp.model.presenters.viewmodel.injection.ViewModelModule
import com.example.slideshowapp.model.ui.activity.BaseActivity
import com.example.slideshowapp.model.ui.fragment.HomeFragment
import com.example.slideshowapp.model.ui.fragment.SettingFragment
import dagger.Component
import javax.inject.Singleton

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerApp_AppComponent.builder()
            .appModule(AppModule(this)).build()
    }

    @Singleton
    @Component(modules = [AppModule::class, ViewModelModule::class, InterfaceModule::class])
    interface AppComponent {
        fun inject (fragment: HomeFragment)
        fun inject(activity: BaseActivity)
        fun inject (fragment: SettingFragment)
    }
}