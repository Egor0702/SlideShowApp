package com.example.slideshowapp.model.presenters.viewmodel.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.slideshowapp.domain.usecase.GetImage
import com.example.slideshowapp.domain.usecase.GetListPhoto
import com.example.slideshowapp.model.presenters.viewmodel.ViewModelFactory
import com.example.slideshowapp.model.presenters.viewmodel.ViewModelPhoto
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelPhoto::class)
    abstract fun bindAccountViewModel(accountViewModel: ViewModelPhoto): ViewModel
}