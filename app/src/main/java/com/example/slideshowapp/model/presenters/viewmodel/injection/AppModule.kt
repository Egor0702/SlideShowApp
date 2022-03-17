package com.example.slideshowapp.model.presenters.viewmodel.injection

import android.content.Context
import android.content.SharedPreferences
import com.example.slideshowapp.data.ImageRepositoryImpl
import com.example.slideshowapp.data.SharedPreferencesManager
import com.example.slideshowapp.domain.ImageRepository
import com.example.slideshowapp.model.ui.PermissionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val context: Context)  {

    @Provides
    @Singleton
    fun getContext() : Context = context

}