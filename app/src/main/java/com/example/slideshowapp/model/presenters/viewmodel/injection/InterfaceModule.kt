package com.example.slideshowapp.model.presenters.viewmodel.injection

import android.content.Context
import android.content.SharedPreferences
import com.example.slideshowapp.data.ImageRepositoryImpl
import com.example.slideshowapp.data.SharedPreferencesManager
import com.example.slideshowapp.domain.ImageRepository
import com.example.slideshowapp.domain.usecase.GetImage
import com.example.slideshowapp.domain.usecase.GetListPhoto
import com.example.slideshowapp.model.presenters.viewmodel.ViewModelPhoto
import com.example.slideshowapp.model.ui.PermissionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InterfaceModule {
    @Provides
    @Singleton
    fun getImageRepository(context: Context, sharedPreferencesManager: SharedPreferencesManager) : ImageRepository = ImageRepositoryImpl(context, sharedPreferencesManager)

    @Provides
    @Singleton
     fun getSharedPreferencesManager(context: Context) : SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun getPermissionManager() : PermissionManager = PermissionManager()

}